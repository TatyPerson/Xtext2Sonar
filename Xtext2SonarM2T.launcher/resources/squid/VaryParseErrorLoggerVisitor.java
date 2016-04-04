package org.sonar.NAME.visitors;

import org.sonar.NAME.api.NAMEImpl;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.SquidAstVisitorContext;

import com.sonar.sslr.api.AstAndTokenVisitor;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.GenericTokenType;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Token;

public class NAMEParseErrorLoggerVisitor <GRAMMAR extends Grammar> extends SquidAstVisitor<GRAMMAR> implements AstAndTokenVisitor {

  private SquidAstVisitorContext context = null;

  public NAMEParseErrorLoggerVisitor(SquidAstVisitorContext context){
    this.context = context;
  }

  @Override
  public void init() {
    subscribeTo(NAMEImpl.CODIGO);
  }

  @Override
  public void visitNode(AstNode node) {
    AstNode identifierAst = node.getFirstChild(GenericTokenType.IDENTIFIER);
    if( identifierAst != null ) {
      NAMEImpl.LOG.warn("[{}:{}]: syntax error, skip '{}'", new Object[] {context.getFile(), node.getToken().getLine(), identifierAst.getTokenValue()});
    }
  }

  public void visitToken(Token token) {
  }
}
