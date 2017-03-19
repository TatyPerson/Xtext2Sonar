package org.sonar.VaryGrammar.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.VaryGrammar.VaryGrammarConfiguration;
import org.sonar.VaryGrammar.lexer.VaryGrammarLexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.VaryGrammar.api.VaryGrammarImpl;

public final class VaryGrammarParser {

	  private VaryGrammarParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new VaryGrammarConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new VaryGrammarConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       VaryGrammarConfiguration conf) {
	    return Parser.builder(VaryGrammarImpl.create(conf))
	      .withLexer(VaryGrammarLexer.create(conf))
	      .build();
	  }

}
