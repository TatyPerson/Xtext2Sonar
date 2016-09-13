package org.sonar.NAME;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;

import org.sonar.NAME.api.NAMEMetric;
import org.sonar.NAME.visitors.NAMECharsetAwareVisitor;
import org.sonar.NAME.visitors.NAMEFileVisitor;
import org.sonar.NAME.api.NAMEImpl;
import org.sonar.NAME.parser.NAMEParser;
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

public final class NAMEAstScanner {

  private NAMEAstScanner() {
  }

  /**
   * Helper method for testing checks without having to deploy them on a Sonar instance.
   */
  public static SourceFile scanSingleFileConfig(File file, NAMEConfiguration NAMEConfig, SquidAstVisitor<Grammar>... visitors) {
	    if (!file.isFile()) {
	      throw new IllegalArgumentException("File '" + file + "' not found.");
	    }
	    AstScanner<Grammar> scanner = create(NAMEConfig, visitors);
	    scanner.scanFile(file);
	    Collection<SourceCode> sources = scanner.getIndex().search(new QueryByType(SourceFile.class));
	    if (sources.size() != 1) {
	      throw new IllegalStateException("Only one SourceFile was expected whereas " + sources.size() + " has been returned.");
	    }
	    return (SourceFile) sources.iterator().next();
  }

  public static AstScanner<Grammar> create(NAMEConfiguration conf, SquidAstVisitor<Grammar>... visitors) {
	final SquidAstVisitorContextImpl<Grammar> context = new SquidAstVisitorContextImpl<Grammar>(new SourceProject("NAME Project"));;
    final Parser<Grammar> parser = NAMEParser.create(/*context, conf*/);

    AstScanner.Builder<Grammar> builder = AstScanner.<Grammar> builder(context).setBaseParser(parser);

    /* Metrics */
    builder.withMetrics(NAMEMetric.values());

    /* Comments */
    builder.setCommentAnalyser(new NAMECommentAnalyser());

    /* Files */
    builder.setFilesMetric(NAMEMetric.FILES);

    /* Functions */
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
            .setMetricDef(NAMEMetric.FUNCTIONS)
            .subscribeTo(NAMEImpl.FUNCION,
            		NAMEImpl.PROCEDIMIENTO)
            .build());

    /* Metrics */
    builder.withSquidAstVisitor(new LinesVisitor<Grammar>(NAMEMetric.LINES));
    builder.withSquidAstVisitor(new LinesOfCodeVisitor<Grammar>(NAMEMetric.LINES_OF_CODE));
    builder.withSquidAstVisitor(CommentsVisitor.<Grammar>builder().withCommentMetric(
    	      NAMEMetric.COMMENT_LINES)
    	      .withNoSonar(true)
    	      /*.withIgnoreHeaderComment(conf.getIgnoreHeaderComments())*/.build());
    builder.withSquidAstVisitor(CounterVisitor.<Grammar> builder()
        .setMetricDef(NAMEMetric.STATEMENTS)
        .subscribeTo(
        	NAMEImpl.LLAMADAFUNCION,
        	NAMEImpl.ASIGNACIONNORMAL,
        	NAMEImpl.ASIGNACIONCOMPLEJA,
        	NAMEImpl.DECLARACIONVARIABLE,
        	NAMEImpl.DECLARACIONPROPIA,
        	NAMEImpl.MIENTRAS,
        	NAMEImpl.REPETIR,
        	NAMEImpl.DESDE,
        	NAMEImpl.SI,
        	NAMEImpl.SINO,
        	NAMEImpl.SEGUN,
        	NAMEImpl.CASO,
        	NAMEImpl.ESCRIBIR,
        	NAMEImpl.LEER)
        .build());
    
    builder.withSquidAstVisitor(new NAMEFileVisitor<Grammar>(context));
    //builder.withSquidAstVisitor(new NAMEComplexityVisitor<Grammar>(context));

    for (SquidAstVisitor<Grammar> visitor : visitors) {
        if (visitor instanceof NAMECharsetAwareVisitor) {
            ((NAMECharsetAwareVisitor) visitor).setCharset(conf.getCharset());
          }
        builder.withSquidAstVisitor(visitor);
    }

    return builder.build();
  }

}