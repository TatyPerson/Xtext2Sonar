package org.sonar.LilyPond.checks;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.utils.SonarException; //@todo: deprecated, see http://javadocs.sonarsource.org/4.5.2/apidocs/deprecated-list.html
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.squidbridge.checks.SquidCheck;

import com.google.common.io.Files;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.annotations.Tags;
import org.sonar.LilyPond.visitors.LilyPondCharsetAwareVisitor;

@Rule(
  key = "TooLongLine",
  name = "Lines of code should not be too long",
  description = "Verifies that all lines of code not be too long.",
  tags = {Tags.BRAIN_OVERLOAD},
  priority = Priority.MINOR)
@ActivatedByDefault
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.READABILITY)
@SqaleConstantRemediation("5min")
//similar Vera++ rule L004 "Line too long"
public class TooLongLineCheck extends SquidCheck<Grammar> implements LilyPondCharsetAwareVisitor {

  private static final int DEFAULT_MAXIMUM_LINE_LENHGTH = 150;
  private static final int DEFAULT_TAB_WIDTH = 8;

  @RuleProperty(
    key = "maximumLineLength",
    description = "The maximum authorized line length",
    defaultValue = "" + DEFAULT_MAXIMUM_LINE_LENHGTH)
  public int maximumLineLength = DEFAULT_MAXIMUM_LINE_LENHGTH;

  @RuleProperty(
    key = "tabWidth",
    description = "Number of spaces in a 'tab' character",
    defaultValue = "" + DEFAULT_TAB_WIDTH)
  public int tabWidth = DEFAULT_TAB_WIDTH;

  private Charset charset;

  public void setCharset(Charset charset) {
    this.charset = charset;
  }

  @Override
  public void visitFile(AstNode astNode) {
    List<String> lines;
    try {
      lines = Files.readLines(getContext().getFile(), charset);
    } catch (IOException e) {
      throw new SonarException(e); //@todo SonarException has been deprecated, see http://javadocs.sonarsource.org/4.5.2/apidocs/deprecated-list.html
    }
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      int length = line.length() + StringUtils.countMatches(line, "\t") * (tabWidth - 1);
      if (length > maximumLineLength) {
        getContext().createLineViolation(this, "Split this {0} characters long line (which is greater than {1} authorized).", i + 1, length, maximumLineLength);
      }
    }
  }

}
