package org.sonar.LilyPond.plugin.coverage;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.bootstrap.ProjectReactor;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.CoverageMeasuresBuilder;
import org.sonar.api.measures.Measure;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.PropertiesBuilder;
import org.sonar.api.resources.Project;
import org.sonar.LilyPond.plugin.LilyPondLanguage;
import org.sonar.LilyPond.plugin.utils.LilyPondReportSensor;
import org.sonar.LilyPond.plugin.utils.LilyPondUtils;
/**
 * {@inheritDoc}
 */
public class LilyPondCoverageSensor extends LilyPondReportSensor {
  private enum CoverageType {
    UT_COVERAGE, IT_COVERAGE, OVERALL_COVERAGE
  }

  public static final String REPORT_PATH_KEY = "sonar.lilypond.coverage.reportPath";
  public static final String IT_REPORT_PATH_KEY = "sonar.lilypond.coverage.itReportPath";
  public static final String OVERALL_REPORT_PATH_KEY = "sonar.lilypond.coverage.overallReportPath";
  private static final String DEFAULT_REPORT_PATH = "coverage-reports/coverage-*.xml";
  private static final String IT_DEFAULT_REPORT_PATH = "coverage-reports/it-coverage-*.xml";
  private static final String OVERALL_DEFAULT_REPORT_PATH = "coverage-reports/overall-coverage-*.xml";

  public static final String FORCE_ZERO_COVERAGE_KEY = "sonar.lilypond.coverage.forceZeroCoverage";

  private static List<CoverageParser> parsers = new LinkedList<CoverageParser>();
    private final ProjectReactor reactor;

  /**
   * {@inheritDoc}
   */
  public LilyPondCoverageSensor(Settings settings, FileSystem fs, ProjectReactor reactor) {
    super(settings, fs, reactor);

    this.reactor = reactor;
    parsers.add(new CoberturaParser());
    parsers.add(new BullseyeParser());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void analyse(Project project, SensorContext context) {

    LilyPondUtils.LOG.debug("Parsing coverage reports");
    List<File> reports = getReports(conf, reactor.getRoot().getBaseDir().getAbsolutePath(), REPORT_PATH_KEY, DEFAULT_REPORT_PATH);

    Map<String, CoverageMeasuresBuilder> coverageMeasures = processReports(project, context, reports);
    saveMeasures(project, context, coverageMeasures, CoverageType.UT_COVERAGE);

    LilyPondUtils.LOG.debug("Parsing integration test coverage reports");
    List<File> itReports = getReports(conf, reactor.getRoot().getBaseDir().getAbsolutePath(), IT_REPORT_PATH_KEY, IT_DEFAULT_REPORT_PATH);
    Map<String, CoverageMeasuresBuilder> itCoverageMeasures = processReports(project, context, itReports);
    saveMeasures(project, context, itCoverageMeasures, CoverageType.IT_COVERAGE);

    LilyPondUtils.LOG.debug("Parsing overall test coverage reports");
    List<File> overallReports = getReports(conf, reactor.getRoot().getBaseDir().getAbsolutePath(), OVERALL_REPORT_PATH_KEY, OVERALL_DEFAULT_REPORT_PATH);
    Map<String, CoverageMeasuresBuilder> overallCoverageMeasures = processReports(project, context, overallReports);
    saveMeasures(project, context, overallCoverageMeasures, CoverageType.OVERALL_COVERAGE);

    if (isForceZeroCoverageActivated()) {
      LilyPondUtils.LOG.debug("Zeroing coverage information for untouched files");
      zeroMeasuresWithoutReports(project, context, coverageMeasures,
                                 itCoverageMeasures, overallCoverageMeasures);
    }
  }

  private Map<String, CoverageMeasuresBuilder> processReports(final Project project, final SensorContext context, List<File> reports) {
    Map<String, CoverageMeasuresBuilder> measuresTotal = new HashMap<String, CoverageMeasuresBuilder>();
    Map<String, CoverageMeasuresBuilder> measuresForReport = new HashMap<String, CoverageMeasuresBuilder>();

    for (File report : reports) {
      LilyPondUtils.LOG.info("Processing report '{}'", report);
      boolean parsed = false;
      for (CoverageParser parser : parsers) {
        try {
          measuresForReport.clear();
          parser.processReport(project, context, report, measuresForReport);

          if (!measuresForReport.isEmpty()) {
            parsed = true;
            measuresTotal.putAll(measuresForReport);
            LilyPondUtils.LOG.info("Added report '{}' (parsed by: {}) to the coverage data", report, parser);
            break;
          }
        } catch (XMLStreamException e) {
          LilyPondUtils.LOG.trace("Report {} cannot be parsed by {}", report, parser);
        }
      }

      if (!parsed) {
        LilyPondUtils.LOG.error("Report {} cannot be parsed", report);
      }
    }

    return measuresTotal;
  }

  private void saveMeasures(Project project,
    SensorContext context,
    Map<String, CoverageMeasuresBuilder> coverageMeasures,
    CoverageType ctype) {
    for (Map.Entry<String, CoverageMeasuresBuilder> entry : coverageMeasures.entrySet()) {
      String filePath = entry.getKey();
      org.sonar.api.resources.File cxxfile
        = org.sonar.api.resources.File.fromIOFile(new File(filePath), project);
      if (fileExist(context, cxxfile)) {
        LilyPondUtils.LOG.debug("Saving coverage measures for file '{}'", filePath);
        for (Measure measure : entry.getValue().createMeasures()) {
          switch (ctype) {
            case IT_COVERAGE:
              measure = convertToItMeasure(measure);
              break;
            case OVERALL_COVERAGE:
              measure = convertForOverall(measure);
              break;
            default:
          }
          context.saveMeasure(cxxfile, measure);
        }
      } else {
        LilyPondUtils.LOG.warn("Cannot find the file '{}', ignoring coverage measures", filePath);
      }
    }
  }

  private void zeroMeasuresWithoutReports(Project project,
    SensorContext context,
    Map<String, CoverageMeasuresBuilder> coverageMeasures,
    Map<String, CoverageMeasuresBuilder> itCoverageMeasures,
    Map<String, CoverageMeasuresBuilder> overallCoverageMeasures
    ) {
    for (File file : fs.files(fs.predicates().hasLanguage(LilyPondLanguage.KEY))) {
      org.sonar.api.resources.File resource = org.sonar.api.resources.File.fromIOFile(file, project);
      if (fileExist(context, resource)) {
        String filePath = LilyPondUtils.normalizePath(file.getAbsolutePath());

        if (coverageMeasures.get(filePath) == null) {
          saveZeroValueForResource(resource, filePath, context, CoverageType.UT_COVERAGE);
        }

        if (itCoverageMeasures.get(filePath) == null) {
          saveZeroValueForResource(resource, filePath, context, CoverageType.IT_COVERAGE);
        }

        if (overallCoverageMeasures.get(filePath) == null) {
          saveZeroValueForResource(resource, filePath, context, CoverageType.OVERALL_COVERAGE);
        }
      }
    }
  }

  private void saveZeroValueForResource(org.sonar.api.resources.File resource,
                                        String filePath,
                                        SensorContext context,
                                        CoverageType ctype) {

    Measure ncloc = context.getMeasure(resource, CoreMetrics.NCLOC);
    Measure stmts = context.getMeasure(resource, CoreMetrics.STATEMENTS);
    if (ncloc != null && stmts != null
        && ncloc.getValue() > 0 && stmts.getValue() > 0) {
      String coverageKind = "unit test ";
      Metric hitsDataMetric = CoreMetrics.COVERAGE_LINE_HITS_DATA;
      Metric linesToCoverMetric = CoreMetrics.LINES_TO_COVER;
      Metric uncoveredLinesMetric = CoreMetrics.UNCOVERED_LINES;

      switch(ctype){
      case IT_COVERAGE:
        coverageKind = "integration test ";
        hitsDataMetric = CoreMetrics.IT_COVERAGE_LINE_HITS_DATA;
        linesToCoverMetric = CoreMetrics.IT_LINES_TO_COVER;
        uncoveredLinesMetric = CoreMetrics.IT_UNCOVERED_LINES;
        break;
      case OVERALL_COVERAGE:
        coverageKind = "overall ";
        hitsDataMetric = CoreMetrics.OVERALL_COVERAGE_LINE_HITS_DATA;
        linesToCoverMetric = CoreMetrics.OVERALL_LINES_TO_COVER;
        uncoveredLinesMetric = CoreMetrics.OVERALL_UNCOVERED_LINES;
        break;
      default:
      }

      LilyPondUtils.LOG.debug("Zeroing {}coverage measures for file '{}'", coverageKind, filePath);

      PropertiesBuilder<Integer, Integer> lineHitsData = new PropertiesBuilder<Integer, Integer>(hitsDataMetric);
      for (int i = 1; i <= context.getMeasure(resource, CoreMetrics.LINES).getIntValue(); ++i) {
        lineHitsData.add(i, 0);
      }
      context.saveMeasure(resource, lineHitsData.build());
      context.saveMeasure(resource, linesToCoverMetric, ncloc.getValue());
      context.saveMeasure(resource, uncoveredLinesMetric, ncloc.getValue());
    }
  }

  private Measure convertToItMeasure(Measure measure) {
    Measure itMeasure = null;
    Metric metric = measure.getMetric();
    Double value = measure.getValue();

    if (CoreMetrics.LINES_TO_COVER.equals(metric)) {
      itMeasure = new Measure(CoreMetrics.IT_LINES_TO_COVER, value);
    } else if (CoreMetrics.UNCOVERED_LINES.equals(metric)) {
      itMeasure = new Measure(CoreMetrics.IT_UNCOVERED_LINES, value);
    } else if (CoreMetrics.COVERAGE_LINE_HITS_DATA.equals(metric)) {
      itMeasure = new Measure(CoreMetrics.IT_COVERAGE_LINE_HITS_DATA, measure.getData());
    } else if (CoreMetrics.CONDITIONS_TO_COVER.equals(metric)) {
      itMeasure = new Measure(CoreMetrics.IT_CONDITIONS_TO_COVER, value);
    } else if (CoreMetrics.UNCOVERED_CONDITIONS.equals(metric)) {
      itMeasure = new Measure(CoreMetrics.IT_UNCOVERED_CONDITIONS, value);
    } else if (CoreMetrics.COVERED_CONDITIONS_BY_LINE.equals(metric)) {
      itMeasure = new Measure(CoreMetrics.IT_COVERED_CONDITIONS_BY_LINE, measure.getData());
    } else if (CoreMetrics.CONDITIONS_BY_LINE.equals(metric)) {
      itMeasure = new Measure(CoreMetrics.IT_CONDITIONS_BY_LINE, measure.getData());
    }

    return itMeasure;
  }

  private Measure convertForOverall(Measure measure) {
    Measure itMeasure = null;

    if (CoreMetrics.LINES_TO_COVER.equals(measure.getMetric())) {
      itMeasure = new Measure(CoreMetrics.OVERALL_LINES_TO_COVER, measure.getValue());
    } else if (CoreMetrics.UNCOVERED_LINES.equals(measure.getMetric())) {
      itMeasure = new Measure(CoreMetrics.OVERALL_UNCOVERED_LINES, measure.getValue());
    } else if (CoreMetrics.COVERAGE_LINE_HITS_DATA.equals(measure.getMetric())) {
      itMeasure = new Measure(CoreMetrics.OVERALL_COVERAGE_LINE_HITS_DATA, measure.getData());
    } else if (CoreMetrics.CONDITIONS_TO_COVER.equals(measure.getMetric())) {
      itMeasure = new Measure(CoreMetrics.OVERALL_CONDITIONS_TO_COVER, measure.getValue());
    } else if (CoreMetrics.UNCOVERED_CONDITIONS.equals(measure.getMetric())) {
      itMeasure = new Measure(CoreMetrics.OVERALL_UNCOVERED_CONDITIONS, measure.getValue());
    } else if (CoreMetrics.COVERED_CONDITIONS_BY_LINE.equals(measure.getMetric())) {
      itMeasure = new Measure(CoreMetrics.OVERALL_COVERED_CONDITIONS_BY_LINE, measure.getData());
    } else if (CoreMetrics.CONDITIONS_BY_LINE.equals(measure.getMetric())) {
      itMeasure = new Measure(CoreMetrics.OVERALL_CONDITIONS_BY_LINE, measure.getData());
    }

    return itMeasure;
  }

  private boolean fileExist(SensorContext context, org.sonar.api.resources.File file) {
    return context.getResource(file) != null;
  }

  private boolean isForceZeroCoverageActivated() {
    return conf.getBoolean(FORCE_ZERO_COVERAGE_KEY);
  }
}
