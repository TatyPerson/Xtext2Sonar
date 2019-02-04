package org.sonar.LilyPond.checks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.LilyPond.api.LilyPondImpl;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.SonarException;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.checks.SquidCheck;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;

@Rule(
		key = "ClosedMusicSheetCheck",
		name = "Check that the Music Sheet has been closed correctly.",
		description = "Check that the Music Sheet has been closed correctly.",
		priority = Priority.MAJOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.LOGIC_RELIABILITY)
		@SqaleConstantRemediation("1min")
public class ClosedMusicSheetCheck extends SquidCheck<Grammar> {
	
	private static final Logger LOG = LoggerFactory.getLogger("ClosedMusicSheetCheck");
	private final String END_BAR_EXPRESSION = "\\bar \"|.\"";
	
	@Override
	public void init() {
		subscribeTo(LilyPondImpl.RELATIVEMUSIC);
	}
	
	
	@Override
	  public void visitNode(AstNode node) {
		if(!node.getLastChild().getTokenValue().contains(END_BAR_EXPRESSION)) {
			getContext().createLineViolation(this, "The music sheet must be closed correctly.", node.getTokenLine());
		}
	}
}