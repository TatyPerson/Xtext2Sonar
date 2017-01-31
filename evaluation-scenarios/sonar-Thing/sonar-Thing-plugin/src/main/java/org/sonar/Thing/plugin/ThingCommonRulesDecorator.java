package org.sonar.Thing.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class ThingCommonRulesDecorator extends CommonRulesDecorator {
	public ThingCommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(ThingLanguage.KEY, fs, qProfile);
	  }
}
