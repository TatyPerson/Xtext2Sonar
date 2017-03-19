package org.sonar.VaryGrammar.checks;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.checks.SquidCheck;
import org.sonar.check.Priority;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.VaryGrammar.api.VaryGrammarImpl;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;

@Rule(
		  key = "TooManyGlobalVariables",
		  name = "The number of global variables used should be minimal. Instead, local variables should be used.",
		  description = "The number of global variables used should be minimal. Instead, local variables should be used.",
		  priority = Priority.MAJOR)
		  @ActivatedByDefault
		  @SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
		  @SqaleConstantRemediation("5min")
public class TooManyGlobalVariables extends SquidCheck<Grammar> {
	
	private static final Logger LOG = LoggerFactory
            .getLogger("TooManyGlobalVariables");
	
	private static final int MAXIMUM_OCCURRENCE_ALLOWED = 5;
	
	private final List<Integer> globalOccurrences = new ArrayList<Integer>();

	  @RuleProperty(
	    key = "maximunOcurrenceAllowed",
	    description = "The maximum number allowed of occurrences of global variables.",
	    defaultValue = "" + MAXIMUM_OCCURRENCE_ALLOWED)
	  public int maximumOccurrenceAllowed = MAXIMUM_OCCURRENCE_ALLOWED;
	  
	  
	  @Override
	  public void init() {
		  subscribeTo(VaryGrammarImpl.DECLARACION);
	     //LOG.debug("Se ha suscrito el tipo de nodo a buscar, " +globalOccurrences.size());
	  }
	  
	  @Override
	  public void visitFile(AstNode node) {
		  globalOccurrences.clear();
	     //LOG.debug("La variable se ha puesto a 0!, " +globalOccurrences.size());
	  }
	  
	  @Override
	  public void visitNode(AstNode node) {
			visitOccurence(node.getTokenOriginalValue(), node.getTokenLine());
			//LOG.debug("Se estan intentando visitar los nodos!, "+globalOccurrences.size());
	  }
	  
	  @Override
	  public void leaveFile(AstNode node) {
		if(globalOccurrences.size() > maximumOccurrenceAllowed) {
			getContext().createLineViolation(this, "Too many global variables (" +globalOccurrences.size() + " global variables have been defined and the maximun is " + maximumOccurrenceAllowed +"). Define a local variable instead of a global variable.", globalOccurrences.get(0));
		}
		//LOG.debug("Se esta intentnado poner el error!, "+globalOccurrences.size());
	  }
	  
	  private void visitOccurence(String literal, int line) {
		  globalOccurrences.add(line);
		  //LOG.debug("Se esta incrementando el número de declaraciones!, "+globalOccurrences.size());
	  }
}
