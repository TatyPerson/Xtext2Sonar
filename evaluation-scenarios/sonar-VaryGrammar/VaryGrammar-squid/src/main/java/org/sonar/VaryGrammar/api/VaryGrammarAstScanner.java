package org.sonar.VaryGrammar.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;

import org.sonar.VaryGrammar.visitors.VaryGrammarCharsetAwareVisitor;
import org.sonar.VaryGrammar.visitors.VaryGrammarFileVisitor;
import org.sonar.VaryGrammar.VaryGrammarCommentAnalyser;
import org.sonar.VaryGrammar.VaryGrammarConfiguration;
import org.sonar.VaryGrammar.parser.VaryGrammarParser;
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

public final class VaryGrammarAstScanner {

  private VaryGrammarAstScanner() {}

  /**
   * Helper method for testing checks without having to deploy them on a Sonar instance.
   */
  public static SourceFile scanSingleFileConfig(File file, VaryGrammarConfiguration VaryGrammarConfig, SquidAstVisitor<Grammar>... visitors) {
	    if (!file.isFile()) {
	      throw new IllegalArgumentException("File '" + file + "' not found.");
	    }
	    AstScanner<Grammar> scanner = create(VaryGrammarConfig, visitors);
	    scanner.scanFile(file);
	    Collection<SourceCode> sources = scanner.getIndex().search(new QueryByType(SourceFile.class));
	    if (sources.size() != 1) {
	      throw new IllegalStateException("Only one SourceFile was expected whereas " + sources.size() + " has been returned.");
	    }
	    return (SourceFile) sources.iterator().next();
  }

  public static AstScanner<Grammar> create(VaryGrammarConfiguration conf, SquidAstVisitor<Grammar>... visitors) {
	final SquidAstVisitorContextImpl<Grammar> context = new SquidAstVisitorContextImpl<Grammar>(new SourceProject("VaryGrammar Project"));;
    final Parser<Grammar> parser = VaryGrammarParser.create(/*context, conf*/);

    AstScanner.Builder<Grammar> builder = AstScanner.<Grammar> builder(context).setBaseParser(parser);

    /* Metrics */
    builder.withMetrics(VaryGrammarMetric.values());

    /* Comments */
    builder.setCommentAnalyser(new VaryGrammarCommentAnalyser());

    /* Files */
    builder.setFilesMetric(VaryGrammarMetric.FILES);

    /* Functions */
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
            .setMetricDef(VaryGrammarMetric.FUNCTIONS)
            /*.subscribeTo(TO COMPLETE WITH THE FUNCTION RULES BY THE FINAL USER) */
            .build());

    /* Metrics */
    builder.withSquidAstVisitor(new LinesVisitor<Grammar>(VaryGrammarMetric.LINES));
    builder.withSquidAstVisitor(new LinesOfCodeVisitor<Grammar>(VaryGrammarMetric.LINES_OF_CODE));
    builder.withSquidAstVisitor(CommentsVisitor.<Grammar>builder().withCommentMetric(
    	      VaryGrammarMetric.COMMENT_LINES)
    	      .withNoSonar(true)
    	      /*.withIgnoreHeaderComment(conf.getIgnoreHeaderComments())*/.build());
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
        .setMetricDef(VaryGrammarMetric.STATEMENTS)
        /*.subscribeTo(TO COMPLETE WITH THE STATEMENT RULES BY THE FINAL USER) */
        .build());
    
    builder.withSquidAstVisitor(new VaryGrammarFileVisitor<Grammar>(context));
    //builder.withSquidAstVisitor(new VaryGrammarComplexityVisitor<Grammar>(context));

    for (SquidAstVisitor<Grammar> visitor : visitors) {
        if (visitor instanceof VaryGrammarCharsetAwareVisitor) {
            ((VaryGrammarCharsetAwareVisitor) visitor).setCharset(conf.getCharset());
          }
        builder.withSquidAstVisitor(visitor);
    }
    return builder.build();
  }
}
