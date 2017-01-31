package org.sonar.Persistence.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.Persistence.PersistenceConfiguration;
import org.sonar.Persistence.lexer.PersistenceLexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.Persistence.api.PersistenceImpl;

public final class PersistenceParser {

	  private PersistenceParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new PersistenceConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new PersistenceConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       PersistenceConfiguration conf) {
	    return Parser.builder(PersistenceImpl.create(conf))
	      .withLexer(PersistenceLexer.create(conf))
	      .build();
	  }

}
