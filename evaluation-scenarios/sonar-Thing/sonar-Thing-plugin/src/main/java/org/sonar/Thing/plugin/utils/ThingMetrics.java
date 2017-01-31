package org.sonar.Thing.plugin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * {@inheritDoc}
 */
public class ThingMetrics implements Metrics {

  public static final Metric<Serializable> EXTERNAL = new Metric.Builder("Thing-EXTERNAL", "External Thing rules violations", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("Thing")
      .create();
  public static final Metric<Serializable> SQUID = new Metric.Builder("Thing-SQUID", "Thing checks", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("Thing")
      .create();
  public static final Metric<Serializable> DEPENDENCIES = new Metric.Builder("Thing-DEPENDENCIES", "Cyclic dependency violations", Metric.ValueType.INT)
  	 .setDirection(Metric.DIRECTION_WORST)
  	 .setQualitative(true)
  	 .setDomain("Thing")
  	 .create();

public List<Metric> getMetrics() {
    List<Metric> list = new ArrayList<Metric>();
    list.add(EXTERNAL);
    list.add(SQUID);
    return list;
  }
}
