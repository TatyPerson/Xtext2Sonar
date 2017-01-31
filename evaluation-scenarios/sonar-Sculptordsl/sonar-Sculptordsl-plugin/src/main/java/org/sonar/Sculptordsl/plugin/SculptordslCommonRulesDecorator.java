package org.sonar.Sculptordsl.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class SculptordslCommonRulesDecorator extends CommonRulesDecorator {
	public SculptordslCommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(SculptordslLanguage.KEY, fs, qProfile);
	  }
}
