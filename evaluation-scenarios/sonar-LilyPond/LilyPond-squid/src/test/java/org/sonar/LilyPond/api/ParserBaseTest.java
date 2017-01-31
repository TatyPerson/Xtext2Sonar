package org.sonar.LilyPond.api;

import org.sonar.LilyPond.LilyPondConfiguration;
import org.sonar.LilyPond.parser.LilyPondParser;
import org.sonar.squidbridge.SquidAstVisitorContext;
import static org.mockito.Mockito.mock;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;

public class ParserBaseTest {
	  protected LilyPondConfiguration conf = null;
	  protected Parser<Grammar> p = null;
	  protected Grammar g = null;

	  public ParserBaseTest(){
	    conf = new LilyPondConfiguration();
	    conf.setErrorRecoveryEnabled(false);
	    p = LilyPondParser.create(mock(SquidAstVisitorContext.class), conf);
	    g = p.getGrammar();
	  }
}
