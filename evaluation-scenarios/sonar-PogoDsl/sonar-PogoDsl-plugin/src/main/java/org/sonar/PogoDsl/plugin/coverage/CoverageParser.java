package org.sonar.PogoDsl.plugin.coverage;

import java.io.File;
import java.util.Map;

import javax.xml.stream.XMLStreamException;
import org.sonar.api.batch.SensorContext;

import org.sonar.api.measures.CoverageMeasuresBuilder;
import org.sonar.api.resources.Project;

/**
 * The interface a coverage report parser has to implement in order to be used
 * by CxxCoverageSensor
 */
public interface CoverageParser {
  /**
   * Parses the given report and stores the results in the according builder
   * @param xmlFile The report to parse
   * @param coverageData A Map mapping source file names to coverage measures. Has
   *        to be used to store the results into.
   */
  void processReport(final Project project, final SensorContext context, File report, Map<String, CoverageMeasuresBuilder> coverageData)
      throws XMLStreamException;
}
