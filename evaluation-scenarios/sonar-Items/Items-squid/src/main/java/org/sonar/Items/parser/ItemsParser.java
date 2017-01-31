package org.sonar.Items.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.Items.ItemsConfiguration;
import org.sonar.Items.lexer.ItemsLexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.Items.api.ItemsImpl;

public final class ItemsParser {

	  private ItemsParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new ItemsConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new ItemsConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       ItemsConfiguration conf) {
	    return Parser.builder(ItemsImpl.create(conf))
	      .withLexer(ItemsLexer.create(conf))
	      .build();
	  }

}
