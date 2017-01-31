package org.sonar.LilyPond.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum LilyPondKeyword implements TokenType, GrammarRuleKey {
		
		DEFAULTCHILD_646914181("defaultchild"),
		SCORE_109264530("score"),
		DRUMMODE_262789559("drummode"),
		MAININPUT_241959503("maininput"),
		VERSION_351608024("version"),
		SOURCEFILENAME_2132045602("sourcefilename"),
		DRUMS_95864205("drums"),
		RELATIVE_554435892("relative"),
		SIMULTANEOUS_1002697093("simultaneous"),
		DEFAULT_1544803905("default"),
		OVERRIDE_529996748("override"),
		SET_113762("set"),
		MARKUPLINES_1497780695("markuplines"),
		OCTAVE_1022150224("octave"),
		NAME_3373707("name"),
		FIGURES_859123185("figures"),
		SEQUENTIAL_164011777("sequential"),
		BOOK_3029737("book"),
		DESCRIPTION_1724546052("description"),
		REMOVE_934610812("remove"),
		NOTEMODE_1581883541("notemode"),
		LYRICS_1087772684("lyrics"),
		LAYOUT_1109722326("layout"),
		WITH_3649734("with"),
		FIGUREMODE_428756345("figuremode"),
		TYPE_3575610("type"),
		MIDI_3351329("midi"),
		CONTEXT_951530927("context"),
		LYRICSTO_1672492689("lyricsto"),
		LYRICMODE_307849598("lyricmode"),
		ADDLYRICS_721200085("addlyrics"),
		CHORDS_1361215593("chords"),
		INCLUDE_1942574248("include"),
		CHANGE_1361636432("change"),
		MARKUPLIST_1572336326("markuplist"),
		TEMPO_110245659("tempo"),
		UNSET_111442729("unset"),
		SOURCEFILELINE_2131993739("sourcefileline"),
		NEW_108960("new"),
		MARKUP_1081305560("markup"),
		ACCEPTS_1177621397("accepts"),
		REVERT_934352412("revert"),
		HEADER_1221270899("header"),
		DENIES_1335395414("denies"),
		PAPER_106434956("paper"),
		BOOKPART_2005467740("bookpart"),
		REPEAT_934531685("repeat"),
		DEFINE_1335633477("define"),
		ALIAS_92902992("alias"),
		REST_3496916("rest"),
		ALTERNATIVE_196794451("alternative"),
		CONSISTS_568121382("consists"),
		TRANSPOSE_1052964665("transpose"),
		CHORDMODE_1107408895("chordmode"),
		OBJECTID_90496154("objectid");
;

  private final String value;

  private LilyPondKeyword(String value) {
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
    LilyPondKeyword[] keywordsEnum = LilyPondKeyword.values();
    String[] keywords = new String[keywordsEnum.length];
    for (int i = 0; i < keywords.length; i++) {
      keywords[i] = keywordsEnum[i].getValue();
    }
    return keywords;
  }
}
