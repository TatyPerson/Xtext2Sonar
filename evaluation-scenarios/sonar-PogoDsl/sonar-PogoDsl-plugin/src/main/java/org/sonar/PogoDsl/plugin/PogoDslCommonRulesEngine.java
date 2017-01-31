package org.sonar.PogoDsl.plugin;

import org.sonar.commonrules.api.CommonRulesEngine;
import org.sonar.commonrules.api.CommonRulesRepository;

public class PogoDslCommonRulesEngine extends CommonRulesEngine {

  public PogoDslCommonRulesEngine() {
    super(PogoDslLanguage.KEY);
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
