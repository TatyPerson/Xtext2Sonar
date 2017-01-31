package org.sonar.Sitemap.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;

import org.sonar.Sitemap.visitors.SitemapCharsetAwareVisitor;
import org.sonar.Sitemap.visitors.SitemapFileVisitor;
import org.sonar.Sitemap.SitemapCommentAnalyser;
import org.sonar.Sitemap.SitemapConfiguration;
import org.sonar.Sitemap.parser.SitemapParser;
import org.sonar.squidbridge.AstScanner;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceCode;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.squidbridge.indexer.QueryByType;
import org.sonar.squidbridge.metrics.CommentsVisitor;
import org.sonar.squidbridge.metrics.CounterVisitor;
import org.sonar.squidbridge.metrics.LinesOfCodeVisitor;
import org.sonar.squidbridge.metrics.LinesVisitor;

import java.io.File;
import java.util.Collection;

public final class SitemapAstScanner {

  private SitemapAstScanner() {}

  /**
   * Helper method for testing checks without having to deploy them on a Sonar instance.
   */
  public static SourceFile scanSingleFileConfig(File file, SitemapConfiguration SitemapConfig, SquidAstVisitor<Grammar>... visitors) {
	    if (!file.isFile()) {
	      throw new IllegalArgumentException("File '" + file + "' not found.");
	    }
	    AstScanner<Grammar> scanner = create(SitemapConfig, visitors);
	    scanner.scanFile(file);
	    Collection<SourceCode> sources = scanner.getIndex().search(new QueryByType(SourceFile.class));
	    if (sources.size() != 1) {
	      throw new IllegalStateException("Only one SourceFile was expected whereas " + sources.size() + " has been returned.");
	    }
	    return (SourceFile) sources.iterator().next();
  }

  public static AstScanner<Grammar> create(SitemapConfiguration conf, SquidAstVisitor<Grammar>... visitors) {
	final SquidAstVisitorContextImpl<Grammar> context = new SquidAstVisitorContextImpl<Grammar>(new SourceProject("Sitemap Project"));;
    final Parser<Grammar> parser = SitemapParser.create(/*context, conf*/);

    AstScanner.Builder<Grammar> builder = AstScanner.<Grammar> builder(context).setBaseParser(parser);

    /* Metrics */
    builder.withMetrics(SitemapMetric.values());

    /* Comments */
    builder.setCommentAnalyser(new SitemapCommentAnalyser());

    /* Files */
    builder.setFilesMetric(SitemapMetric.FILES);

    /* Functions */
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
            .setMetricDef(SitemapMetric.FUNCTIONS)
            /*.subscribeTo(TO COMPLETE WITH THE FUNCTION RULES BY THE FINAL USER) */
            .build());

    /* Metrics */
    builder.withSquidAstVisitor(new LinesVisitor<Grammar>(SitemapMetric.LINES));
    builder.withSquidAstVisitor(new LinesOfCodeVisitor<Grammar>(SitemapMetric.LINES_OF_CODE));
    builder.withSquidAstVisitor(CommentsVisitor.<Grammar>builder().withCommentMetric(
    	      SitemapMetric.COMMENT_LINES)
    	      .withNoSonar(true)
    	      /*.withIgnoreHeaderComment(conf.getIgnoreHeaderComments())*/.build());
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
        .setMetricDef(SitemapMetric.STATEMENTS)
        /*.subscribeTo(TO COMPLETE WITH THE STATEMENT RULES BY THE FINAL USER) */
        .build());
    
    builder.withSquidAstVisitor(new SitemapFileVisitor<Grammar>(context));
    //builder.withSquidAstVisitor(new SitemapComplexityVisitor<Grammar>(context));

    for (SquidAstVisitor<Grammar> visitor : visitors) {
        if (visitor instanceof SitemapCharsetAwareVisitor) {
            ((SitemapCharsetAwareVisitor) visitor).setCharset(conf.getCharset());
          }
        builder.withSquidAstVisitor(visitor);
    }
    return builder.build();
  }
}
