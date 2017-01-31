package org.sonar.Sculptordsl.plugin.coverage;

import java.io.File;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang.StringUtils;
import org.codehaus.staxmate.in.SMHierarchicCursor;
import org.codehaus.staxmate.in.SMInputCursor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.measures.CoverageMeasuresBuilder;
import org.sonar.api.resources.Project;
import org.sonar.api.utils.StaxParser;
import org.sonar.Sculptordsl.plugin.utils.SculptordslUtils;

/**
 * {@inheritDoc}
 */
public class CoberturaParser implements CoverageParser {
  /**
   * {@inheritDoc}
   */
  public void processReport(final Project project, final SensorContext context, File report, final Map<String, CoverageMeasuresBuilder> coverageData)
      throws XMLStreamException
  {
    SculptordslUtils.LOG.info("Parsing 'Cobertura' format");

    StaxParser parser = new StaxParser(new StaxParser.XmlStreamHandler() {
      /**
       * {@inheritDoc}
       */
      public void stream(SMHierarchicCursor rootCursor) throws XMLStreamException {
        rootCursor.advance();
        collectPackageMeasures(rootCursor.descendantElementCursor("package"), coverageData);
      }
    });
    parser.parse(report);
  }

  private void collectPackageMeasures(SMInputCursor pack, Map<String, CoverageMeasuresBuilder> coverageData)
      throws XMLStreamException
  {
    while (pack.getNext() != null) {
      collectFileMeasures(pack.descendantElementCursor("class"), coverageData);
    }
  }

  private void collectFileMeasures(SMInputCursor clazz, Map<String, CoverageMeasuresBuilder> coverageData)
      throws XMLStreamException
  {
    while (clazz.getNext() != null) {
      String normalPath = SculptordslUtils.normalizePath(clazz.getAttrValue("filename"));
      if(normalPath != null){
        CoverageMeasuresBuilder builder = coverageData.get(normalPath);
        if (builder == null) {
          builder = CoverageMeasuresBuilder.create();
          coverageData.put(normalPath, builder);
        }
        collectFileData(clazz, builder);
      }
    }
  }

  private void collectFileData(SMInputCursor clazz, CoverageMeasuresBuilder builder) throws XMLStreamException {
    SMInputCursor line = clazz.childElementCursor("lines").advance().childElementCursor("line");
    while (line.getNext() != null) {
      int lineId = Integer.parseInt(line.getAttrValue("number"));
      long noHits = Long.parseLong(line.getAttrValue("hits"));
      if(noHits > Integer.MAX_VALUE){
        SculptordslUtils.LOG.warn("Truncating the actual number of hits ({}) to the maximum number supported by Sonar ({})",
                          noHits, Integer.MAX_VALUE);
        noHits = Integer.MAX_VALUE;
      }
      builder.setHits(lineId, (int)noHits);

      String isBranch = line.getAttrValue("branch");
      String text = line.getAttrValue("condition-coverage");
      if (StringUtils.equals(isBranch, "true") && StringUtils.isNotBlank(text)) {
        String[] conditions = StringUtils.split(StringUtils.substringBetween(text, "(", ")"), "/");
        builder.setConditions(lineId, Integer.parseInt(conditions[1]), Integer.parseInt(conditions[0]));
      }
    }
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
