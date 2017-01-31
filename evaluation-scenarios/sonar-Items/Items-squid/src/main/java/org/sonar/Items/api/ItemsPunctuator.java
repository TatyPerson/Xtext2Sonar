package org.sonar.Items.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum ItemsPunctuator implements TokenType, GrammarRuleKey {

		SK34("\""),
		SK39("'"),
		SK40("("),
		SK41(")"),
		SK44(","),
		SK45("-"),
		SK58(":"),
		SK60("<"),
		SK61("="),
		SK62(">"),
		SK4247("*/"),
		SK91("["),
		SK4742("/*"),
		SK92("\\"),
		SK93("]"),
		SK94("^"),
		SK95("_"),
		SK4747("//"),
		SK123("{"),
		SK125("}"),
;

  private final String value;

  private ItemsPunctuator(String word) {
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
