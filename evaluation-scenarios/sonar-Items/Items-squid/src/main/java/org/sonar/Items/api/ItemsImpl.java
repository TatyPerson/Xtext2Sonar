package org.sonar.Items.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.Items.ItemsConfiguration;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.Items.api.ItemsTokenType.NUMBER;
import static org.sonar.Items.api.ItemsTokenType.STRING;
import static org.sonar.Items.api.ItemsTokenType.CHARACTER;
import static org.sonar.Items.api.ItemsTokenType.ID;
import static org.sonar.Items.api.ItemsTokenType.ML_COMMENT;
import static org.sonar.Items.api.ItemsTokenType.SL_COMMENT;
import static org.sonar.Items.api.ItemsTokenType.WS;
import static org.sonar.Items.api.ItemsTokenType.ANY_OTHER;

public enum ItemsImpl implements GrammarRuleKey {
	MODELGROUPFUNCTION,
	ITEMMODEL,
	MODELITEM,
	MODELGROUPITEM,
	MODELNORMALITEM,
	MODELITEMTYPE,
	MODELBINDING;

	public static final Logger LOG = LoggerFactory.getLogger("ItemsImpl");

	public static Grammar create(ItemsConfiguration conf) {
		 LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

		 generate(b);
		 b.setRootRule(ITEMMODEL);

		 return b.buildWithMemoizationOfMatchesForAllRules();
	}

	private static void generate(LexerfulGrammarBuilder b) {
		b.rule(ID).is(b.firstOf(b.isOneOfThem(com.sonar.sslr.api.GenericTokenType.IDENTIFIER, com.sonar.sslr.api.GenericTokenType.IDENTIFIER),"|", "."));
		b.rule(STRING).is(b.isOneOfThem(STRING, STRING));
		b.rule(ML_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(SL_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(WS).is(b.isOneOfThem(STRING, STRING));
		b.rule(ANY_OTHER).is(b.isOneOfThem(STRING, STRING));
		b.rule(MODELGROUPFUNCTION).is(
			b.firstOf(b.isOneOfThem(ItemsKeyword.	AND_64951, ItemsKeyword.	AND_64951),
			b.isOneOfThem(ItemsKeyword.	OR_2531, ItemsKeyword.	OR_2531),
			b.isOneOfThem(ItemsKeyword.	NAND_2388649, ItemsKeyword.	NAND_2388649),
			b.isOneOfThem(ItemsKeyword.	NOR_77489, ItemsKeyword.	NOR_77489),
			b.isOneOfThem(ItemsKeyword.	AVG_65202, ItemsKeyword.	AVG_65202),
			b.isOneOfThem(ItemsKeyword.	SUM_82475, ItemsKeyword.	SUM_82475),
			b.isOneOfThem(ItemsKeyword.	MAX_76100, ItemsKeyword.	MAX_76100),
			b.isOneOfThem(ItemsKeyword.	MIN_76338, ItemsKeyword.	MIN_76338),
			b.isOneOfThem(ItemsKeyword.	COUNT_64313583, ItemsKeyword.	COUNT_64313583)
		));

		b.rule(ITEMMODEL).is(
		b.zeroOrMore(MODELITEM)
		);
		


		b.rule(MODELITEM).is(
		b.sequence(		b.firstOf(
		MODELNORMALITEM,
		MODELGROUPITEM),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(ItemsPunctuator.	SK60, ItemsPunctuator.	SK60),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING),
		b.isOneOfThem(ItemsPunctuator.	SK62, ItemsPunctuator.	SK62)),
		b.optional(
		b.isOneOfThem(ItemsPunctuator.	SK40, ItemsPunctuator.	SK40),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.zeroOrMore(
		b.isOneOfThem(ItemsPunctuator.	SK44, ItemsPunctuator.	SK44),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.isOneOfThem(ItemsPunctuator.	SK41, ItemsPunctuator.	SK41)),
		b.optional(
		b.isOneOfThem(ItemsPunctuator.	SK91, ItemsPunctuator.	SK91),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING),
		b.zeroOrMore(
		b.isOneOfThem(ItemsPunctuator.	SK44, ItemsPunctuator.	SK44),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING),
		b.isOneOfThem(ItemsPunctuator.	SK93, ItemsPunctuator.	SK93)),
		b.optional(
		b.isOneOfThem(ItemsPunctuator.	SK123, ItemsPunctuator.	SK123),
		MODELBINDING,
		b.zeroOrMore(
		b.isOneOfThem(ItemsPunctuator.	SK44, ItemsPunctuator.	SK44),
		MODELBINDING),
		b.isOneOfThem(ItemsPunctuator.	SK125, ItemsPunctuator.	SK125))
		));
		


		b.rule(MODELGROUPITEM).is(
		b.sequence(		b.isOneOfThem(ItemsKeyword.	GROUP_69076575, ItemsKeyword.	GROUP_69076575),
		b.optional(
		b.isOneOfThem(ItemsPunctuator.	SK58, ItemsPunctuator.	SK58),
		MODELITEMTYPE,
		b.isOneOfThem(ItemsPunctuator.	SK58, ItemsPunctuator.	SK58),
		MODELGROUPFUNCTION,
		b.isOneOfThem(ItemsPunctuator.	SK40, ItemsPunctuator.	SK40),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING),
		b.zeroOrMore(
		b.isOneOfThem(ItemsPunctuator.	SK44, ItemsPunctuator.	SK44),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING),
		b.isOneOfThem(ItemsPunctuator.	SK41, ItemsPunctuator.	SK41))
		));
		

		b.rule(MODELNORMALITEM).is(MODELITEMTYPE);
		


		b.rule(MODELITEMTYPE).is(
		b.firstOf(
		b.isOneOfThem(ItemsKeyword.	SWITCH_1805606060, ItemsKeyword.	SWITCH_1805606060),
		b.isOneOfThem(ItemsKeyword.	ROLLERSHUTTER_666400093, ItemsKeyword.	ROLLERSHUTTER_666400093),
		b.isOneOfThem(ItemsKeyword.	NUMBER_1950496919, ItemsKeyword.	NUMBER_1950496919),
		b.isOneOfThem(ItemsKeyword.	STRING_1808118735, ItemsKeyword.	STRING_1808118735),
		b.isOneOfThem(ItemsKeyword.	DIMMER_2047107186, ItemsKeyword.	DIMMER_2047107186),
		b.isOneOfThem(ItemsKeyword.	CONTACT_1678787584, ItemsKeyword.	CONTACT_1678787584),
		b.isOneOfThem(ItemsKeyword.	DATETIME_1857393595, ItemsKeyword.	DATETIME_1857393595),
		b.isOneOfThem(ItemsKeyword.	COLOR_65290051, ItemsKeyword.	COLOR_65290051),
		b.isOneOfThem(ItemsKeyword.	PLAYER_1901885695, ItemsKeyword.	PLAYER_1901885695),
		b.isOneOfThem(ItemsKeyword.	LOCATION_1965687765, ItemsKeyword.	LOCATION_1965687765),
		b.isOneOfThem(ItemsKeyword.	CALL_2092670, ItemsKeyword.	CALL_2092670),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		)).skipIfOneChild();
		
		


		b.rule(MODELBINDING).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(ItemsPunctuator.	SK61, ItemsPunctuator.	SK61),
		STRING
		));
		

	}
}
