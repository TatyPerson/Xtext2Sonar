package org.sonar.VaryGrammar.checks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.checks.SquidCheck;
import org.sonar.VaryGrammar.api.VaryGrammarImpl;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;

@Rule(
		key = "MissingCommentsBeforeSubprocess",
		name = "Before each subprocess is important to have documentation explaining the functionality developed in him",
		description = "Before each subprocess is important to have documentation explaining the functionality developed in him",
		priority = Priority.MAJOR)
		@ActivatedByDefault
		@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.UNDERSTANDABILITY)
		@SqaleConstantRemediation("3min")
public class MissingCommentsBeforeSubprocess extends SquidCheck<Grammar> {
	
	private static final Logger LOG = LoggerFactory
            .getLogger("MissingCommentsBeforeSubprocess");
	
	private static final int MINIMUM_NUMBER_COMMENTS_ALLOWED = 3;

	  @RuleProperty(
	    key = "minimumNumberCommentsAllowed",
	    description = "The minimum number of comments allowed for a subprocess.",
	    defaultValue = "" + MINIMUM_NUMBER_COMMENTS_ALLOWED)
	  public int minimumNumberCommentsAllowed = MINIMUM_NUMBER_COMMENTS_ALLOWED;
	
	@Override
	  public void init() {
		  subscribeTo(VaryGrammarImpl.ALGORITMO);
		  subscribeTo(VaryGrammarImpl.MODULO);
	  }
	
	 @Override
	  public void visitFile(AstNode astNode) {
		if(astNode.getChildren().get(0).is(VaryGrammarImpl.ALGORITMO)) {
			visitFileAux(astNode.getChildren().get(0).getChildren(VaryGrammarImpl.FUNCION));
			visitFileAux(astNode.getChildren().get(0).getChildren(VaryGrammarImpl.PROCEDIMIENTO));
		}
		else if(astNode.getChildren().get(0).is(VaryGrammarImpl.MODULO)) {
			visitFileAux(astNode.getChildren().get(0).getChildren(VaryGrammarImpl.IMPLEMENTACION).get(0).getChildren(VaryGrammarImpl.FUNCION));
			visitFileAux(astNode.getChildren().get(0).getChildren(VaryGrammarImpl.IMPLEMENTACION).get(0).getChildren(VaryGrammarImpl.PROCEDIMIENTO));
		}
	  }
	 
	 private void visitFileAux(List<AstNode> children) {
		 for(AstNode nodeAux: children) {
				
				//LOG.debug("Soy el subproceso :"+nodeAux.getTokenOriginalValue());
				
				FileInputStream fis;
				try {
					fis = new FileInputStream(getContext().getFile());
				 
				//Construct BufferedReader from InputStreamReader
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			 
				String line = null;
				int numberLine = 0;
					while ((line = br.readLine()) != null) {
						numberLine= numberLine + 1;
						if((numberLine+1 == nodeAux.getTokenLine()) && line.contains("//")) {
							//Hay un comentario justo delante
							if(!subprocessWithComment(nodeAux.getTokenLine(), numberLine+1)) {
								getContext().createLineViolation(this, "Comments insufficient lines (minimum number of lines is "+ minimumNumberCommentsAllowed +").", numberLine);
							}
						} else if((numberLine+1 == nodeAux.getTokenLine()) && !line.contains("//")) {
							getContext().createLineViolation(this, "Comments insufficient lines (minimum number of lines is "+ minimumNumberCommentsAllowed +").", numberLine);
						}
					}
					
					br.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			}
	 }
	 
	 private boolean subprocessWithComment(int nodeNumberLine, int commentNumberLine) {
		 
		 FileInputStream fis;
		 String line = null;
	     int numberLine = 0;
	     int numberComment = 0;
		 int previous = 3;
		 
			try {
				fis = new FileInputStream(getContext().getFile());
			 
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		 
				while ((line = br.readLine()) != null) {
					numberLine = numberLine + 1;
					LOG.debug("numberLine="+numberLine+ " commentNumberLine - previous=" +(commentNumberLine-previous));
					if((numberLine == commentNumberLine - previous)) {
						LOG.debug("line= "+line);
					}
					if((numberLine == commentNumberLine - previous) && line.contains("//")) {
						numberComment= numberComment + 1;
						previous = previous - 1;
					}
				}
				
				br.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//LOG.debug("El numero de comentarios es: "+numberComment);
			
			if(numberComment < minimumNumberCommentsAllowed) {
				return false;
			}
			else {
				return true;
			}
	}
	
}
