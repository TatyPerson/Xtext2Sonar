package org.sonar.PogoDsl.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.PogoDsl.PogoDslConfiguration;
import org.sonar.PogoDsl.lexer.PogoDslLexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.PogoDsl.api.PogoDslImpl;

public final class PogoDslParser {

	  private PogoDslParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new PogoDslConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new PogoDslConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       PogoDslConfiguration conf) {
	    return Parser.builder(PogoDslImpl.create(conf))
	      .withLexer(PogoDslLexer.create(conf))
	      .build();
	  }

}
