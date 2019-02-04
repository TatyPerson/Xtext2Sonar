package org.sonar.LilyPond.checks;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		key = "RepeatBeatsOfSilence",
		name = "There are no consecutive silent beats.",
		description = "Verifies that there are no consecutive silent beats.",
		priority = Priority.MAJOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.LOGIC_RELIABILITY)
		@SqaleConstantRemediation("1min")
public class RepeatBeatsOfSilenceCheck extends SquidCheck<Grammar> implements LilyPondCharsetAwareVisitor {
	
	private Charset charset;

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
	    for (int i = 0; i < lines.size(); i++) {
	      String line = lines.get(i);
	      int count = line.length() - line.replace("R1*1", "").length();
	      if((count/4) > 1) {
	    	  	getContext().createLineViolation(this, "Do not repeat several beat in silence (use R1* number of repetitions).", i + 1);
	      }
	    }
	}	
}