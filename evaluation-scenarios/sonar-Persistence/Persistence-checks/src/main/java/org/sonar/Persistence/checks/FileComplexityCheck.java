package org.sonar.Persistence.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.Persistence.api.PersistenceMetric;
import org.sonar.squidbridge.checks.AbstractFileComplexityCheck;
import org.sonar.squidbridge.measures.MetricDef;
import com.sonar.sslr.api.Grammar;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleLinearWithOffsetRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;
import org.sonar.squidbridge.annotations.Tags;

@Rule(
  key = "FileComplexity",
  name = "Files should not be too complex",
  tags = {Tags.BRAIN_OVERLOAD},
  description = "Verifies that the file not be too complex.",
  priority = Priority.MAJOR)
//@ActivatedByDefault
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.UNIT_TESTABILITY)
@SqaleLinearWithOffsetRemediation(
  coeff = "1min",
  offset = "30min",
  effortToFixDescription = "per complexity point above the threshold")
public class FileComplexityCheck extends AbstractFileComplexityCheck<Grammar> {
  private static final int DEFAULT_MAX = 200;

  @RuleProperty(
    key = "max",
    description = "Maximum complexity allowed",
    defaultValue = "" + DEFAULT_MAX)
  private int max = DEFAULT_MAX;


  public void setMax(int max) {
    this.max = max;
  }


  @Override
  public int getMaximumFileComplexity() {
    return this.max;
  }


  @Override
  public MetricDef getComplexityMetric() {
    return PersistenceMetric.COMPLEXITY;
  }

}
