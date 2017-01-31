package org.sonar.Items.visitors;

import org.sonar.Items.parser.ItemsParser;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.SquidAstVisitorContext;

import com.sonar.sslr.api.AstAndTokenVisitor;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Token;

public class ItemsFileVisitor<GRAMMAR extends Grammar> extends SquidAstVisitor<GRAMMAR>
  implements AstAndTokenVisitor {

  private SquidAstVisitorContext context = null;

  public ItemsFileVisitor(SquidAstVisitorContext context){
    this.context = context;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void visitFile(AstNode node) {
    //ItemsParser.finishedParsing(context.getFile());
  }

  /**
   * {@inheritDoc}
   */
  public void visitToken(Token token) {
  }
}
