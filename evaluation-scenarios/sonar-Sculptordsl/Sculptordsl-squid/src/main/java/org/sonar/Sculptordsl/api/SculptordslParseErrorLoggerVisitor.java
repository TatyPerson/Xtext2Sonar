package org.sonar.Sculptordsl.api;

import org.sonar.Sculptordsl.api.SculptordslImpl;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.SquidAstVisitorContext;

import com.sonar.sslr.api.AstAndTokenVisitor;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.GenericTokenType;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Token;

public class SculptordslParseErrorLoggerVisitor <GRAMMAR extends Grammar> extends SquidAstVisitor<GRAMMAR> implements AstAndTokenVisitor {

  private SquidAstVisitorContext context = null;

  public SculptordslParseErrorLoggerVisitor(SquidAstVisitorContext context){
    this.context = context;
  }

  @Override
  public void init() {
    subscribeTo(SculptordslImpl.DSLMODEL);
  }

  @Override
  public void visitNode(AstNode node) {
    AstNode identifierAst = node.getFirstChild(GenericTokenType.IDENTIFIER);
    if( identifierAst != null ) {
      SculptordslImpl.LOG.warn("[{}:{}]: syntax error, skip '{}'", new Object[] {context.getFile(), node.getToken().getLine(), identifierAst.getTokenValue()});
    }
  }

  public void visitToken(Token token) {
  }
}
