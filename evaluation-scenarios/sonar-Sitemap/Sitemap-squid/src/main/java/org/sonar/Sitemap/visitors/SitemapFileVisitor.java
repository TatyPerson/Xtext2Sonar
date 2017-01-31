package org.sonar.Sitemap.visitors;

import org.sonar.Sitemap.parser.SitemapParser;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.SquidAstVisitorContext;

import com.sonar.sslr.api.AstAndTokenVisitor;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Token;

public class SitemapFileVisitor<GRAMMAR extends Grammar> extends SquidAstVisitor<GRAMMAR>
  implements AstAndTokenVisitor {

  private SquidAstVisitorContext context = null;

  public SitemapFileVisitor(SquidAstVisitorContext context){
    this.context = context;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitFile(AstNode node) {
    //SitemapParser.finishedParsing(context.getFile());
  }

  /**
   * {@inheritDoc}
   */
  public void visitToken(Token token) {
  }
}
