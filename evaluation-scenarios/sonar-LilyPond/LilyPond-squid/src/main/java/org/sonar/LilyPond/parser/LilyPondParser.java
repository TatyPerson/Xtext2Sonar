package org.sonar.LilyPond.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.LilyPond.LilyPondConfiguration;
import org.sonar.LilyPond.lexer.LilyPondLexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.LilyPond.api.LilyPondImpl;

public final class LilyPondParser {

	  private LilyPondParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new LilyPondConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new LilyPondConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       LilyPondConfiguration conf) {
	    return Parser.builder(LilyPondImpl.create(conf))
	      .withLexer(LilyPondLexer.create(conf))
	      .build();
	  }

}
