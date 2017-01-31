package org.sonar.PogoDsl.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;

import org.sonar.PogoDsl.visitors.PogoDslCharsetAwareVisitor;
import org.sonar.PogoDsl.visitors.PogoDslFileVisitor;
import org.sonar.PogoDsl.PogoDslCommentAnalyser;
import org.sonar.PogoDsl.PogoDslConfiguration;
import org.sonar.PogoDsl.parser.PogoDslParser;
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

public final class PogoDslAstScanner {

  private PogoDslAstScanner() {}

  /**
   * Helper method for testing checks without having to deploy them on a Sonar instance.
   */
  public static SourceFile scanSingleFileConfig(File file, PogoDslConfiguration PogoDslConfig, SquidAstVisitor<Grammar>... visitors) {
	    if (!file.isFile()) {
	      throw new IllegalArgumentException("File '" + file + "' not found.");
	    }
	    AstScanner<Grammar> scanner = create(PogoDslConfig, visitors);
	    scanner.scanFile(file);
	    Collection<SourceCode> sources = scanner.getIndex().search(new QueryByType(SourceFile.class));
	    if (sources.size() != 1) {
	      throw new IllegalStateException("Only one SourceFile was expected whereas " + sources.size() + " has been returned.");
	    }
	    return (SourceFile) sources.iterator().next();
  }

  public static AstScanner<Grammar> create(PogoDslConfiguration conf, SquidAstVisitor<Grammar>... visitors) {
	final SquidAstVisitorContextImpl<Grammar> context = new SquidAstVisitorContextImpl<Grammar>(new SourceProject("PogoDsl Project"));;
    final Parser<Grammar> parser = PogoDslParser.create(/*context, conf*/);

    AstScanner.Builder<Grammar> builder = AstScanner.<Grammar> builder(context).setBaseParser(parser);

    /* Metrics */
    builder.withMetrics(PogoDslMetric.values());

    /* Comments */
    builder.setCommentAnalyser(new PogoDslCommentAnalyser());

    /* Files */
    builder.setFilesMetric(PogoDslMetric.FILES);

    /* Functions */
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
            .setMetricDef(PogoDslMetric.FUNCTIONS)
            /*.subscribeTo(TO COMPLETE WITH THE FUNCTION RULES BY THE FINAL USER) */
            .build());

    /* Metrics */
    builder.withSquidAstVisitor(new LinesVisitor<Grammar>(PogoDslMetric.LINES));
    builder.withSquidAstVisitor(new LinesOfCodeVisitor<Grammar>(PogoDslMetric.LINES_OF_CODE));
    builder.withSquidAstVisitor(CommentsVisitor.<Grammar>builder().withCommentMetric(
    	      PogoDslMetric.COMMENT_LINES)
    	      .withNoSonar(true)
    	      /*.withIgnoreHeaderComment(conf.getIgnoreHeaderComments())*/.build());
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
        .setMetricDef(PogoDslMetric.STATEMENTS)
        /*.subscribeTo(TO COMPLETE WITH THE STATEMENT RULES BY THE FINAL USER) */
        .build());
    
    builder.withSquidAstVisitor(new PogoDslFileVisitor<Grammar>(context));
    //builder.withSquidAstVisitor(new PogoDslComplexityVisitor<Grammar>(context));

    for (SquidAstVisitor<Grammar> visitor : visitors) {
        if (visitor instanceof PogoDslCharsetAwareVisitor) {
            ((PogoDslCharsetAwareVisitor) visitor).setCharset(conf.getCharset());
          }
        builder.withSquidAstVisitor(visitor);
    }
    return builder.build();
  }
}
