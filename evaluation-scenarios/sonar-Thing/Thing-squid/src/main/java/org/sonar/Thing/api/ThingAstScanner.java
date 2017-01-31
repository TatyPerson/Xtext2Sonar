package org.sonar.Thing.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;

import org.sonar.Thing.visitors.ThingCharsetAwareVisitor;
import org.sonar.Thing.visitors.ThingFileVisitor;
import org.sonar.Thing.ThingCommentAnalyser;
import org.sonar.Thing.ThingConfiguration;
import org.sonar.Thing.parser.ThingParser;
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

public final class ThingAstScanner {

  private ThingAstScanner() {}

  /**
   * Helper method for testing checks without having to deploy them on a Sonar instance.
   */
  public static SourceFile scanSingleFileConfig(File file, ThingConfiguration ThingConfig, SquidAstVisitor<Grammar>... visitors) {
	    if (!file.isFile()) {
	      throw new IllegalArgumentException("File '" + file + "' not found.");
	    }
	    AstScanner<Grammar> scanner = create(ThingConfig, visitors);
	    scanner.scanFile(file);
	    Collection<SourceCode> sources = scanner.getIndex().search(new QueryByType(SourceFile.class));
	    if (sources.size() != 1) {
	      throw new IllegalStateException("Only one SourceFile was expected whereas " + sources.size() + " has been returned.");
	    }
	    return (SourceFile) sources.iterator().next();
  }

  public static AstScanner<Grammar> create(ThingConfiguration conf, SquidAstVisitor<Grammar>... visitors) {
	final SquidAstVisitorContextImpl<Grammar> context = new SquidAstVisitorContextImpl<Grammar>(new SourceProject("Thing Project"));;
    final Parser<Grammar> parser = ThingParser.create(/*context, conf*/);

    AstScanner.Builder<Grammar> builder = AstScanner.<Grammar> builder(context).setBaseParser(parser);

    /* Metrics */
    builder.withMetrics(ThingMetric.values());

    /* Comments */
    builder.setCommentAnalyser(new ThingCommentAnalyser());

    /* Files */
    builder.setFilesMetric(ThingMetric.FILES);

    /* Functions */
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
            .setMetricDef(ThingMetric.FUNCTIONS)
            /*.subscribeTo(TO COMPLETE WITH THE FUNCTION RULES BY THE FINAL USER) */
            .build());

    /* Metrics */
    builder.withSquidAstVisitor(new LinesVisitor<Grammar>(ThingMetric.LINES));
    builder.withSquidAstVisitor(new LinesOfCodeVisitor<Grammar>(ThingMetric.LINES_OF_CODE));
    builder.withSquidAstVisitor(CommentsVisitor.<Grammar>builder().withCommentMetric(
    	      ThingMetric.COMMENT_LINES)
    	      .withNoSonar(true)
    	      /*.withIgnoreHeaderComment(conf.getIgnoreHeaderComments())*/.build());
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
        .setMetricDef(ThingMetric.STATEMENTS)
        /*.subscribeTo(TO COMPLETE WITH THE STATEMENT RULES BY THE FINAL USER) */
        .build());
    
    builder.withSquidAstVisitor(new ThingFileVisitor<Grammar>(context));
    //builder.withSquidAstVisitor(new ThingComplexityVisitor<Grammar>(context));

    for (SquidAstVisitor<Grammar> visitor : visitors) {
        if (visitor instanceof ThingCharsetAwareVisitor) {
            ((ThingCharsetAwareVisitor) visitor).setCharset(conf.getCharset());
          }
        builder.withSquidAstVisitor(visitor);
    }
    return builder.build();
  }
}
