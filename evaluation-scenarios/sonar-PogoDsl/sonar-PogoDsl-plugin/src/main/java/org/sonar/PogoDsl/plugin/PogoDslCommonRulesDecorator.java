package org.sonar.PogoDsl.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class PogoDslCommonRulesDecorator extends CommonRulesDecorator {
	public PogoDslCommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(PogoDslLanguage.KEY, fs, qProfile);
	  }
}
