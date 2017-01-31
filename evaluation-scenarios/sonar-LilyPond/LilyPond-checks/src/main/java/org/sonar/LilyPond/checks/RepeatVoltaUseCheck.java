package org.sonar.LilyPond.checks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.LilyPond.api.LilyPondImpl;
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
		key = "RepeatVoltaUseCheck",
		name = "The 'Repeat Volta' block must have be used instead of individual repeated sentences.",
		description = "Verifies that 'Repeat Volta' block is used correctly.",
		priority = Priority.MAJOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.LOGIC_RELIABILITY)
		@SqaleConstantRemediation("2min")
public class RepeatVoltaUseCheck extends SquidCheck<Grammar> {
	
	private static final Logger LOG = LoggerFactory.getLogger("RepeatVoltaUseCheck");
	private Map<Integer, List<String>> notesPerLine = new HashMap<Integer, List<String>>();
	private List<Integer> lines = new ArrayList<Integer>();
	
	@Override
	public void init() {
		subscribeTo(LilyPondImpl.RELATIVEMUSIC);
	}
	
	@Override
	public void visitFile(AstNode node) {
		notesPerLine.clear();
		lines.clear();
	}
	
	@Override
	public void visitNode(AstNode node) {
		for(AstNode nodeChild: node.getFirstChild(LilyPondImpl.SIMPLEBLOCK).getChildren(LilyPondImpl.SCHEMEIDENTIFIER)) {
			  visitOccurence(nodeChild.getTokenValue(), nodeChild.getTokenLine());
		}
	}
	
	@Override
	public void leaveFile(AstNode node) {
		int countRepetitions = 0;
		int startRepetitionLine = 0;
		
		for(Integer line: lines) {
			if(notesPerLine.get(line).equals(notesPerLine.get(line+1))) {
				if(countRepetitions == 0) {
					startRepetitionLine = line;
				}
				countRepetitions = countRepetitions + 1;
				LOG.debug("repeticiones = "+countRepetitions);
			} else {
				if(countRepetitions != 0) {
					 getContext().createLineViolation(this, "You must use the 'Repeat Volta' block instead of repeated individuals blocks", startRepetitionLine);
				}
				
				countRepetitions = 0;
				startRepetitionLine = 0;
			}
		}
	}
	
	private void visitOccurence(String literal, int line) {
		if(!notesPerLine.containsKey(line)) {
			notesPerLine.put(line, new ArrayList<String>());
			notesPerLine.get(line).add(literal);
		} else {
			notesPerLine.get(line).add(literal);
		}
		  
		lines.add(line);
	}
}
