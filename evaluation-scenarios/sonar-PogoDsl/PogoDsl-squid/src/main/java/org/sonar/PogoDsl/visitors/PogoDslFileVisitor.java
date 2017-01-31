package org.sonar.PogoDsl.visitors;

import org.sonar.PogoDsl.parser.PogoDslParser;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.SquidAstVisitorContext;

import com.sonar.sslr.api.AstAndTokenVisitor;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Token;

public class PogoDslFileVisitor<GRAMMAR extends Grammar> extends SquidAstVisitor<GRAMMAR>
  implements AstAndTokenVisitor {

  private SquidAstVisitorContext context = null;

  public PogoDslFileVisitor(SquidAstVisitorContext context){
    this.context = context;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitFile(AstNode node) {
    //PogoDslParser.finishedParsing(context.getFile());
  }

  /**
   * {@inheritDoc}
   */
  public void visitToken(Token token) {
  }
}
