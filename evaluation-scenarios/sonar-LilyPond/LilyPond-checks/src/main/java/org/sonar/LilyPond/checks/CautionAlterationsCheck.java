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
		key = "CautionAlterationsCheck",
		name = "When a note is held in the previous beat, a precautionary alteration must be included.",
		description = "When a note is held in the previous beat, a precautionary alteration must be included.",
		priority = Priority.MAJOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.READABILITY)
		@SqaleConstantRemediation("1min")
public class CautionAlterationsCheck extends SquidCheck<Grammar> implements LilyPondCharsetAwareVisitor {
	
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
	    String previousLine = "";
	    for (int i = 0; i < lines.size(); i++) {
	      String line = lines.get(i);
	      
	      String[] notes = line.split(" ");
	      
	      for(int j=0; j<notes.length; j++) {
	    	  	if(previousLine.contains(" " + notes[j]+"s ")) {
	    	  		getContext().createLineViolation(this, "When a note is held in the previous beat, a precautionary alteration must be included.", i + 1);
	    	  	}
	      }
	      
	      previousLine = line;
	    }
	}
}
