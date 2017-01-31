package org.sonar.Items.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum ItemsKeyword implements TokenType, GrammarRuleKey {
		
		STRING_1808118735("String"),
		NAND_2388649("NAND"),
		DATETIME_1857393595("DateTime"),
		PLAYER_1901885695("Player"),
		COLOR_65290051("Color"),
		SUM_82475("SUM"),
		NOR_77489("NOR"),
		AVG_65202("AVG"),
		MIN_76338("MIN"),
		SWITCH_1805606060("Switch"),
		AND_64951("AND"),
		COUNT_64313583("COUNT"),
		GROUP_69076575("Group"),
		MAX_76100("MAX"),
		NUMBER_1950496919("Number"),
		CALL_2092670("Call"),
		B_98("b"),
		OR_2531("OR"),
		ROLLERSHUTTER_666400093("Rollershutter"),
		F_102("f"),
		N_110("n"),
		CONTACT_1678787584("Contact"),
		R_114("r"),
		T_116("t"),
		U_117("u"),
		DIMMER_2047107186("Dimmer"),
		LOCATION_1965687765("Location");
;

  private final String value;

  private ItemsKeyword(String value) {
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
    ItemsKeyword[] keywordsEnum = ItemsKeyword.values();
    String[] keywords = new String[keywordsEnum.length];
    for (int i = 0; i < keywords.length; i++) {
      keywords[i] = keywordsEnum[i].getValue();
    }
    return keywords;
  }
}
