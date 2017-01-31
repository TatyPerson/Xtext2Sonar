package org.sonar.Thing.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.Thing.ThingConfiguration;
import org.sonar.Thing.lexer.ThingLexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.Thing.api.ThingImpl;

public final class ThingParser {

	  private ThingParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new ThingConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new ThingConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       ThingConfiguration conf) {
	    return Parser.builder(ThingImpl.create(conf))
	      .withLexer(ThingLexer.create(conf))
	      .build();
	  }

}
