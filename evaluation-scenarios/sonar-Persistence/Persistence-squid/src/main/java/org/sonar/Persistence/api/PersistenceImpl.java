package org.sonar.Persistence.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.Persistence.PersistenceConfiguration;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.Persistence.api.PersistenceTokenType.NUMBER;
import static org.sonar.Persistence.api.PersistenceTokenType.STRING;
import static org.sonar.Persistence.api.PersistenceTokenType.CHARACTER;

public enum PersistenceImpl implements GrammarRuleKey {
	PERSISTENCEMODEL,
	STRATEGY,
	CRONSTRATEGY,
	FILTER,
	FILTERDETAILS,
	THRESHOLDFILTER,
	TIMEFILTER,
	PERSISTENCECONFIGURATION,
	ALLCONFIG,
	ITEMCONFIG,
	GROUPCONFIG,
	DECIMAL;

	public static final Logger LOG = LoggerFactory.getLogger("PersistenceImpl");

	public static Grammar create(PersistenceConfiguration conf) {
		 LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

		 generate(b);
		 b.setRootRule(PERSISTENCEMODEL);

		 return b.buildWithMemoizationOfMatchesForAllRules();
	}

	private static void generate(LexerfulGrammarBuilder b) {

		b.rule(PERSISTENCEMODEL).is(
		b.sequence(		b.isOneOfThem(PersistenceKeyword.	STRATEGIES_2080784241, PersistenceKeyword.	STRATEGIES_2080784241),
		b.isOneOfThem(PersistencePunctuator.	SK123, PersistencePunctuator.	SK123),
		b.zeroOrMore(STRATEGY),
		b.optional(
		b.isOneOfThem(PersistenceKeyword.	DEFAULT_1544803905, PersistenceKeyword.	DEFAULT_1544803905),
		b.isOneOfThem(PersistencePunctuator.	SK61, PersistencePunctuator.	SK61),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.zeroOrMore(
		b.isOneOfThem(PersistencePunctuator.	SK44, PersistencePunctuator.	SK44),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER)),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PersistencePunctuator.	SK125, PersistencePunctuator.	SK125),
		b.optional(
		b.isOneOfThem(PersistenceKeyword.	FILTERS_810105819, PersistenceKeyword.	FILTERS_810105819),
		b.isOneOfThem(PersistencePunctuator.	SK123, PersistencePunctuator.	SK123),
		b.zeroOrMore(FILTER),
		b.isOneOfThem(PersistencePunctuator.	SK125, PersistencePunctuator.	SK125)),
		b.optional(
		b.isOneOfThem(PersistenceKeyword.	ITEMS_70973344, PersistenceKeyword.	ITEMS_70973344),
		b.isOneOfThem(PersistencePunctuator.	SK123, PersistencePunctuator.	SK123),
		b.zeroOrMore(PERSISTENCECONFIGURATION),
		b.isOneOfThem(PersistencePunctuator.	SK125, PersistencePunctuator.	SK125))
		));
		


		b.rule(STRATEGY).is(
		b.firstOf(
		CRONSTRATEGY,
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		)).skipIfOneChild();
		
		


		b.rule(CRONSTRATEGY).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PersistencePunctuator.	SK58, PersistencePunctuator.	SK58),
		STRING
		));
		


		b.rule(FILTER).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PersistencePunctuator.	SK58, PersistencePunctuator.	SK58),
		FILTERDETAILS
		));
		


		b.rule(FILTERDETAILS).is(
		b.firstOf(
		THRESHOLDFILTER,
		TIMEFILTER
		)).skipIfOneChild();
		
		


		b.rule(THRESHOLDFILTER).is(
		b.sequence(		b.isOneOfThem(PersistencePunctuator.	SK62, PersistencePunctuator.	SK62),
		DECIMAL,
		b.isOneOfThem(PersistencePunctuator.	SK37, PersistencePunctuator.	SK37)
		));
		


		b.rule(TIMEFILTER).is(
		b.sequence(		b.isOneOfThem(NUMBER, NUMBER),
		b.firstOf(
		b.isOneOfThem(PersistenceKeyword.	S_115, PersistenceKeyword.	S_115),
		b.isOneOfThem(PersistenceKeyword.	M_109, PersistenceKeyword.	M_109),
		b.isOneOfThem(PersistenceKeyword.	H_104, PersistenceKeyword.	H_104),
		b.isOneOfThem(PersistenceKeyword.	D_100, PersistenceKeyword.	D_100)
		))).skipIfOneChild();
		
		


		b.rule(PERSISTENCECONFIGURATION).is(
		b.sequence(		b.firstOf(
		ALLCONFIG,
		ITEMCONFIG,
		GROUPCONFIG),
		b.zeroOrMore(
		b.isOneOfThem(PersistencePunctuator.	SK44, PersistencePunctuator.	SK44),
		ALLCONFIG,
		ITEMCONFIG,
		GROUPCONFIG),
		b.firstOf(
		ALLCONFIG,
		ITEMCONFIG,
		GROUPCONFIG),
		b.optional(
		b.isOneOfThem(PersistencePunctuator.	SK4562, PersistencePunctuator.	SK4562),
		STRING),
		b.firstOf(
		b.isOneOfThem(PersistencePunctuator.	SK58, PersistencePunctuator.	SK58),
		b.optional(
		b.isOneOfThem(PersistenceKeyword.	STRATEGY_1787798387, PersistenceKeyword.	STRATEGY_1787798387),
		b.isOneOfThem(PersistencePunctuator.	SK61, PersistencePunctuator.	SK61),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PersistencePunctuator.	SK44, PersistencePunctuator.	SK44),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.optional(
		b.isOneOfThem(PersistenceKeyword.	FILTER_1274492040, PersistenceKeyword.	FILTER_1274492040),
		b.isOneOfThem(PersistencePunctuator.	SK61, PersistencePunctuator.	SK61),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PersistencePunctuator.	SK44, PersistencePunctuator.	SK44),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.isOneOfThem(PersistencePunctuator.	SK59, PersistencePunctuator.	SK59)
		))).skipIfOneChild();
		
		


		b.rule(ALLCONFIG).is(
		b.isOneOfThem(PersistencePunctuator.	SK42, PersistencePunctuator.	SK42)
		);
		

		b.rule(ITEMCONFIG).is(b.isOneOfThem(IDENTIFIER, IDENTIFIER));
		


		b.rule(GROUPCONFIG).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PersistencePunctuator.	SK42, PersistencePunctuator.	SK42)
		));
		


		b.rule(DECIMAL).is(
		b.sequence(		b.isOneOfThem(NUMBER, NUMBER),
		b.optional(
		b.isOneOfThem(PersistencePunctuator.	SK46, PersistencePunctuator.	SK46),
		b.isOneOfThem(NUMBER, NUMBER))
		));
		

	}
}
