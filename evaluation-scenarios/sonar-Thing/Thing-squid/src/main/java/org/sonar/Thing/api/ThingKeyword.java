package org.sonar.Thing.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum ThingKeyword implements TokenType, GrammarRuleKey {
		
		STRING_1808118735("String"),
		DATETIME_1857393595("DateTime"),
		THING_80778446("Thing"),
		SWITCH_1805606060("Switch"),
		B_98("b"),
		F_102("f"),
		N_110("n"),
		TYPE_2622298("Type"),
		R_114("r"),
		T_116("t"),
		U_117("u"),
		DIMMER_2047107186("Dimmer"),
		THINGS__318678901("Things:"),
		TRIGGER_604761496("Trigger"),
		COLOR_65290051("Color"),
		STATE_80204913("State"),
		TRUE_3569038("true"),
		BRIDGE_1998032809("Bridge"),
		NUMBER_1950496919("Number"),
		CHANNELS__829262262("Channels:"),
		ROLLERSHUTTER_666400093("Rollershutter"),
		FALSE_97196323("false"),
		CONTACT_1678787584("Contact");
;

  private final String value;

  private ThingKeyword(String value) {
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
    ThingKeyword[] keywordsEnum = ThingKeyword.values();
    String[] keywords = new String[keywordsEnum.length];
    for (int i = 0; i < keywords.length; i++) {
      keywords[i] = keywordsEnum[i].getValue();
    }
    return keywords;
  }
}
