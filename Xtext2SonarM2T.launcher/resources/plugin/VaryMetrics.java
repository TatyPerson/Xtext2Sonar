package org.sonar.NAME.plugin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * {@inheritDoc}
 */
public class NAMEMetrics implements Metrics {

  public static final Metric<Serializable> EXTERNAL = new Metric.Builder("NAME-EXTERNAL", "External NAME rules violations", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("NAME")
      .create();
  public static final Metric<Serializable> SQUID = new Metric.Builder("NAME-SQUID", "NAME checks", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("NAME")
      .create();
  public static final Metric<Serializable> DEPENDENCIES = new Metric.Builder("NAME-DEPENDENCIES", "Cyclic dependency violations", Metric.ValueType.INT)
  	 .setDirection(Metric.DIRECTION_WORST)
  	 .setQualitative(true)
  	 .setDomain("NAME")
  	 .create();

public List<Metric> getMetrics() {
    List<Metric> list = new ArrayList<Metric>();
    list.add(EXTERNAL);
    list.add(SQUID);
    return list;
  }
}
