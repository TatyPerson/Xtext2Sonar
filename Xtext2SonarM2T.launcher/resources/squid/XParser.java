package org.sonar.NAME.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.NAME.NAMEConfiguration;
import org.sonar.NAME.lexer.NAMELexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.NAME.api.NAMEImpl;

public final class NAMEParser {

	  private NAMEParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new NAMEConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new NAMEConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       NAMEConfiguration conf) {
	    return Parser.builder(NAMEImpl.create(conf))
	      .withLexer(NAMELexer.create(conf))
	      .build();
	  }

}
