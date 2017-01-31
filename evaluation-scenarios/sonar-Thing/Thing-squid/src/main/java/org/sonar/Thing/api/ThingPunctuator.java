package org.sonar.Thing.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum ThingPunctuator implements TokenType, GrammarRuleKey {

		SK34("\""),
		SK35("#"),
		SK39("'"),
		SK40("("),
		SK41(")"),
		SK44(","),
		SK45("-"),
		SK46("."),
		SK58(":"),
		SK61("="),
		SK64("@"),
		SK4247("*/"),
		SK91("["),
		SK92("\\"),
		SK93("]"),
		SK94("^"),
		SK95("_"),
		SK123("{"),
		SK125("}"),
		SK4742("/*"),
		SK4747("//"),
;

  private final String value;

  private ThingPunctuator(String word) {
    this.value = word;
  }

  public String getName() {
    return name();
  }

  public String getValue() {
    return value;
  }

  public boolean hasToBeSkippedFromAst(AstNode node) {
    return false;
  }
}
