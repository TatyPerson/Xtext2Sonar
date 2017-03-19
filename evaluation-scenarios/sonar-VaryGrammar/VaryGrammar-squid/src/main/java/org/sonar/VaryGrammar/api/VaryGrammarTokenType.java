package org.sonar.VaryGrammar.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum VaryGrammarTokenType implements TokenType, GrammarRuleKey {
  SL_COMMENT,
  NOMBRE_FUN,
  CAD,
  CAR,
  COMENT,
  CHARACTER,
  NUMBER,
  STRING;	

  // TODO in fact it's STRING_LITERAL, but we need to keep compatibility of XPath expressions

  public String getName() {
    return name();
  }

  public String getValue() {
    return name();
  }

  public boolean hasToBeSkippedFromAst(AstNode node) {
    return false;
  }
}
