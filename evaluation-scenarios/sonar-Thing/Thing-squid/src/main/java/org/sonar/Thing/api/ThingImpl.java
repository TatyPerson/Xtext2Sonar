package org.sonar.Thing.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.Thing.ThingConfiguration;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.Thing.api.ThingTokenType.NUMBER;
import static org.sonar.Thing.api.ThingTokenType.STRING;
import static org.sonar.Thing.api.ThingTokenType.CHARACTER;
import static org.sonar.Thing.api.ThingTokenType.ID;
import static org.sonar.Thing.api.ThingTokenType.ML_COMMENT;
import static org.sonar.Thing.api.ThingTokenType.SL_COMMENT;
import static org.sonar.Thing.api.ThingTokenType.WS;
import static org.sonar.Thing.api.ThingTokenType.ANY_OTHER;

public enum ThingImpl implements GrammarRuleKey {
	THINGMODEL,
	MODELPROPERTYCONTAINER,
	MODELBRIDGE,
	MODELTHING,
	MODELCHANNEL,
	MODELITEMTYPE,
	MODELPROPERTY,
	CHANNEL_ID,
	UID,
	UID_SEGMENT,
	VALUETYPE,
	BOOLEAN;

	public static final Logger LOG = LoggerFactory.getLogger("ThingImpl");

	public static Grammar create(ThingConfiguration conf) {
		 LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

		 generate(b);
		 b.setRootRule(THINGMODEL);

		 return b.buildWithMemoizationOfMatchesForAllRules();
	}

	private static void generate(LexerfulGrammarBuilder b) {
		b.rule(ID).is(b.firstOf(b.isOneOfThem(com.sonar.sslr.api.GenericTokenType.IDENTIFIER, com.sonar.sslr.api.GenericTokenType.IDENTIFIER),"|", "."));
		b.rule(STRING).is(b.isOneOfThem(STRING, STRING));
		b.rule(ML_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(SL_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(WS).is(b.isOneOfThem(STRING, STRING));
		b.rule(ANY_OTHER).is(b.isOneOfThem(STRING, STRING));

		b.rule(THINGMODEL).is(
		b.firstOf(
		b.zeroOrMore(MODELTHING),
		b.zeroOrMore(MODELBRIDGE)
		)).skipIfOneChild();
		
		


		b.rule(MODELPROPERTYCONTAINER).is(
		b.firstOf(
		MODELTHING,
		MODELBRIDGE,
		MODELCHANNEL
		)).skipIfOneChild();
		
		


		b.rule(MODELBRIDGE).is(
		b.sequence(		b.isOneOfThem(ThingKeyword.	BRIDGE_1998032809, ThingKeyword.	BRIDGE_1998032809),
		b.firstOf(
		UID,
		UID_SEGMENT,
		UID_SEGMENT),
		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK64, ThingPunctuator.	SK64),
		STRING),
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK91, ThingPunctuator.	SK91),
		b.optional(
		MODELPROPERTY),
		b.zeroOrMore(
		b.isOneOfThem(ThingPunctuator.	SK44, ThingPunctuator.	SK44),
		MODELPROPERTY),
		b.isOneOfThem(ThingPunctuator.	SK93, ThingPunctuator.	SK93)),
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK123, ThingPunctuator.	SK123),
		b.isOneOfThem(ThingKeyword.	THINGS__318678901, ThingKeyword.	THINGS__318678901),
		b.firstOf(
		b.zeroOrMore(MODELTHING),
		b.zeroOrMore(MODELBRIDGE)),
		b.isOneOfThem(ThingKeyword.	CHANNELS__829262262, ThingKeyword.	CHANNELS__829262262),
		b.zeroOrMore(MODELCHANNEL),
		b.isOneOfThem(ThingPunctuator.	SK125, ThingPunctuator.	SK125))
		));
		


		b.rule(MODELTHING).is(
		b.sequence(		b.isOneOfThem(ThingKeyword.	THING_80778446, ThingKeyword.	THING_80778446),
		b.firstOf(
		UID,
		UID_SEGMENT,
		UID_SEGMENT),
		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK40, ThingPunctuator.	SK40),
		UID,
		b.isOneOfThem(ThingPunctuator.	SK41, ThingPunctuator.	SK41)),
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK64, ThingPunctuator.	SK64),
		STRING),
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK91, ThingPunctuator.	SK91),
		b.optional(
		MODELPROPERTY),
		b.zeroOrMore(
		b.isOneOfThem(ThingPunctuator.	SK44, ThingPunctuator.	SK44),
		MODELPROPERTY),
		b.isOneOfThem(ThingPunctuator.	SK93, ThingPunctuator.	SK93)),
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK123, ThingPunctuator.	SK123),
		b.isOneOfThem(ThingKeyword.	CHANNELS__829262262, ThingKeyword.	CHANNELS__829262262),
		b.zeroOrMore(MODELCHANNEL),
		b.isOneOfThem(ThingPunctuator.	SK125, ThingPunctuator.	SK125))
		));
		


		b.rule(MODELCHANNEL).is(
		b.sequence(		b.firstOf(
		b.isOneOfThem(ThingKeyword.	STATE_80204913, ThingKeyword.	STATE_80204913),
		b.isOneOfThem(ThingKeyword.	TRIGGER_604761496, ThingKeyword.	TRIGGER_604761496),
		MODELITEMTYPE,
		b.isOneOfThem(ThingKeyword.	TYPE_2622298, ThingKeyword.	TYPE_2622298),
		UID_SEGMENT),
		b.isOneOfThem(ThingPunctuator.	SK58, ThingPunctuator.	SK58),
		CHANNEL_ID,
		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK91, ThingPunctuator.	SK91),
		b.optional(
		MODELPROPERTY),
		b.zeroOrMore(
		b.isOneOfThem(ThingPunctuator.	SK44, ThingPunctuator.	SK44),
		MODELPROPERTY),
		b.isOneOfThem(ThingPunctuator.	SK93, ThingPunctuator.	SK93))
		));
		


		b.rule(MODELITEMTYPE).is(
		b.firstOf(
		b.isOneOfThem(ThingKeyword.	SWITCH_1805606060, ThingKeyword.	SWITCH_1805606060),
		b.isOneOfThem(ThingKeyword.	ROLLERSHUTTER_666400093, ThingKeyword.	ROLLERSHUTTER_666400093),
		b.isOneOfThem(ThingKeyword.	NUMBER_1950496919, ThingKeyword.	NUMBER_1950496919),
		b.isOneOfThem(ThingKeyword.	STRING_1808118735, ThingKeyword.	STRING_1808118735),
		b.isOneOfThem(ThingKeyword.	DIMMER_2047107186, ThingKeyword.	DIMMER_2047107186),
		b.isOneOfThem(ThingKeyword.	CONTACT_1678787584, ThingKeyword.	CONTACT_1678787584),
		b.isOneOfThem(ThingKeyword.	DATETIME_1857393595, ThingKeyword.	DATETIME_1857393595),
		b.isOneOfThem(ThingKeyword.	COLOR_65290051, ThingKeyword.	COLOR_65290051),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		)).skipIfOneChild();
		
		


		b.rule(MODELPROPERTY).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(ThingPunctuator.	SK61, ThingPunctuator.	SK61),
		VALUETYPE
		));
		


		b.rule(CHANNEL_ID).is(
		b.sequence(		UID_SEGMENT,
		b.optional(
		b.isOneOfThem(ThingPunctuator.	SK35, ThingPunctuator.	SK35),
		UID_SEGMENT)
		));
		


		b.rule(UID).is(
		b.sequence(		UID_SEGMENT,
		b.isOneOfThem(ThingPunctuator.	SK58, ThingPunctuator.	SK58),
		UID_SEGMENT,
		b.isOneOfThem(ThingPunctuator.	SK58, ThingPunctuator.	SK58),
		UID_SEGMENT,
		b.zeroOrMore(
		b.isOneOfThem(ThingPunctuator.	SK58, ThingPunctuator.	SK58),
		UID_SEGMENT
		)
		));
		

		b.rule(UID_SEGMENT).is(b.isOneOfThem(IDENTIFIER, IDENTIFIER));
		


		b.rule(VALUETYPE).is(
		b.firstOf(
		STRING,
		NUMBER,
		BOOLEAN
		)).skipIfOneChild();
		
		


		b.rule(BOOLEAN).is(
		b.firstOf(
		b.isOneOfThem(ThingKeyword.	TRUE_3569038, ThingKeyword.	TRUE_3569038),
		b.isOneOfThem(ThingKeyword.	FALSE_97196323, ThingKeyword.	FALSE_97196323)
		)).skipIfOneChild();
		
		

		

	}
}
