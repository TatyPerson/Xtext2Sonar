package org.sonar.Sculptordsl.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum SculptordslPunctuator implements TokenType, GrammarRuleKey {

		SK33("!"),
		SK40("("),
		SK41(")"),
		SK42("*"),
		SK44(","),
		SK45("-"),
		SK46("."),
		SK47("/"),
		SK58(":"),
		SK59(";"),
		SK60("<"),
		SK61("="),
		SK62(">"),
		SK64("@"),
		SK123("{"),
		SK125("}"),
		SK604562("<->"),
		SK6162("=>"),
;

  private final String value;

  private SculptordslPunctuator(String word) {
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
