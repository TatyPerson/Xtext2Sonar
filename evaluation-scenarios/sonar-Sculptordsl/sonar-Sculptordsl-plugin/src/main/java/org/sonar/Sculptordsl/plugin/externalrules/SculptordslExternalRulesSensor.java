package org.sonar.Sculptordsl.plugin.externalrules;

import java.io.File;

import javax.xml.stream.XMLStreamException;

import org.codehaus.staxmate.in.SMHierarchicCursor;
import org.codehaus.staxmate.in.SMInputCursor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.Project;
import org.sonar.api.utils.StaxParser;
import org.sonar.Sculptordsl.plugin.utils.SculptordslMetrics;
import org.sonar.Sculptordsl.plugin.utils.SculptordslReportSensor;
import org.sonar.api.batch.bootstrap.ProjectReactor;
import org.sonar.Sculptordsl.plugin.utils.SculptordslUtils;

/**
 * Custom Rule Import, all static analysis are supported.
 *
 */
public class SculptordslExternalRulesSensor extends SculptordslReportSensor {

  public static final String REPORT_PATH_KEY = "sonar.sculptordsl.other.reportPath";
  private static final String DEFAULT_REPORT_PATH = "externalrules-reports/externalrules-result-*.xml";
  private final RulesProfile profile;

  /**
   * {@inheritDoc}
   */
  public SculptordslExternalRulesSensor(ResourcePerspectives perspectives, Settings conf, FileSystem fs, RulesProfile profile, ProjectReactor reactor) {
    super(perspectives, conf, fs, reactor, SculptordslMetrics.EXTERNAL);
    this.profile = profile;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean shouldExecuteOnProject(Project project) {
    return super.shouldExecuteOnProject(project)
      && !profile.getActiveRulesByRepository(SculptordslExternalRuleRepository.KEY).isEmpty();
  }

  @Override
  protected String reportPathKey() {
    return REPORT_PATH_KEY;
  }

  @Override
  protected String defaultReportPath() {
    return DEFAULT_REPORT_PATH;
  }

  @Override
  protected void processReport(final Project project, final SensorContext context, File report) throws javax.xml.stream.XMLStreamException
  {
    SculptordslUtils.LOG.info("Parsing 'other' format");
    
    StaxParser parser = new StaxParser(new StaxParser.XmlStreamHandler() {
 
      /**
       * {@inheritDoc}
       */
      public void stream(SMHierarchicCursor rootCursor) throws XMLStreamException {
        rootCursor.advance();

        SMInputCursor errorCursor = rootCursor.childElementCursor("error");
        while (errorCursor.getNext() != null) {
          String file = errorCursor.getAttrValue("file");
          String line = errorCursor.getAttrValue("line");
          String id = errorCursor.getAttrValue("id");
          String msg = errorCursor.getAttrValue("msg");

          saveUniqueViolation(project, context, SculptordslExternalRuleRepository.KEY, file, line, id, msg);
        }
      }
    });

    parser.parse(report);
  }
}
