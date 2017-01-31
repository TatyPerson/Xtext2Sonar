package org.sonar.LilyPond.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum LilyPondPunctuator implements TokenType, GrammarRuleKey {

		SK33("!"),
		SK34("\""),
		SK3335("!#"),
		SK35("#"),
		SK36("$"),
		SK37("%"),
		SK39("'"),
		SK40("("),
		SK41(")"),
		SK43("+"),
		SK44(","),
		SK45("-"),
		SK58(":"),
		SK60("<"),
		SK61("="),
		SK62(">"),
		SK63("?"),
		SK64("@"),
		SK91("["),
		SK92("\\"),
		SK93("]"),
		SK3533("#!"),
		SK96("`"),
		SK3558("#:"),
		SK123("{"),
		SK125("}"),
		SK126("~"),
		SK3592("#\""),
		SK161("¡"),
		SK35123("#{"),
		SK35125("#}"),
		SK37123("%{"),
		SK37125("%}"),
		SK5959(";;"),
		SK6060("<<"),
		SK9292("\\"),
		SK6262(">>"),
		SK65500("ￜ"),
;

  private final String value;

  private LilyPondPunctuator(String word) {
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
