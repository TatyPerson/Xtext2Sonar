package org.sonar.PogoDsl.checks;

import javax.annotation.CheckForNull;

import org.sonar.api.batch.rule.ActiveRules;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.checks.SquidCheck;
import com.sonar.sslr.api.Grammar;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.NoSqale;
import org.sonar.squidbridge.annotations.Tags;

/**
 * Companion of {@link org.sonar.plugins.cxx.squid.DependencyAnalyzer} which
 * actually does the job of finding duplicated includes
 */
@Rule(
  key = "CompileIncludePathNotFoundOrInvalid",
  name = "Include path used during compilation not found or invalid",
  tags = {Tags.PREPROCESSOR},
  description = "Verifies that include files used by compiler exist",
  priority = Priority.INFO)
@ActivatedByDefault
@NoSqale
public class CompileIncludePathNotFoundOrInvalid extends SquidCheck<Grammar> {

  public static final String RULE_KEY = "CompileIncludePathNotFoundOrInvalid";

  /**
   * @return null, if this check is inactive
   */
  @CheckForNull
  public static ActiveRule getActiveRule(ActiveRules rules) {
    return rules.find(RuleKey.of(CheckList.REPOSITORY_KEY, RULE_KEY));
  }

  @Override
  public String toString() {
    return RULE_KEY + " rule";
  }

}
