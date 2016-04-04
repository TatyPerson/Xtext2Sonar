package org.sonar.NAME.visitors;

import org.sonar.NAME.parser.NAMEParser;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.SquidAstVisitorContext;

import com.sonar.sslr.api.AstAndTokenVisitor;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Token;

public class NAMEFileVisitor<GRAMMAR extends Grammar> extends SquidAstVisitor<GRAMMAR>
  implements AstAndTokenVisitor {

  private SquidAstVisitorContext context = null;

  public NAMEFileVisitor(SquidAstVisitorContext context){
    this.context = context;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitFile(AstNode node) {
    //NAMEParser.finishedParsing(context.getFile());
  }

  /**
   * {@inheritDoc}
   */
  public void visitToken(Token token) {
  }
}
