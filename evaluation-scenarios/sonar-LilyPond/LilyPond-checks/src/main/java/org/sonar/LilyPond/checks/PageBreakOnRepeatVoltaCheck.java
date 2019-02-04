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
		key = "PageBreakOnRepeatVoltaCheck",
		name = "A page step cannot exist in a repeat volta instruction.",
		description = "Verify that there is no page step in a repeat volta instruction.",
		priority = Priority.MINOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.READABILITY)
		@SqaleConstantRemediation("1min")
public class PageBreakOnRepeatVoltaCheck extends SquidCheck<Grammar> implements LilyPondCharsetAwareVisitor {

	private Charset charset;
	private static final String REPEAT_VOLTA_KEYWORD = "repeat volta";
	private static final String PAGE_BREAK_KEYWORD = "pageBreak";

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
	      
	      if(line.contains(REPEAT_VOLTA_KEYWORD) && line.contains(PAGE_BREAK_KEYWORD)) {
	    	  	getContext().createLineViolation(this, "A page step cannot exist in a repeat volta instruction.", i + 1);
	      }
	    }
	}
}