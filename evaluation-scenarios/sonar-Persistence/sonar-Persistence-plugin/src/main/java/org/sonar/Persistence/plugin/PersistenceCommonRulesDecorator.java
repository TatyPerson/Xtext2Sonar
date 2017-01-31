package org.sonar.Persistence.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class PersistenceCommonRulesDecorator extends CommonRulesDecorator {
	public PersistenceCommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(PersistenceLanguage.KEY, fs, qProfile);
	  }
}
