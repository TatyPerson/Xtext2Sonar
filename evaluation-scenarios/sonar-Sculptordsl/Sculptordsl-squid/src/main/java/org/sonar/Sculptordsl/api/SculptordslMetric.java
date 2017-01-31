package org.sonar.Sculptordsl.api;

import org.sonar.squidbridge.measures.CalculatedMetricFormula;
import org.sonar.squidbridge.measures.MetricDef;

public enum SculptordslMetric implements MetricDef {
  FILES,
  LINES,
  LINES_OF_CODE,
  COMMENT_LINES,
  STATEMENTS,
  COMPLEXITY,
  FUNCTIONS,
  ACCESSORS,
  PUBLIC_API;

  public String getName() {
    return name();
  }

  public boolean isCalculatedMetric() {
    return false;
  }

  public boolean aggregateIfThereIsAlreadyAValue() {
    return true;
  }

  public boolean isThereAggregationFormula() {
    return true;
  }

  public CalculatedMetricFormula getCalculatedMetricFormula() {
    return null;
  }

}
