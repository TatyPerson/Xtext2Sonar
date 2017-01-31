package org.sonar.PogoDsl.plugin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * {@inheritDoc}
 */
public class PogoDslMetrics implements Metrics {

  public static final Metric<Serializable> EXTERNAL = new Metric.Builder("PogoDsl-EXTERNAL", "External PogoDsl rules violations", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("PogoDsl")
      .create();
  public static final Metric<Serializable> SQUID = new Metric.Builder("PogoDsl-SQUID", "PogoDsl checks", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("PogoDsl")
      .create();
  public static final Metric<Serializable> DEPENDENCIES = new Metric.Builder("PogoDsl-DEPENDENCIES", "Cyclic dependency violations", Metric.ValueType.INT)
  	 .setDirection(Metric.DIRECTION_WORST)
  	 .setQualitative(true)
  	 .setDomain("PogoDsl")
  	 .create();

public List<Metric> getMetrics() {
    List<Metric> list = new ArrayList<Metric>();
    list.add(EXTERNAL);
    list.add(SQUID);
    return list;
  }
}
