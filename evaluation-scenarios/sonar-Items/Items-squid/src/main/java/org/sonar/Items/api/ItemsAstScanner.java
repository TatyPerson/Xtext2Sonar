package org.sonar.Items.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;

import org.sonar.Items.visitors.ItemsCharsetAwareVisitor;
import org.sonar.Items.visitors.ItemsFileVisitor;
import org.sonar.Items.ItemsCommentAnalyser;
import org.sonar.Items.ItemsConfiguration;
import org.sonar.Items.parser.ItemsParser;
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

public final class ItemsAstScanner {

  private ItemsAstScanner() {}

  /**
   * Helper method for testing checks without having to deploy them on a Sonar instance.
   */
  public static SourceFile scanSingleFileConfig(File file, ItemsConfiguration ItemsConfig, SquidAstVisitor<Grammar>... visitors) {
	    if (!file.isFile()) {
	      throw new IllegalArgumentException("File '" + file + "' not found.");
	    }
	    AstScanner<Grammar> scanner = create(ItemsConfig, visitors);
	    scanner.scanFile(file);
	    Collection<SourceCode> sources = scanner.getIndex().search(new QueryByType(SourceFile.class));
	    if (sources.size() != 1) {
	      throw new IllegalStateException("Only one SourceFile was expected whereas " + sources.size() + " has been returned.");
	    }
	    return (SourceFile) sources.iterator().next();
  }

  public static AstScanner<Grammar> create(ItemsConfiguration conf, SquidAstVisitor<Grammar>... visitors) {
	final SquidAstVisitorContextImpl<Grammar> context = new SquidAstVisitorContextImpl<Grammar>(new SourceProject("Items Project"));;
    final Parser<Grammar> parser = ItemsParser.create(/*context, conf*/);

    AstScanner.Builder<Grammar> builder = AstScanner.<Grammar> builder(context).setBaseParser(parser);

    /* Metrics */
    builder.withMetrics(ItemsMetric.values());

    /* Comments */
    builder.setCommentAnalyser(new ItemsCommentAnalyser());

    /* Files */
    builder.setFilesMetric(ItemsMetric.FILES);

    /* Functions */
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
            .setMetricDef(ItemsMetric.FUNCTIONS)
            /*.subscribeTo(TO COMPLETE WITH THE FUNCTION RULES BY THE FINAL USER) */
            .build());

    /* Metrics */
    builder.withSquidAstVisitor(new LinesVisitor<Grammar>(ItemsMetric.LINES));
    builder.withSquidAstVisitor(new LinesOfCodeVisitor<Grammar>(ItemsMetric.LINES_OF_CODE));
    builder.withSquidAstVisitor(CommentsVisitor.<Grammar>builder().withCommentMetric(
    	      ItemsMetric.COMMENT_LINES)
    	      .withNoSonar(true)
    	      /*.withIgnoreHeaderComment(conf.getIgnoreHeaderComments())*/.build());
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
        .setMetricDef(ItemsMetric.STATEMENTS)
        /*.subscribeTo(TO COMPLETE WITH THE STATEMENT RULES BY THE FINAL USER) */
        .build());
    
    builder.withSquidAstVisitor(new ItemsFileVisitor<Grammar>(context));
    //builder.withSquidAstVisitor(new ItemsComplexityVisitor<Grammar>(context));

    for (SquidAstVisitor<Grammar> visitor : visitors) {
        if (visitor instanceof ItemsCharsetAwareVisitor) {
            ((ItemsCharsetAwareVisitor) visitor).setCharset(conf.getCharset());
          }
        builder.withSquidAstVisitor(visitor);
    }
    return builder.build();
  }
}
