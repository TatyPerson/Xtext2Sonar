package org.sonar.Thing.plugin.squid;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.Nullable;

import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.rule.ActiveRules;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.rule.Checks;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.component.Perspective;
import org.sonar.api.config.Settings;
import org.sonar.api.issue.Issuable;
import org.sonar.api.issue.Issue;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.PersistenceMode;
import org.sonar.api.measures.RangeDistributionBuilder;
import org.sonar.api.resources.Project;
import org.sonar.Thing.checks.CompileIncludePathNotFoundOrInvalid;
import org.sonar.Thing.api.ThingAstScanner;
import org.sonar.Thing.ThingConfiguration;
import org.sonar.Thing.api.ThingMetric;
import org.sonar.Thing.checks.CheckList;
import org.sonar.Thing.plugin.ThingLanguage;
import org.sonar.Thing.plugin.ThingPlugin;
import org.sonar.Thing.plugin.highlighter.ThingHighlighter;
import org.sonar.Thing.plugin.utils.ThingMetrics;
import org.sonar.squidbridge.AstScanner;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.api.CheckMessage;
import org.sonar.squidbridge.api.SourceCode;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.api.SourceFunction;
import org.sonar.squidbridge.indexer.QueryByParent;
import org.sonar.squidbridge.indexer.QueryByType;
import org.sonar.api.source.Highlightable;

import com.sonar.sslr.api.Grammar;

/**
 * {@inheritDoc}
 */
public final class ThingSquidSensor implements Sensor {
  private static final Number[] FUNCTIONS_DISTRIB_BOTTOM_LIMITS = {1, 2, 4, 6, 8, 10, 12, 20, 30};
  private static final Number[] FILES_DISTRIB_BOTTOM_LIMITS = {0, 5, 10, 20, 30, 60, 90};

  private final Checks<Object> checks;
  private ActiveRules rules;

  private Project project;
  private SensorContext context;
  private AstScanner<Grammar> scanner;
  private Settings conf;
  private FileSystem fs;
  private ResourcePerspectives resourcePerspectives;
  private final FilePredicate mainFilePredicate;
  /**
   * {@inheritDoc}
   */
  public ThingSquidSensor(ResourcePerspectives resourcePerspectives, Settings conf,
                        FileSystem fs, CheckFactory checkFactory, ActiveRules rules) {
    this.checks = checkFactory.create(CheckList.REPOSITORY_KEY).addAnnotatedChecks(CheckList.getChecks());
    this.rules = rules;
    this.conf = conf;
    this.fs = fs;
    this.resourcePerspectives = resourcePerspectives;
    FilePredicates predicates = fs.predicates();
    this.mainFilePredicate = predicates.and(predicates.hasType(InputFile.Type.MAIN),
                                            predicates.hasLanguage(ThingLanguage.KEY));
  }

  public boolean shouldExecuteOnProject(Project project) {
    return fs.hasFiles(mainFilePredicate);
  }

  /**
   * {@inheritDoc}
   */
  public void analyse(Project project, SensorContext context) {
	  
    this.project = project;
    this.context = context;

    List<SquidAstVisitor<Grammar>> visitors = new ArrayList<SquidAstVisitor<Grammar>>((Collection) checks.all());
    this.scanner = ThingAstScanner.create(createConfiguration(this.fs, this.conf),
                                        visitors.toArray(new SquidAstVisitor[visitors.size()]));

    scanner.scanFiles(Lists.newArrayList(fs.files(mainFilePredicate)));

    Collection<SourceCode> squidSourceFiles = scanner.getIndex().search(new QueryByType(SourceFile.class));
    save(squidSourceFiles);
    
    highlight();
  }

  private void highlight() {
    ThingHighlighter highlighter = new ThingHighlighter(createConfiguration(fs, conf));
    for (InputFile inputFile : fs.inputFiles(mainFilePredicate)) {
      highlighter.highlight(perspective(Highlightable.class, inputFile), inputFile.file());
    }
  }
  
  <P extends Perspective<?>> P perspective(Class<P> clazz, @Nullable InputFile file) {
    if (file == null) {
      throw new IllegalArgumentException("Cannot get " + clazz.getCanonicalName() + "for a null file");
    }
    P result = resourcePerspectives.as(clazz, file);
    if (result == null) {
      throw new IllegalStateException("Could not get " + clazz.getCanonicalName() + " for " + file);
    }
    return result;
  }

  private ThingConfiguration createConfiguration(FileSystem fs, Settings conf) {
    ThingConfiguration ThingConf = new ThingConfiguration(fs, resourcePerspectives, CompileIncludePathNotFoundOrInvalid.getActiveRule(rules));
    ThingConf.setBaseDir(fs.baseDir().getAbsolutePath());
    ThingConf.setErrorRecoveryEnabled(conf.getBoolean(ThingPlugin.ERROR_RECOVERY_KEY));
    return ThingConf;
  }

  private void save(Collection<SourceCode> squidSourceFiles) {
    int violationsCount = 0;
    DependencyAnalyzer dependencyAnalyzer = new DependencyAnalyzer(resourcePerspectives, project, context, rules);
    for (SourceCode squidSourceFile : squidSourceFiles) {
      SourceFile squidFile = (SourceFile) squidSourceFile;
      File ioFile = new File(squidFile.getKey());

      org.sonar.api.resources.File sonarFile = org.sonar.api.resources.File.fromIOFile(ioFile, project); //@todo fromIOFile: deprecated, see http://javadocs.sonarsource.org/4.5.2/apidocs/deprecated-list.html

      saveMeasures(sonarFile, squidFile);
      saveFilesComplexityDistribution(sonarFile, squidFile);
      saveFunctionsComplexityDistribution(sonarFile, squidFile);
      violationsCount += saveViolations(sonarFile, squidFile);
      dependencyAnalyzer.addFile(sonarFile/*, ThingParser.getIncludedFiles(ioFile)*/);
    }

    Measure measure = new Measure(ThingMetrics.SQUID);
    measure.setIntValue(violationsCount);
    context.saveMeasure(measure);
    dependencyAnalyzer.save();
  }

  private void saveMeasures(org.sonar.api.resources.File sonarFile, SourceFile squidFile) {
    context.saveMeasure(sonarFile, CoreMetrics.FILES, squidFile.getDouble(ThingMetric.FILES));
    context.saveMeasure(sonarFile, CoreMetrics.LINES, squidFile.getDouble(ThingMetric.LINES));
    context.saveMeasure(sonarFile, CoreMetrics.NCLOC, squidFile.getDouble(ThingMetric.LINES_OF_CODE));
    context.saveMeasure(sonarFile, CoreMetrics.STATEMENTS, squidFile.getDouble(ThingMetric.STATEMENTS));
    context.saveMeasure(sonarFile, CoreMetrics.FUNCTIONS, squidFile.getDouble(ThingMetric.FUNCTIONS));
    context.saveMeasure(sonarFile, CoreMetrics.COMPLEXITY, squidFile.getDouble(ThingMetric.COMPLEXITY));
    context.saveMeasure(sonarFile, CoreMetrics.COMMENT_LINES, squidFile.getDouble(ThingMetric.COMMENT_LINES));
    context.saveMeasure(sonarFile, CoreMetrics.PUBLIC_API, squidFile.getDouble(ThingMetric.PUBLIC_API));
  }

  private void saveFunctionsComplexityDistribution(org.sonar.api.resources.File sonarFile, SourceFile squidFile) {
    Collection<SourceCode> squidFunctionsInFile = scanner.getIndex().search(new QueryByParent(squidFile), new QueryByType(SourceFunction.class));
    RangeDistributionBuilder complexityDistribution = new RangeDistributionBuilder(CoreMetrics.FUNCTION_COMPLEXITY_DISTRIBUTION, FUNCTIONS_DISTRIB_BOTTOM_LIMITS);
    for (SourceCode squidFunction : squidFunctionsInFile) {
      complexityDistribution.add(squidFunction.getDouble(ThingMetric.COMPLEXITY));
    }
    context.saveMeasure(sonarFile, complexityDistribution.build().setPersistenceMode(PersistenceMode.MEMORY));
  }

  private void saveFilesComplexityDistribution(org.sonar.api.resources.File sonarFile, SourceFile squidFile) {
    RangeDistributionBuilder complexityDistribution = new RangeDistributionBuilder(CoreMetrics.FILE_COMPLEXITY_DISTRIBUTION, FILES_DISTRIB_BOTTOM_LIMITS);
    complexityDistribution.add(squidFile.getDouble(ThingMetric.COMPLEXITY));
    context.saveMeasure(sonarFile, complexityDistribution.build().setPersistenceMode(PersistenceMode.MEMORY));
  }

  private int saveViolations(org.sonar.api.resources.File sonarFile, SourceFile squidFile) {
    Collection<CheckMessage> messages = squidFile.getCheckMessages();
    int violationsCount = 0;
    if (messages != null) {
      Issuable issuable = resourcePerspectives.as(Issuable.class, sonarFile);
      if (issuable != null) {
        for (CheckMessage message : messages) {
          Issue issue = issuable.newIssueBuilder()
            .ruleKey(checks.ruleKey(message.getCheck()))
            .line(message.getLine())
            .message(message.getText(Locale.ENGLISH))
            .build();
          if (issuable.addIssue(issue))
            violationsCount++;
        }
      }
      return violationsCount;
    }
    return 0;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

}
