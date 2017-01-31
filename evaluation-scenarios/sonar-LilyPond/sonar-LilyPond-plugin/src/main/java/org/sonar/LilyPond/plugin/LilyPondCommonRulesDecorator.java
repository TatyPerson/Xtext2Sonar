package org.sonar.LilyPond.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class LilyPondCommonRulesDecorator extends CommonRulesDecorator {
	public LilyPondCommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(LilyPondLanguage.KEY, fs, qProfile);
	  }
}
