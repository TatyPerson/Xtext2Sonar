package org.sonar.LilyPond.checks;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.sonar.LilyPond.visitors.LilyPondCharsetAwareVisitor;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.SonarException;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.checks.SquidCheck;

import com.google.common.io.Files;
import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;

@Rule(
		key = "TempoDefinedCheck",
		name = "Check that the tempo has been defined at the beginning.",
		description = "Check that the tempo has been defined at the beginning.",
		priority = Priority.MAJOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.LOGIC_RELIABILITY)
		@SqaleConstantRemediation("2min")
public class TempoDefinedCheck extends SquidCheck<Grammar> implements LilyPondCharsetAwareVisitor {
	
	private Charset charset;
	private static final String TEMPO_KEYWORD = "tempo";
	private static final String RELATIVE_KEYWORD = "relative";

	public void setCharset(Charset charset) {
	   this.charset = charset;
	}
	
	@Override
	public void visitFile(AstNode node) {
		List<String> lines;
	    try {
	      lines = Files.readLines(getContext().getFile(), charset);
	    } catch (IOException e) {
	      throw new SonarException(e); 
	    }
	    
	    boolean relativeBlock = false;
	    int numberLine = 0;
	    for (int i = 0; i < lines.size(); i++) {
	      String line = lines.get(i);
	      
	      if(line.contains(RELATIVE_KEYWORD)) {
	    	  	relativeBlock = true;
	    	  	numberLine++;
	      }
	      
	      if(!line.contains(TEMPO_KEYWORD) && numberLine == 2) {
	    	  	getContext().createLineViolation(this, "The tempo must be defined at the beginning.", i + 1);
	      }
	      
	      if(relativeBlock) {
	    	  	numberLine++;
	      }
	    }
	}
	
}
