package org.sonar.VaryGrammar.plugin.squid;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.rule.ActiveRules;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.design.Dependency;
import org.sonar.api.issue.Issuable;
import org.sonar.api.issue.Issue;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.PersistenceMode;
import org.sonar.api.resources.Directory;
import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.graph.Cycle;
import org.sonar.graph.DirectedGraph;
import org.sonar.graph.Dsm;
import org.sonar.graph.DsmTopologicalSorter;
import org.sonar.graph.Edge;
import org.sonar.graph.IncrementalCyclesAndFESSolver;
import org.sonar.graph.MinimumFeedbackEdgeSetSolver;
import org.sonar.VaryGrammar.plugin.utils.VaryGrammarMetrics;
import org.sonar.VaryGrammar.plugin.utils.VaryGrammarUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class DependencyAnalyzer {

  private Project project;
  private SensorContext context;
  private ResourcePerspectives perspectives;
  private int violationsCount;
  private ActiveRule duplicateIncludeRule;
  private ActiveRule cycleBetweenPackagesRule;

  private DirectedGraph<File, FileEdge> filesGraph = new DirectedGraph<File, FileEdge>();
  private DirectedGraph<Directory, DirectoryEdge> packagesGraph = new DirectedGraph<Directory, DirectoryEdge>();
  private Map<Edge, Dependency> dependencyIndex = new HashMap<Edge, Dependency>();
  private Multimap<Directory, File> directoryFiles = HashMultimap.create();

  public DependencyAnalyzer(ResourcePerspectives perspectives, Project project, SensorContext context, ActiveRules rules) {
    this.project = project;
    this.context = context;
    this.perspectives = perspectives;

    this.violationsCount = 0;
  }

  public void addFile(File sonarFile/*, Collection<CxxPreprocessor.Include> includedFiles*/) {
    //Store the directory and file
    Directory sonarDir = sonarFile.getParent();
    packagesGraph.addVertex(sonarDir);
    directoryFiles.put(sonarDir, sonarFile);

    //Build the dependency graph
  }

  /** Perform the analysis and save the results.
   */
  public void save() {
    final Collection<Directory> packages = packagesGraph.getVertices();
    for (Directory dir : packages) {
      //Save dependencies (cross-directories, including cross-directory file dependencies)
      for (DirectoryEdge edge : packagesGraph.getOutgoingEdges(dir)) {
        Dependency dependency = new Dependency(dir, edge.getTo())
            .setUsage("references")
            .setWeight(edge.getWeight())
            .setParent(null);
        context.saveDependency(dependency);
        dependencyIndex.put(edge, dependency);

        for(FileEdge subEdge : edge.getRootEdges()) {
          saveFileEdge(subEdge, dependency);
        }
      }

      //Save file dependencies (inside directory) & directory metrics
      saveDirectory(dir);
    }

    IncrementalCyclesAndFESSolver<Directory> cycleDetector = new IncrementalCyclesAndFESSolver<Directory>(packagesGraph, packages);
    Set<Cycle> cycles = cycleDetector.getCycles();
    Set<Edge> feedbackEdges = cycleDetector.getFeedbackEdgeSet();
    int tangles = cycleDetector.getWeightOfFeedbackEdgeSet();

    VaryGrammarUtils.LOG.info("Project '" + project.getKey() + "'"
        + " Cycles:" + cycles.size()
        + " Feedback cycles:" + feedbackEdges.size()
        + " Tangles:" + tangles
        + " Weight:" + getEdgesWeight(packagesGraph.getEdges(packages)));

    saveViolations(feedbackEdges, packagesGraph);
    savePositiveMeasure(project, CoreMetrics.PACKAGE_CYCLES, cycles.size());
    savePositiveMeasure(project, CoreMetrics.PACKAGE_FEEDBACK_EDGES, feedbackEdges.size());
    savePositiveMeasure(project, CoreMetrics.PACKAGE_TANGLES, tangles);
    savePositiveMeasure(project, CoreMetrics.PACKAGE_EDGES_WEIGHT, getEdgesWeight(packagesGraph.getEdges(packages)));

    String dsmJson = serializeDsm(packages, feedbackEdges);
    Measure dsmMeasure = new Measure(CoreMetrics.DEPENDENCY_MATRIX, dsmJson)
        .setPersistenceMode(PersistenceMode.DATABASE);
    context.saveMeasure(project, dsmMeasure);
  }

  private void saveDirectory(Directory dir) {
    final Collection<File> files = directoryFiles.get(dir);
    for(File file: files) {
      for (FileEdge edge : filesGraph.getOutgoingEdges(file)) {
        saveFileEdge(edge, null);
      }
    }

    IncrementalCyclesAndFESSolver<File> cycleDetector = new IncrementalCyclesAndFESSolver<File>(filesGraph, files);
    Set<Cycle> cycles = cycleDetector.getCycles();
    MinimumFeedbackEdgeSetSolver solver = new MinimumFeedbackEdgeSetSolver(cycles);
    Set<Edge> feedbackEdges = solver.getEdges();
    int tangles = solver.getWeightOfFeedbackEdgeSet();

    VaryGrammarUtils.LOG.info("Directory: '" + dir.getKey() + "'"
        + " Cycles:" + cycles.size()
        + " Feedback cycles:" + feedbackEdges.size()
        + " Tangles:" + tangles
        + " Weight:" + getEdgesWeight(filesGraph.getEdges(files)));

    savePositiveMeasure(dir, CoreMetrics.FILE_CYCLES, cycles.size());
    savePositiveMeasure(dir, CoreMetrics.FILE_FEEDBACK_EDGES, feedbackEdges.size());
    savePositiveMeasure(dir, CoreMetrics.FILE_TANGLES, tangles);
    savePositiveMeasure(dir, CoreMetrics.FILE_EDGES_WEIGHT, getEdgesWeight(filesGraph.getEdges(files)));

    String dsmJson = serializeDsm(files, feedbackEdges);
    context.saveMeasure(dir, new Measure(CoreMetrics.DEPENDENCY_MATRIX, dsmJson));
  }

  private void saveViolations(Set<Edge> feedbackEdges, DirectedGraph<Directory, DirectoryEdge> packagesGraph) {
    if (cycleBetweenPackagesRule != null) {
      for (Edge feedbackEdge : feedbackEdges) {
        Directory fromPackage = (Directory) feedbackEdge.getFrom();
        Directory toPackage = (Directory) feedbackEdge.getTo();
        DirectoryEdge edge = packagesGraph.getEdge(fromPackage, toPackage);
        for (FileEdge subEdge : edge.getRootEdges()) {
          Resource fromFile = subEdge.getFrom();
          Resource toFile = subEdge.getTo();
          Issuable issuable = perspectives.as(Issuable.class, fromFile);
          // If resource cannot be obtained, then silently ignore, because anyway warning will be printed by method addFile
          if ((issuable != null) && (fromFile != null) && (toFile != null)) {
            Issue issue = issuable.newIssueBuilder()
                .ruleKey(cycleBetweenPackagesRule.ruleKey())
                .line(subEdge.getLine())
                .message("Remove the dependency from file \"" + fromFile.getLongName()
                    + "\" to file \"" + toFile.getLongName() + "\" to break a package cycle.")
                .effortToFix((double) subEdge.getWeight())
                .build();
            if (issuable.addIssue(issue))
              violationsCount++;
          }
        }
      }
    }
    if (cycleBetweenPackagesRule != null || duplicateIncludeRule != null) {
      Measure measure = new Measure(VaryGrammarMetrics.DEPENDENCIES);
      measure.setIntValue(violationsCount);
      context.saveMeasure(measure);
    }
  }

  private void saveFileEdge(FileEdge edge, Dependency parent) {
    if (!dependencyIndex.containsKey(edge)) {
      Dependency dependency = new Dependency(edge.getFrom(), edge.getTo())
          .setUsage("includes")
          .setWeight(edge.getWeight())
          .setParent(parent);
      context.saveDependency(dependency);
      dependencyIndex.put(edge, dependency);
    }
  }

  private double getEdgesWeight(Collection<? extends Edge> edges) {
    double total = 0.0;
    for (Edge edge : edges) {
      total += edge.getWeight();
    }
    return total;
  }

  private String serializeDsm(Collection<? extends Resource> vertices, Set<Edge> feedbackEdges) {
    Dsm<? extends Resource> dsm = new Dsm(packagesGraph, vertices, feedbackEdges);
    DsmTopologicalSorter.sort(dsm);
    return DsmSerializer.serialize(dsm, dependencyIndex);
  }

  private void savePositiveMeasure(Resource sonarResource, Metric metric, double value) {
    if (value >= 0.0) {
      context.saveMeasure(sonarResource, metric, value);
    }
  }

}
