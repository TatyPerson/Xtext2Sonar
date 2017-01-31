package org.sonar.Sitemap.plugin;

import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.resources.ProjectFileSystem;
import org.sonar.commonrules.api.CommonRulesDecorator;

public class SitemapCommonRulesDecorator extends CommonRulesDecorator {
	public SitemapCommonRulesDecorator(ProjectFileSystem fs, RulesProfile qProfile) {
	    super(SitemapLanguage.KEY, fs, qProfile);
	  }
}
