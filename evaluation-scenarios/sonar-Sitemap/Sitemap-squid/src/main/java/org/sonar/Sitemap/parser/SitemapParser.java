package org.sonar.Sitemap.parser;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;
import org.sonar.Sitemap.SitemapConfiguration;
import org.sonar.Sitemap.lexer.SitemapLexer;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.Sitemap.api.SitemapImpl;

public final class SitemapParser {

	  private SitemapParser() {
	  }

	  public static Parser<Grammar> create() {
	    return create(new SquidAstVisitorContextImpl<Grammar>(new SourceProject("")),
	                  new SitemapConfiguration());
	  }

	  public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context) {
	    return create(context, new SitemapConfiguration());
	  }

	 public static Parser<Grammar> create(SquidAstVisitorContext<Grammar> context,
	                                       SitemapConfiguration conf) {
	    return Parser.builder(SitemapImpl.create(conf))
	      .withLexer(SitemapLexer.create(conf))
	      .build();
	  }

}
