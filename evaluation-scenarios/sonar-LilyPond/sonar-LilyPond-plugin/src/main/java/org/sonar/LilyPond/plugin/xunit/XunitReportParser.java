package org.sonar.LilyPond.plugin.xunit;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang.StringUtils;
import org.codehaus.staxmate.in.ElementFilter;
import org.codehaus.staxmate.in.SMHierarchicCursor;
import org.codehaus.staxmate.in.SMInputCursor;
import org.sonar.api.utils.ParsingUtils;
import org.sonar.api.utils.StaxParser.XmlStreamHandler;
import org.sonar.LilyPond.plugin.utils.EmptyReportException;

/**
 * {@inheritDoc}
 */
public class XunitReportParser implements XmlStreamHandler {
  private List<TestCase> testCases = new LinkedList<TestCase>();

  /**
   * Returns successfully parsed testcases.
   */
  public List<TestCase> getTestCases() {
    return testCases;
  }

  /**
   * {@inheritDoc}
   */
  public void stream(SMHierarchicCursor rootCursor) throws XMLStreamException {
    SMInputCursor testSuiteCursor = rootCursor.constructDescendantCursor(new ElementFilter("testsuite"));
    try{
      testSuiteCursor.getNext();
    }
    catch(com.ctc.wstx.exc.WstxEOFException eofExc){
      throw new EmptyReportException();
    }

    do{
      parseTestSuiteTag(testSuiteCursor);
    }while (testSuiteCursor.getNext() != null);
  }

  public void parseTestSuiteTag(SMInputCursor testSuiteCursor)
    throws XMLStreamException
  {
    String testSuiteName = testSuiteCursor.getAttrValue("name");
    String testSuiteFName = testSuiteCursor.getAttrValue("filename");

    SMInputCursor childCursor = testSuiteCursor.childElementCursor();
    while (childCursor.getNext() != null) {
      String elementName = childCursor.getLocalName();
      if ("testsuite".equals(elementName)) {
        parseTestSuiteTag(childCursor);
      } else if ("testcase".equals(elementName)) {
        testCases.add(parseTestCaseTag(childCursor, testSuiteName, testSuiteFName));
      }
    }
  }

  private TestCase parseTestCaseTag(SMInputCursor testCaseCursor, String tsName, String tsFilename)
      throws XMLStreamException
  {
    String classname = testCaseCursor.getAttrValue("classname");
    String tcFilename = testCaseCursor.getAttrValue("filename");
    String name = parseTestCaseName(testCaseCursor);
    Double time = parseTime(testCaseCursor);
    String status = "ok";
    String stack = "";
    String msg = "";

    // Googletest-reports mark the skipped tests with status="notrun"
    String statusattr = testCaseCursor.getAttrValue("status");
    if ("notrun".equals(statusattr)) {
      status = "skipped";
    } else {
      SMInputCursor childCursor = testCaseCursor.childElementCursor();
      if (childCursor.getNext() != null) {
        String elementName = childCursor.getLocalName();
        if ("skipped".equals(elementName)) {
          status = "skipped";
        } else if ("failure".equals(elementName)) {
          status = "failure";
          msg = childCursor.getAttrValue("message");
          stack = childCursor.collectDescendantText();
        } else if ("error".equals(elementName)) {
          status = "error";
          msg = childCursor.getAttrValue("message");
          stack = childCursor.collectDescendantText();
        }
      }
    }

    return new TestCase(name, time.intValue(), status, stack, msg, classname, tcFilename, tsName, tsFilename);
  }

  private double parseTime(SMInputCursor testCaseCursor)
      throws XMLStreamException
  {
    double time = 0.0;
    try {
      String sTime = testCaseCursor.getAttrValue("time");
      if (!StringUtils.isEmpty(sTime)) {
        Double tmp = ParsingUtils.parseNumber(sTime, Locale.ENGLISH);
        if (!Double.isNaN(tmp)) {
          time = ParsingUtils.scaleValue(tmp * 1000, 3);
        }
      }
    } catch (ParseException e) {
      throw new XMLStreamException(e);
    }

    return time;
  }

  private String parseTestCaseName(SMInputCursor testCaseCursor) throws XMLStreamException {
    String name = testCaseCursor.getAttrValue("name");
    String classname = testCaseCursor.getAttrValue("classname");
    if (classname != null) {
      name = classname + "/" + name;
    }
    return name;
  }
}
