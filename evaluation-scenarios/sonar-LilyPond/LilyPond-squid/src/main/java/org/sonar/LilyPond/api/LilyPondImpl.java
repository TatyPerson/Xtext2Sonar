package org.sonar.LilyPond.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.LilyPond.LilyPondConfiguration;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.LilyPond.api.LilyPondTokenType.NUMBER;
import static org.sonar.LilyPond.api.LilyPondTokenType.STRING;
import static org.sonar.LilyPond.api.LilyPondTokenType.CHARACTER;
import static org.sonar.LilyPond.api.LilyPondTokenType.INT;
import static org.sonar.LilyPond.api.LilyPondTokenType.ID;
import static org.sonar.LilyPond.api.LilyPondTokenType.NL_NOINDENT;
import static org.sonar.LilyPond.api.LilyPondTokenType.WS;
import static org.sonar.LilyPond.api.LilyPondTokenType.SL_COMMENT;
import static org.sonar.LilyPond.api.LilyPondTokenType.ML_COMMENT;
import static org.sonar.LilyPond.api.LilyPondTokenType.SCHEME_SL_COMMENT;
import static org.sonar.LilyPond.api.LilyPondTokenType.SCHEME_ML_COMMENT;
import static org.sonar.LilyPond.api.LilyPondTokenType.ANY_OTHER;

public enum LilyPondImpl implements GrammarRuleKey {
	LILYPOND,
	EXPRESSION,
	COMMONEXPRESSION,
	ASSIGNMENT,
	SIMPLESCHEMEASSIGNMENT,
	BLOCK,
	SIMPLEBLOCK,
	SIMULTANEOUSBLOCK,
	COMMAND,
	SPECIALCHARACTER,
	UNPARSEDBLOCK,
	UNPARSEDEXPRESSION,
	UNPARSEDCOMMAND,
	REFERENCE,
	TEXT,
	SPECIALCOMMAND,
	INCLUDE,
	VERSION,
	SOURCEFILENAME,
	SOURCEFILELINE,
	MARKUP,
	MARKUPLINES,
	MARKUPLIST,
	MARKUPBODY,
	BLOCKCOMMAND,
	OUTPUTDEFINITION,
	RELATIVEMUSIC,
	PITCH,
	OCTAVE,
	TRANSPOSEDMUSIC,
	MODECHANGE,
	MUSICWITHLYRICS,
	NEWCONTEXT,
	CONTEXTMODIFICATION,
	CONTEXTDEF,
	STRINGINDICATION,
	OTHER,
	OTHERNAME,
	SPECIALCOMMANDNAME,
	SCHEME,
	MARKUPCOMMANDSCHEME,
	SCHEMEEXPRESSION,
	MARKUPSCHEMEEXPRESSION,
	SCHEMEVALUE,
	MARKUPSCHEMEVALUE,
	SCHEMEBOOLEAN,
	SCHEMEBOOLEANVALUE,
	SCHEMELIST,
	SCHEMEBLOCK,
	SCHEMECHARACTER,
	SCHEMETEXT,
	MARKUPCOMMANDSCHEMETEXT,
	SCHEMETEXTVALUE,
	SCHEMETEXTVALUESEGMENT,
	SCHEMEIDENTIFIER,
	IDENTIFIER,
	SCHEMENUMBER,
	SCHEMENUMBERRADIX,
	SIGNEDINTEGER,
	SCHEMEMARKUPCOMMAND;

	public static final Logger LOG = LoggerFactory.getLogger("LilyPondImpl");

	public static Grammar create(LilyPondConfiguration conf) {
		 LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

		 generate(b);
		 b.setRootRule(LILYPOND);

		 return b.buildWithMemoizationOfMatchesForAllRules();
	}

	private static void generate(LexerfulGrammarBuilder b) {
		b.rule(STRING).is(b.isOneOfThem(STRING, STRING));
		b.rule(INT).is(b.isOneOfThem(NUMBER, NUMBER));
		b.rule(ID).is(b.firstOf(b.isOneOfThem(com.sonar.sslr.api.GenericTokenType.IDENTIFIER, com.sonar.sslr.api.GenericTokenType.IDENTIFIER),"|", "."));
		b.rule(NL_NOINDENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(WS).is(b.isOneOfThem(STRING, STRING));
		b.rule(SL_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(ML_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(SCHEME_SL_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(SCHEME_ML_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(ANY_OTHER).is(b.isOneOfThem(STRING, STRING));

		b.rule(LILYPOND).is(
		b.zeroOrMore(EXPRESSION)
		);
		


		b.rule(EXPRESSION).is(
		b.firstOf(
		ASSIGNMENT,
		SIMPLESCHEMEASSIGNMENT,
		COMMONEXPRESSION
		)).skipIfOneChild();
		
		


		b.rule(COMMONEXPRESSION).is(
		b.firstOf(
		COMMAND,
		BLOCK,
		SCHEME,
		b.isOneOfThem(NUMBER, NUMBER),
		TEXT
		)).skipIfOneChild();
		
		


		b.rule(ASSIGNMENT).is(
		b.sequence(		b.firstOf(
		SCHEMEIDENTIFIER,
		STRING),
		b.isOneOfThem(LilyPondPunctuator.	SK61, LilyPondPunctuator.	SK61),
		EXPRESSION
		));
		


		b.rule(SIMPLESCHEMEASSIGNMENT).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK35, LilyPondPunctuator.	SK35),
		b.isOneOfThem(LilyPondPunctuator.	SK40, LilyPondPunctuator.	SK40),
		b.isOneOfThem(LilyPondKeyword.	DEFINE_1335633477, LilyPondKeyword.	DEFINE_1335633477),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		TEXT,
		b.isOneOfThem(LilyPondPunctuator.	SK41, LilyPondPunctuator.	SK41)
		));
		


		b.rule(BLOCK).is(
		b.firstOf(
		SIMPLEBLOCK,
		SIMULTANEOUSBLOCK
		)).skipIfOneChild();
		
		


		b.rule(SIMPLEBLOCK).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK123, LilyPondPunctuator.	SK123),
		b.zeroOrMore(EXPRESSION),
		b.isOneOfThem(LilyPondPunctuator.	SK125, LilyPondPunctuator.	SK125)
		));
		


		b.rule(SIMULTANEOUSBLOCK).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK6060, LilyPondPunctuator.	SK6060),
		b.zeroOrMore(EXPRESSION),
		b.isOneOfThem(LilyPondPunctuator.	SK6262, LilyPondPunctuator.	SK6262)
		));
		


		b.rule(COMMAND).is(
		b.firstOf(
		SPECIALCOMMAND,
		REFERENCE,
		STRINGINDICATION
		)).skipIfOneChild();
		
		


		b.rule(SPECIALCHARACTER).is(
		b.firstOf(
		b.isOneOfThem(LilyPondPunctuator.	SK33, LilyPondPunctuator.	SK33),
		b.isOneOfThem(LilyPondPunctuator.	SK63, LilyPondPunctuator.	SK63),
		b.isOneOfThem(LilyPondPunctuator.	SK43, LilyPondPunctuator.	SK43),
		b.isOneOfThem(LilyPondPunctuator.	SK60, LilyPondPunctuator.	SK60),
		b.isOneOfThem(LilyPondPunctuator.	SK62, LilyPondPunctuator.	SK62),
		b.isOneOfThem(LilyPondPunctuator.	SK91, LilyPondPunctuator.	SK91),
		b.isOneOfThem(LilyPondPunctuator.	SK93, LilyPondPunctuator.	SK93),
		b.isOneOfThem(LilyPondPunctuator.	SK126, LilyPondPunctuator.	SK126)
		)).skipIfOneChild();
		
		


		b.rule(UNPARSEDBLOCK).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK123, LilyPondPunctuator.	SK123),
		b.zeroOrMore(UNPARSEDEXPRESSION),
		b.isOneOfThem(LilyPondPunctuator.	SK125, LilyPondPunctuator.	SK125)
		));
		


		b.rule(UNPARSEDEXPRESSION).is(
		b.firstOf(
		BLOCKCOMMAND,
		INCLUDE,
		ASSIGNMENT,
		UNPARSEDCOMMAND,
		UNPARSEDBLOCK,
		SCHEME,
		b.isOneOfThem(NUMBER, NUMBER),
		TEXT
		)).skipIfOneChild();
		
		


		b.rule(UNPARSEDCOMMAND).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.firstOf(
		SCHEMEIDENTIFIER,
		SPECIALCOMMANDNAME
		))).skipIfOneChild();
		
		


		b.rule(REFERENCE).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		));
		


		b.rule(TEXT).is(
		b.firstOf(
		SCHEMETEXTVALUESEGMENT,
		b.isOneOfThem(LilyPondPunctuator.	SK40, LilyPondPunctuator.	SK40),
		b.isOneOfThem(LilyPondPunctuator.	SK41, LilyPondPunctuator.	SK41),
		b.isOneOfThem(LilyPondPunctuator.	SK39, LilyPondPunctuator.	SK39),
		b.isOneOfThem(LilyPondPunctuator.	SK44, LilyPondPunctuator.	SK44),
		b.isOneOfThem(LilyPondPunctuator.	SK58, LilyPondPunctuator.	SK58)
		)).skipIfOneChild();
		
		

		


		b.rule(SPECIALCOMMAND).is(
		b.firstOf(
		INCLUDE,
		VERSION,
		SOURCEFILENAME,
		SOURCEFILELINE,
		MARKUP,
		MARKUPLINES,
		MARKUPLIST,
		BLOCKCOMMAND,
		OUTPUTDEFINITION,
		RELATIVEMUSIC,
		TRANSPOSEDMUSIC,
		MODECHANGE,
		MUSICWITHLYRICS,
		NEWCONTEXT,
		CONTEXTDEF,
		OTHER
		)).skipIfOneChild();
		
		


		b.rule(INCLUDE).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	INCLUDE_1942574248, LilyPondKeyword.	INCLUDE_1942574248),
		b.firstOf(
		STRING,
		UNPARSEDCOMMAND
		))).skipIfOneChild();
		
		


		b.rule(VERSION).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	VERSION_351608024, LilyPondKeyword.	VERSION_351608024),
		STRING
		));
		


		b.rule(SOURCEFILENAME).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	SOURCEFILENAME_2132045602, LilyPondKeyword.	SOURCEFILENAME_2132045602),
		STRING
		));
		


		b.rule(SOURCEFILELINE).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	SOURCEFILELINE_2131993739, LilyPondKeyword.	SOURCEFILELINE_2131993739),
		b.isOneOfThem(NUMBER, NUMBER)
		));
		


		b.rule(MARKUP).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	MARKUP_1081305560, LilyPondKeyword.	MARKUP_1081305560),
		MARKUPBODY
		));
		


		b.rule(MARKUPLINES).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	MARKUPLINES_1497780695, LilyPondKeyword.	MARKUPLINES_1497780695),
		MARKUPBODY
		));
		


		b.rule(MARKUPLIST).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	MARKUPLIST_1572336326, LilyPondKeyword.	MARKUPLIST_1572336326),
		MARKUPBODY
		));
		


		b.rule(MARKUPBODY).is(
		b.sequence(		b.firstOf(
		b.zeroOrMore(MARKUPCOMMANDSCHEME),
		b.zeroOrMore(MARKUPCOMMANDSCHEMETEXT),
		b.zeroOrMore(UNPARSEDCOMMAND)),
		b.optional(
		UNPARSEDBLOCK)
		));
		


		b.rule(BLOCKCOMMAND).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.firstOf(
		b.isOneOfThem(LilyPondKeyword.	BOOK_3029737, LilyPondKeyword.	BOOK_3029737),
		b.isOneOfThem(LilyPondKeyword.	BOOKPART_2005467740, LilyPondKeyword.	BOOKPART_2005467740),
		b.isOneOfThem(LilyPondKeyword.	HEADER_1221270899, LilyPondKeyword.	HEADER_1221270899),
		b.isOneOfThem(LilyPondKeyword.	SCORE_109264530, LilyPondKeyword.	SCORE_109264530)),
		SIMPLEBLOCK
		));
		


		b.rule(OUTPUTDEFINITION).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.firstOf(
		b.isOneOfThem(LilyPondKeyword.	PAPER_106434956, LilyPondKeyword.	PAPER_106434956),
		b.isOneOfThem(LilyPondKeyword.	MIDI_3351329, LilyPondKeyword.	MIDI_3351329),
		b.isOneOfThem(LilyPondKeyword.	LAYOUT_1109722326, LilyPondKeyword.	LAYOUT_1109722326)),
		UNPARSEDBLOCK
		));
		


		b.rule(RELATIVEMUSIC).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	RELATIVE_554435892, LilyPondKeyword.	RELATIVE_554435892),
		b.optional(
		PITCH),
		EXPRESSION
		));
		


		b.rule(PITCH).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(LilyPondPunctuator.	SK61, LilyPondPunctuator.	SK61)),
		b.optional(
		OCTAVE),
		b.optional(
		b.isOneOfThem(LilyPondPunctuator.	SK33, LilyPondPunctuator.	SK33)),
		b.optional(
		b.isOneOfThem(LilyPondPunctuator.	SK63, LilyPondPunctuator.	SK63))
		));
		


		b.rule(OCTAVE).is(
		b.firstOf(
		b.isOneOfThem(LilyPondPunctuator.	SK39, LilyPondPunctuator.	SK39),
		b.isOneOfThem(LilyPondPunctuator.	SK44, LilyPondPunctuator.	SK44)
		)).skipIfOneChild();
		
		


		b.rule(TRANSPOSEDMUSIC).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	TRANSPOSE_1052964665, LilyPondKeyword.	TRANSPOSE_1052964665),
		PITCH,
		PITCH,
		EXPRESSION
		));
		


		b.rule(MODECHANGE).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.firstOf(
		b.isOneOfThem(LilyPondKeyword.	CHORDMODE_1107408895, LilyPondKeyword.	CHORDMODE_1107408895),
		b.isOneOfThem(LilyPondKeyword.	DRUMMODE_262789559, LilyPondKeyword.	DRUMMODE_262789559),
		b.isOneOfThem(LilyPondKeyword.	FIGUREMODE_428756345, LilyPondKeyword.	FIGUREMODE_428756345),
		b.isOneOfThem(LilyPondKeyword.	LYRICMODE_307849598, LilyPondKeyword.	LYRICMODE_307849598),
		b.isOneOfThem(LilyPondKeyword.	NOTEMODE_1581883541, LilyPondKeyword.	NOTEMODE_1581883541)),
		BLOCK
		));
		


		b.rule(MUSICWITHLYRICS).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	LYRICSTO_1672492689, LilyPondKeyword.	LYRICSTO_1672492689),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING),
		EXPRESSION
		));
		


		b.rule(NEWCONTEXT).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.firstOf(
		b.isOneOfThem(LilyPondKeyword.	NEW_108960, LilyPondKeyword.	NEW_108960),
		b.isOneOfThem(LilyPondKeyword.	CONTEXT_951530927, LilyPondKeyword.	CONTEXT_951530927)),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(LilyPondPunctuator.	SK61, LilyPondPunctuator.	SK61),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		CONTEXTMODIFICATION),
		EXPRESSION
		));
		


		b.rule(CONTEXTMODIFICATION).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	WITH_3649734, LilyPondKeyword.	WITH_3649734),
		UNPARSEDBLOCK
		));
		


		b.rule(CONTEXTDEF).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(LilyPondKeyword.	CONTEXT_951530927, LilyPondKeyword.	CONTEXT_951530927),
		UNPARSEDBLOCK
		));
		


		b.rule(STRINGINDICATION).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.isOneOfThem(NUMBER, NUMBER)
		));
		


		b.rule(OTHER).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK92, LilyPondPunctuator.	SK92),
		b.firstOf(
		SPECIALCHARACTER,
		b.isOneOfThem(LilyPondPunctuator.	SK40, LilyPondPunctuator.	SK40),
		b.isOneOfThem(LilyPondPunctuator.	SK41, LilyPondPunctuator.	SK41),
		OTHERNAME
		))).skipIfOneChild();
		
		


		b.rule(OTHERNAME).is(
		b.firstOf(
		b.isOneOfThem(LilyPondKeyword.	ACCEPTS_1177621397, LilyPondKeyword.	ACCEPTS_1177621397),
		b.isOneOfThem(LilyPondKeyword.	ADDLYRICS_721200085, LilyPondKeyword.	ADDLYRICS_721200085),
		b.isOneOfThem(LilyPondKeyword.	ALIAS_92902992, LilyPondKeyword.	ALIAS_92902992),
		b.isOneOfThem(LilyPondKeyword.	ALTERNATIVE_196794451, LilyPondKeyword.	ALTERNATIVE_196794451),
		b.isOneOfThem(LilyPondKeyword.	CHANGE_1361636432, LilyPondKeyword.	CHANGE_1361636432),
		b.isOneOfThem(LilyPondKeyword.	CHORDS_1361215593, LilyPondKeyword.	CHORDS_1361215593),
		b.isOneOfThem(LilyPondKeyword.	CONSISTS_568121382, LilyPondKeyword.	CONSISTS_568121382),
		b.isOneOfThem(LilyPondKeyword.	DEFAULT_1544803905, LilyPondKeyword.	DEFAULT_1544803905),
		b.isOneOfThem(LilyPondKeyword.	DEFAULTCHILD_646914181, LilyPondKeyword.	DEFAULTCHILD_646914181),
		b.isOneOfThem(LilyPondKeyword.	DENIES_1335395414, LilyPondKeyword.	DENIES_1335395414),
		b.isOneOfThem(LilyPondKeyword.	DESCRIPTION_1724546052, LilyPondKeyword.	DESCRIPTION_1724546052),
		b.isOneOfThem(LilyPondKeyword.	DRUMS_95864205, LilyPondKeyword.	DRUMS_95864205),
		b.isOneOfThem(LilyPondKeyword.	FIGURES_859123185, LilyPondKeyword.	FIGURES_859123185),
		b.isOneOfThem(LilyPondKeyword.	LYRICS_1087772684, LilyPondKeyword.	LYRICS_1087772684),
		b.isOneOfThem(LilyPondKeyword.	MAININPUT_241959503, LilyPondKeyword.	MAININPUT_241959503),
		b.isOneOfThem(LilyPondKeyword.	NAME_3373707, LilyPondKeyword.	NAME_3373707),
		b.isOneOfThem(LilyPondKeyword.	OBJECTID_90496154, LilyPondKeyword.	OBJECTID_90496154),
		b.isOneOfThem(LilyPondKeyword.	OCTAVE_1022150224, LilyPondKeyword.	OCTAVE_1022150224),
		b.isOneOfThem(LilyPondKeyword.	OVERRIDE_529996748, LilyPondKeyword.	OVERRIDE_529996748),
		b.isOneOfThem(LilyPondKeyword.	REMOVE_934610812, LilyPondKeyword.	REMOVE_934610812),
		b.isOneOfThem(LilyPondKeyword.	REPEAT_934531685, LilyPondKeyword.	REPEAT_934531685),
		b.isOneOfThem(LilyPondKeyword.	REST_3496916, LilyPondKeyword.	REST_3496916),
		b.isOneOfThem(LilyPondKeyword.	REVERT_934352412, LilyPondKeyword.	REVERT_934352412),
		b.isOneOfThem(LilyPondKeyword.	SEQUENTIAL_164011777, LilyPondKeyword.	SEQUENTIAL_164011777),
		b.isOneOfThem(LilyPondKeyword.	SET_113762, LilyPondKeyword.	SET_113762),
		b.isOneOfThem(LilyPondKeyword.	SIMULTANEOUS_1002697093, LilyPondKeyword.	SIMULTANEOUS_1002697093),
		b.isOneOfThem(LilyPondKeyword.	TEMPO_110245659, LilyPondKeyword.	TEMPO_110245659),
		b.isOneOfThem(LilyPondKeyword.	TYPE_3575610, LilyPondKeyword.	TYPE_3575610),
		b.isOneOfThem(LilyPondKeyword.	UNSET_111442729, LilyPondKeyword.	UNSET_111442729),
		b.isOneOfThem(LilyPondKeyword.	WITH_3649734, LilyPondKeyword.	WITH_3649734)
		)).skipIfOneChild();
		
		


		b.rule(SPECIALCOMMANDNAME).is(
		b.firstOf(
		b.isOneOfThem(LilyPondKeyword.	DEFINE_1335633477, LilyPondKeyword.	DEFINE_1335633477),
		b.isOneOfThem(LilyPondKeyword.	INCLUDE_1942574248, LilyPondKeyword.	INCLUDE_1942574248),
		b.isOneOfThem(LilyPondKeyword.	VERSION_351608024, LilyPondKeyword.	VERSION_351608024),
		b.isOneOfThem(LilyPondKeyword.	SOURCEFILENAME_2132045602, LilyPondKeyword.	SOURCEFILENAME_2132045602),
		b.isOneOfThem(LilyPondKeyword.	SOURCEFILELINE_2131993739, LilyPondKeyword.	SOURCEFILELINE_2131993739),
		b.isOneOfThem(LilyPondKeyword.	MARKUP_1081305560, LilyPondKeyword.	MARKUP_1081305560),
		b.isOneOfThem(LilyPondKeyword.	MARKUPLINES_1497780695, LilyPondKeyword.	MARKUPLINES_1497780695),
		b.isOneOfThem(LilyPondKeyword.	MARKUPLIST_1572336326, LilyPondKeyword.	MARKUPLIST_1572336326),
		b.isOneOfThem(LilyPondKeyword.	BOOK_3029737, LilyPondKeyword.	BOOK_3029737),
		b.isOneOfThem(LilyPondKeyword.	BOOKPART_2005467740, LilyPondKeyword.	BOOKPART_2005467740),
		b.isOneOfThem(LilyPondKeyword.	CONTEXT_951530927, LilyPondKeyword.	CONTEXT_951530927),
		b.isOneOfThem(LilyPondKeyword.	HEADER_1221270899, LilyPondKeyword.	HEADER_1221270899),
		b.isOneOfThem(LilyPondKeyword.	SCORE_109264530, LilyPondKeyword.	SCORE_109264530),
		b.isOneOfThem(LilyPondKeyword.	PAPER_106434956, LilyPondKeyword.	PAPER_106434956),
		b.isOneOfThem(LilyPondKeyword.	MIDI_3351329, LilyPondKeyword.	MIDI_3351329),
		b.isOneOfThem(LilyPondKeyword.	LAYOUT_1109722326, LilyPondKeyword.	LAYOUT_1109722326),
		b.isOneOfThem(LilyPondKeyword.	RELATIVE_554435892, LilyPondKeyword.	RELATIVE_554435892),
		b.isOneOfThem(LilyPondKeyword.	TRANSPOSE_1052964665, LilyPondKeyword.	TRANSPOSE_1052964665),
		b.isOneOfThem(LilyPondKeyword.	CHORDMODE_1107408895, LilyPondKeyword.	CHORDMODE_1107408895),
		b.isOneOfThem(LilyPondKeyword.	DRUMMODE_262789559, LilyPondKeyword.	DRUMMODE_262789559),
		b.isOneOfThem(LilyPondKeyword.	FIGUREMODE_428756345, LilyPondKeyword.	FIGUREMODE_428756345),
		b.isOneOfThem(LilyPondKeyword.	LYRICMODE_307849598, LilyPondKeyword.	LYRICMODE_307849598),
		b.isOneOfThem(LilyPondKeyword.	NOTEMODE_1581883541, LilyPondKeyword.	NOTEMODE_1581883541),
		b.isOneOfThem(LilyPondKeyword.	LYRICSTO_1672492689, LilyPondKeyword.	LYRICSTO_1672492689),
		b.isOneOfThem(LilyPondKeyword.	NEW_108960, LilyPondKeyword.	NEW_108960),
		b.isOneOfThem(LilyPondKeyword.	WITH_3649734, LilyPondKeyword.	WITH_3649734),
		OTHERNAME
		)).skipIfOneChild();
		
		


		b.rule(SCHEME).is(
		b.sequence(		b.firstOf(
		b.isOneOfThem(LilyPondPunctuator.	SK35, LilyPondPunctuator.	SK35),
		b.isOneOfThem(LilyPondPunctuator.	SK36, LilyPondPunctuator.	SK36)),
		SCHEMEEXPRESSION
		));
		


		b.rule(MARKUPCOMMANDSCHEME).is(
		b.sequence(		b.firstOf(
		b.isOneOfThem(LilyPondPunctuator.	SK35, LilyPondPunctuator.	SK35),
		b.isOneOfThem(LilyPondPunctuator.	SK36, LilyPondPunctuator.	SK36)),
		MARKUPSCHEMEEXPRESSION
		));
		


		b.rule(SCHEMEEXPRESSION).is(
		b.sequence(		b.firstOf(
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK39, LilyPondPunctuator.	SK39)),
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK96, LilyPondPunctuator.	SK96)),
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK44, LilyPondPunctuator.	SK44)),
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK64, LilyPondPunctuator.	SK64))),
		b.optional(
		b.isOneOfThem(LilyPondPunctuator.	SK36, LilyPondPunctuator.	SK36)),
		SCHEMEVALUE
		));
		


		b.rule(MARKUPSCHEMEEXPRESSION).is(
		b.sequence(		b.firstOf(
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK39, LilyPondPunctuator.	SK39)),
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK96, LilyPondPunctuator.	SK96)),
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK44, LilyPondPunctuator.	SK44)),
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK64, LilyPondPunctuator.	SK64))),
		b.optional(
		b.isOneOfThem(LilyPondPunctuator.	SK36, LilyPondPunctuator.	SK36)),
		MARKUPSCHEMEVALUE
		));
		


		b.rule(SCHEMEVALUE).is(
		b.firstOf(
		SCHEMEBOOLEAN,
		SCHEMELIST,
		SCHEMEBLOCK,
		SCHEMECHARACTER,
		SCHEMENUMBER,
		SCHEMETEXT,
		SCHEMEMARKUPCOMMAND
		)).skipIfOneChild();
		
		


		b.rule(MARKUPSCHEMEVALUE).is(
		b.firstOf(
		SCHEMEBOOLEAN,
		SCHEMELIST,
		SCHEMEBLOCK,
		SCHEMECHARACTER,
		SCHEMETEXT,
		SCHEMEMARKUPCOMMAND
		)).skipIfOneChild();
		
		

		b.rule(SCHEMEBOOLEAN).is(SCHEMEBOOLEANVALUE);
		


		b.rule(SCHEMEBOOLEANVALUE).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK35, LilyPondPunctuator.	SK35),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		));
		


		b.rule(SCHEMELIST).is(
		b.sequence(		b.optional(
		b.isOneOfThem(LilyPondPunctuator.	SK35, LilyPondPunctuator.	SK35)),
		b.isOneOfThem(LilyPondPunctuator.	SK40, LilyPondPunctuator.	SK40),
		b.zeroOrMore(SCHEMEEXPRESSION),
		b.firstOf(
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK39, LilyPondPunctuator.	SK39)),
		b.zeroOrMore(b.isOneOfThem(LilyPondPunctuator.	SK44, LilyPondPunctuator.	SK44))),
		b.isOneOfThem(LilyPondPunctuator.	SK41, LilyPondPunctuator.	SK41)
		));
		


		b.rule(SCHEMEBLOCK).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK35123, LilyPondPunctuator.	SK35123),
		b.oneOrMore(EXPRESSION),
		b.isOneOfThem(LilyPondPunctuator.	SK35125, LilyPondPunctuator.	SK35125)
		));
		


		b.rule(SCHEMECHARACTER).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK3592, LilyPondPunctuator.	SK3592),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		SPECIALCHARACTER,
		b.isOneOfThem(LilyPondPunctuator.	SK45, LilyPondPunctuator.	SK45),
		ANY_OTHER
		))).skipIfOneChild();
		
		

		b.rule(SCHEMETEXT).is(SCHEMETEXTVALUE);
		

		b.rule(MARKUPCOMMANDSCHEMETEXT).is(SCHEMETEXTVALUE);
		


		b.rule(SCHEMETEXTVALUE).is(
		b.sequence(		SCHEMETEXTVALUESEGMENT,
		b.zeroOrMore(
		b.isOneOfThem(LilyPondPunctuator.	SK58, LilyPondPunctuator.	SK58),
		b.isOneOfThem(LilyPondPunctuator.	SK58, LilyPondPunctuator.	SK58),
		SCHEMETEXTVALUESEGMENT
		)
		));
		


		b.rule(SCHEMETEXTVALUESEGMENT).is(
		b.firstOf(
		b.isOneOfThem(LilyPondPunctuator.	SK9292, LilyPondPunctuator.	SK9292),
		b.isOneOfThem(LilyPondPunctuator.	SK61, LilyPondPunctuator.	SK61),
		b.isOneOfThem(LilyPondPunctuator.	SK45, LilyPondPunctuator.	SK45),
		SPECIALCHARACTER,
		SCHEMEIDENTIFIER,
		STRING,
		b.isOneOfThem(NUMBER, NUMBER),
		ANY_OTHER
		)).skipIfOneChild();
		
		


		b.rule(SCHEMEIDENTIFIER).is(
		b.sequence(		IDENTIFIER,
		b.zeroOrMore(
		b.isOneOfThem(LilyPondPunctuator.	SK45, LilyPondPunctuator.	SK45),
		IDENTIFIER
		)
		));
		


		b.rule(IDENTIFIER).is(
		b.firstOf(
		ID,
		SPECIALCOMMANDNAME
		)).skipIfOneChild();
		
		


		b.rule(SCHEMENUMBER).is(
		b.sequence(		b.optional(
		SCHEMENUMBERRADIX),
		SIGNEDINTEGER
		));
		


		b.rule(SCHEMENUMBERRADIX).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK35, LilyPondPunctuator.	SK35),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		));
		


		b.rule(SIGNEDINTEGER).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK45, LilyPondPunctuator.	SK45),
		b.isOneOfThem(NUMBER, NUMBER)
		));
		


		b.rule(SCHEMEMARKUPCOMMAND).is(
		b.sequence(		b.isOneOfThem(LilyPondPunctuator.	SK3558, LilyPondPunctuator.	SK3558),
		SCHEMEIDENTIFIER
		));
		

	}
}
