package org.sonar.Sitemap.visitors;

import static com.sonar.sslr.api.GenericTokenType.EOF;

import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.measures.MetricDef;

import com.sonar.sslr.api.AstAndTokenVisitor;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Token;

/**
 * Visitor that computes the number of lines of code of a file.
 */
public class SitemapLinesOfCodeVisitor<GRAMMAR extends Grammar> extends SquidAstVisitor<GRAMMAR> implements AstAndTokenVisitor {

  private final MetricDef metric;
  private int lastTokenLine;

  public SitemapLinesOfCodeVisitor(MetricDef metric) {
    this.metric = metric;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitFile(AstNode node) {
    lastTokenLine = -1;
  }

  /**
   * {@inheritDoc}
   */
  public void visitToken(Token token) {
    if (token.getType() != EOF) {
      /* Handle all the lines of the token */
      String[] tokenLines = token.getValue().split("\n", -1);

      int firstLineAlreadyCounted = lastTokenLine == token.getLine() ? 1 : 0;
      getContext().peekSourceCode().add(metric, tokenLines.length - firstLineAlreadyCounted);

      lastTokenLine = token.getLine() + tokenLines.length - 1;
    }
  }

}
