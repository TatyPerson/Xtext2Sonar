package org.sonar.LilyPond.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum LilyPondTokenType implements TokenType, GrammarRuleKey {
  INT,
  ID,
  NL_NOINDENT,
  WS,
  SL_COMMENT,
  ML_COMMENT,
  SCHEME_SL_COMMENT,
  SCHEME_ML_COMMENT,
  ANY_OTHER,
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
