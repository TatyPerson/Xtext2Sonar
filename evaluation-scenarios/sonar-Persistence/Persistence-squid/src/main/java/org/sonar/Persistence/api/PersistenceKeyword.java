package org.sonar.Persistence.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum PersistenceKeyword implements TokenType, GrammarRuleKey {
		
		DEFAULT_1544803905("default"),
		FILTERS_810105819("Filters"),
		ITEMS_70973344("Items"),
		D_100("d"),
		H_104("h"),
		M_109("m"),
		FILTER_1274492040("filter"),
		S_115("s"),
		STRATEGIES_2080784241("Strategies"),
		STRATEGY_1787798387("strategy"),
;

  private final String value;

  private PersistenceKeyword(String value) {
    this.value = value;
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

  public static String[] keywordValues() {
    PersistenceKeyword[] keywordsEnum = PersistenceKeyword.values();
    String[] keywords = new String[keywordsEnum.length];
    for (int i = 0; i < keywords.length; i++) {
      keywords[i] = keywordsEnum[i].getValue();
    }
    return keywords;
  }
}
