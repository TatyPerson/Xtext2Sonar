package org.sonar.VaryGrammar.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum VaryGrammarPunctuator implements TokenType, GrammarRuleKey {

		SK33("!"),
		SK34("\""),
		SK35("#"),
		SK37("%"),
		SK38("&"),
		SK39("'"),
		SK40("("),
		SK41(")"),
		SK42("*"),
		SK43("+"),
		SK44(","),
		SK45("-"),
		SK46("."),
		SK47("/"),
		SK58(":"),
		SK3361("!="),
		SK60("<"),
		SK61("="),
		SK62(">"),
		SK63("?"),
		SK64("@"),
		SK91("["),
		SK92("\\"),
		SK93("]"),
		SK95("_"),
		SK123("{"),
		SK125("}"),
		SK5832(": "),
		SK4247("*/"),
		SK6045("<-"),
		SK6061("<="),
		SK9391("]["),
		SK6261(">="),
		SK4646(".."),
		SK4742("/*"),
		SK4747("//");
;

  private final String value;

  private VaryGrammarPunctuator(String word) {
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
