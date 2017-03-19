package org.sonar.VaryGrammar.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class VaryGrammarCommonRulesDecorator extends CommonRulesDecorator {
	public VaryGrammarCommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(VaryGrammarLanguage.KEY, fs, qProfile);
	  }
}
