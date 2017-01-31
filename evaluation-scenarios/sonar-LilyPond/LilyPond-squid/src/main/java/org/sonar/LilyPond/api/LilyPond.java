package org.sonar.LilyPond.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;

public class LilyPond extends Grammar {
	public Rule LILYPOND;
	public Rule EXPRESSION;
	public Rule COMMONEXPRESSION;
	public Rule ASSIGNMENT;
	public Rule SIMPLESCHEMEASSIGNMENT;
	public Rule BLOCK;
	public Rule SIMPLEBLOCK;
	public Rule SIMULTANEOUSBLOCK;
	public Rule COMMAND;
	public Rule SPECIALCHARACTER;
	public Rule UNPARSEDBLOCK;
	public Rule UNPARSEDEXPRESSION;
	public Rule UNPARSEDCOMMAND;
	public Rule REFERENCE;
	public Rule TEXT;
	public Rule NUMBER;
	public Rule SPECIALCOMMAND;
	public Rule INCLUDE;
	public Rule VERSION;
	public Rule SOURCEFILENAME;
	public Rule SOURCEFILELINE;
	public Rule MARKUP;
	public Rule MARKUPLINES;
	public Rule MARKUPLIST;
	public Rule MARKUPBODY;
	public Rule BLOCKCOMMAND;
	public Rule OUTPUTDEFINITION;
	public Rule RELATIVEMUSIC;
	public Rule PITCH;
	public Rule OCTAVE;
	public Rule TRANSPOSEDMUSIC;
	public Rule MODECHANGE;
	public Rule MUSICWITHLYRICS;
	public Rule NEWCONTEXT;
	public Rule CONTEXTMODIFICATION;
	public Rule CONTEXTDEF;
	public Rule STRINGINDICATION;
	public Rule OTHER;
	public Rule OTHERNAME;
	public Rule SPECIALCOMMANDNAME;
	public Rule SCHEME;
	public Rule MARKUPCOMMANDSCHEME;
	public Rule SCHEMEEXPRESSION;
	public Rule MARKUPSCHEMEEXPRESSION;
	public Rule SCHEMEVALUE;
	public Rule MARKUPSCHEMEVALUE;
	public Rule SCHEMEBOOLEAN;
	public Rule SCHEMEBOOLEANVALUE;
	public Rule SCHEMELIST;
	public Rule SCHEMEBLOCK;
	public Rule SCHEMECHARACTER;
	public Rule SCHEMETEXT;
	public Rule MARKUPCOMMANDSCHEMETEXT;
	public Rule SCHEMETEXTVALUE;
	public Rule SCHEMETEXTVALUESEGMENT;
	public Rule SCHEMEIDENTIFIER;
	public Rule IDENTIFIER;
	public Rule SCHEMENUMBER;
	public Rule SCHEMENUMBERRADIX;
	public Rule SIGNEDINTEGER;
	public Rule SCHEMEMARKUPCOMMAND;

	@Override
	public Rule getRootRule() {
		// TODO Auto-generated method stub
		return LILYPOND;
	}
}
