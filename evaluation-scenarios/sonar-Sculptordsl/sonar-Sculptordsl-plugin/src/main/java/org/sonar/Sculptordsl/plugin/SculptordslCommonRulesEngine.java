package org.sonar.Sculptordsl.plugin;

import org.sonar.commonrules.api.CommonRulesEngine;
import org.sonar.commonrules.api.CommonRulesRepository;

public class SculptordslCommonRulesEngine extends CommonRulesEngine {

  public SculptordslCommonRulesEngine() {
    super(SculptordslLanguage.KEY);
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
