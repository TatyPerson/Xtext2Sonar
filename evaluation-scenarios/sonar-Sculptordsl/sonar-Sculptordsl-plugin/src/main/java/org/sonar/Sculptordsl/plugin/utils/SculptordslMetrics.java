package org.sonar.Sculptordsl.plugin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * {@inheritDoc}
 */
public class SculptordslMetrics implements Metrics {

  public static final Metric<Serializable> EXTERNAL = new Metric.Builder("Sculptordsl-EXTERNAL", "External Sculptordsl rules violations", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("Sculptordsl")
      .create();
  public static final Metric<Serializable> SQUID = new Metric.Builder("Sculptordsl-SQUID", "Sculptordsl checks", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("Sculptordsl")
      .create();
  public static final Metric<Serializable> DEPENDENCIES = new Metric.Builder("Sculptordsl-DEPENDENCIES", "Cyclic dependency violations", Metric.ValueType.INT)
  	 .setDirection(Metric.DIRECTION_WORST)
  	 .setQualitative(true)
  	 .setDomain("Sculptordsl")
  	 .create();

public List<Metric> getMetrics() {
    List<Metric> list = new ArrayList<Metric>();
    list.add(EXTERNAL);
    list.add(SQUID);
    return list;
  }
}
