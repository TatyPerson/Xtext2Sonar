package org.sonar.VaryGrammar.plugin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * {@inheritDoc}
 */
public class VaryGrammarMetrics implements Metrics {

  public static final Metric<Serializable> EXTERNAL = new Metric.Builder("VaryGrammar-EXTERNAL", "External VaryGrammar rules violations", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("VaryGrammar")
      .create();
  public static final Metric<Serializable> SQUID = new Metric.Builder("VaryGrammar-SQUID", "VaryGrammar checks", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("VaryGrammar")
      .create();
  public static final Metric<Serializable> DEPENDENCIES = new Metric.Builder("VaryGrammar-DEPENDENCIES", "Cyclic dependency violations", Metric.ValueType.INT)
  	 .setDirection(Metric.DIRECTION_WORST)
  	 .setQualitative(true)
  	 .setDomain("VaryGrammar")
  	 .create();

public List<Metric> getMetrics() {
    List<Metric> list = new ArrayList<Metric>();
    list.add(EXTERNAL);
    list.add(SQUID);
    return list;
  }
}
