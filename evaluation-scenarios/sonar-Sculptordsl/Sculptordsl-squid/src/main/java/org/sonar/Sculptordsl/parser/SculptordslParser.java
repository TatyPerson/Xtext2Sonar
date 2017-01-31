package org.sonar.Sculptordsl.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.Sculptordsl.SculptordslConfiguration;
import org.sonar.Sculptordsl.lexer.SculptordslLexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.Sculptordsl.api.SculptordslImpl;

public final class SculptordslParser {

	  private SculptordslParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new SculptordslConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new SculptordslConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       SculptordslConfiguration conf) {
	    return Parser.builder(SculptordslImpl.create(conf))
	      .withLexer(SculptordslLexer.create(conf))
	      .build();
	  }

}
