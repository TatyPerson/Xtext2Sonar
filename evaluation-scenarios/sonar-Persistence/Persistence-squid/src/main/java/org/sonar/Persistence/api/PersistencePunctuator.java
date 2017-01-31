package org.sonar.Persistence.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum PersistencePunctuator implements TokenType, GrammarRuleKey {

		SK37("%"),
		SK42("*"),
		SK44(","),
		SK46("."),
		SK4562("->"),
		SK58(":"),
		SK123("{"),
		SK59(";"),
		SK61("="),
		SK125("}"),
		SK62(">");
;

  private final String value;

  private PersistencePunctuator(String word) {
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
