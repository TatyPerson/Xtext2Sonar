package org.sonar.LilyPond.checks;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.LilyPond.api.LilyPondImpl;
import org.sonar.LilyPond.api.LilyPondTokenType;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.checks.SquidCheck;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;

@Rule(
		key = "TitleIsDefinedCheck",
		name = "The music sheet title must be defined.",
		description = "Verifies that the music sheet title have been defined.",
		priority = Priority.MAJOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.READABILITY)
		@SqaleConstantRemediation("2min")
public class TitleIsDefinedCheck extends SquidCheck<Grammar> {
	
	private static final Logger LOG = LoggerFactory.getLogger("TitleIsDefinedCheck");
	private static final String TITLE_KEYWORD = "title";
	private static final String HEADER_TYPE = "HEADER";
	private List<String> attributes = new ArrayList<String>();
	private int headerLine;
	
	@Override
	public void init() {
		//title = "" (format)
		subscribeTo(LilyPondImpl.BLOCKCOMMAND);
		subscribeTo(LilyPondImpl.ASSIGNMENT);
	}
	
	@Override
	public void visitFile(AstNode node) {
		attributes.clear();
	}
	
	@Override
	public void visitNode(AstNode node) {
		//Get \header line
		
		for(AstNode child: node.getChildren()) {
			if(child.getName().equals(HEADER_TYPE)) {
				headerLine = node.getTokenLine();
			}
		}
		
		for(AstNode identifier: node.getChildren(LilyPondImpl.SCHEMEIDENTIFIER)) {
			for(AstNode id: identifier.getChildren(LilyPondTokenType.ID)) {
				attributes.add(id.getTokenValue());
			}
		}
	}
	
	@Override
	public void leaveFile(AstNode node) {
		if(!attributes.contains(TITLE_KEYWORD)) {
			getContext().createLineViolation(this, " The music sheet title must have been defined.", headerLine);
		}
	}
}