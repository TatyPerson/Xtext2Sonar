package org.sonar.Sitemap.plugin;

import org.sonar.commonrules.api.CommonRulesEngine;
import org.sonar.commonrules.api.CommonRulesRepository;

public class SitemapCommonRulesEngine extends CommonRulesEngine {

  public SitemapCommonRulesEngine() {
    super(SitemapLanguage.KEY);
  }

  @Override
  protected void doEnableRules(CommonRulesRepository repository) {
	repository
    //.enableDuplicatedBlocksRule()
    .enableSkippedUnitTestsRule()
    .enableFailedUnitTestsRule()
    // null parameters -> keep default values as hardcoded in sonar-common-rules
    .enableInsufficientBranchCoverageRule(null)
    .enableInsufficientCommentDensityRule(0.20)
    .enableInsufficientLineCoverageRule(null);
	
  }
}
