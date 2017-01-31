package org.sonar.Sitemap.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum SitemapPunctuator implements TokenType, GrammarRuleKey {

		SK43("+"),
		SK44(","),
		SK45("-"),
		SK46("."),
		SK60("<"),
		SK3361("!="),
		SK61("="),
		SK62(">"),
		SK93("]"),
		SK94("^"),
		SK95("_"),
		SK123("{"),
		SK125("}"),
		SK6061("<="),
		SK6161("=="),
		SK6261(">="),
;

  private final String value;

  private SitemapPunctuator(String word) {
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
