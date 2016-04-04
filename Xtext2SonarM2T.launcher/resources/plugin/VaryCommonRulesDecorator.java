package org.sonar.NAME.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class NAMECommonRulesDecorator extends CommonRulesDecorator {
	public NAMECommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(NAMELanguage.KEY, fs, qProfile);
	  }
}
