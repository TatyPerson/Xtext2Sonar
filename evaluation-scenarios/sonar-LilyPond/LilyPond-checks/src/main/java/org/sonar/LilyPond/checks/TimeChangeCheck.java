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
		key = "TimeChangeCheck",
		name = "Check that the time change has been made correctly.",
		description = "Check that the time change has been made correctly.",
		priority = Priority.MAJOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.LOGIC_RELIABILITY)
		@SqaleConstantRemediation("2min")
public class TimeChangeCheck extends SquidCheck<Grammar> implements LilyPondCharsetAwareVisitor {
	
	private Charset charset;
	private static final String TIME_4_4 = "time 4/4";
	private static final String TEMPO_KEYWORD = "tempo";

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
	    boolean time4_4 = false;
	    for (int i = 0; i < lines.size(); i++) {
	      String line = lines.get(i);
	      
	      if(line.contains(TIME_4_4)) {
	    	  	time4_4 = true;
	      } else if(line.contains(TEMPO_KEYWORD) && !line.contains("4=") && time4_4) {
	    	  	getContext().createLineViolation(this, "The time change has not been done correctly. The time is 4/4.", i + 1);
	      }
	    }
	}
}
