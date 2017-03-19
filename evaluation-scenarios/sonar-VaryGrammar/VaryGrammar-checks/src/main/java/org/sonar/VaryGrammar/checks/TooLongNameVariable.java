package org.sonar.VaryGrammar.checks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.checks.SquidCheck;
import org.sonar.VaryGrammar.api.VaryGrammarImpl;
import org.sonar.check.Priority;
import org.sonar.api.server.rule.RulesDefinition;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;

@Rule(
		key = "TooLongNameVariable",
		name = "The variables names should not be too longs and should be sufficiently descriptives",
		description = "The variables names should not be too longs and should be sufficiently descriptives",
		priority = Priority.MINOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
		@SqaleConstantRemediation("2min")
public class TooLongNameVariable extends SquidCheck<Grammar> {
	
	private static final Logger LOG = LoggerFactory
            .getLogger("TooLongNameVariable");
	
	private static final int MAXIMUM_NAME_LENGTH = 12;
	
	private final Map<String, Integer> variablesLenght = new HashMap<String, Integer>();
	private final List<String> variablesName = new ArrayList<String>();
	
	@RuleProperty(
		    key = "maximumNameLenght",
		    description = "The maximum length allowed for the name of a variable.",
		    defaultValue = "" + MAXIMUM_NAME_LENGTH)
		  public int maximumLenghtAllowed = MAXIMUM_NAME_LENGTH;
	
	@Override
	  public void init() {
		  subscribeTo(VaryGrammarImpl.DECLARACIONPROPIA);
		  subscribeTo(VaryGrammarImpl.DECLARACIONVARIABLE);
	  }
	  
	  @Override
	  public void visitFile(AstNode node) {
		variablesLenght.clear();
		variablesName.clear();
	  }
	  
	  @Override
	  public void visitNode(AstNode node) {
		 visitOccurence(node.getFirstChild(VaryGrammarImpl.VARIABLE).getTokenValue(), node.getTokenLine());
	  }
	  
	  @Override
	  public void leaveFile(AstNode node) {
		  for(String name: variablesName) {
			  getContext().createLineViolation(this, "The name of the variable " + name + " is too long. The length of the name is " + name.length() + " characters and the maximum is " + maximumLenghtAllowed +".", variablesLenght.get(name));
		  }
	  }
	  
	  private void visitOccurence(String literal, int line) {
		  if(literal.length() > maximumLenghtAllowed) {
			  variablesLenght.put(literal, line);
			  variablesName.add(literal);
		  }
		  LOG.debug("El nombre de la variable que se va a registrar es: "+literal);
	  }
	
}
