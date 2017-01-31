package org.sonar.Items.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class ItemsCommonRulesDecorator extends CommonRulesDecorator {
	public ItemsCommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(ItemsLanguage.KEY, fs, qProfile);
	  }
}
