package org.sonar.Sculptordsl.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum SculptordslTokenType implements TokenType, GrammarRuleKey {
  DSL_MAP_COLLECTION_TYPE,
  NOT,
  DELEGATE,
  OPPOSITE,
  REF,
  OP,
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
