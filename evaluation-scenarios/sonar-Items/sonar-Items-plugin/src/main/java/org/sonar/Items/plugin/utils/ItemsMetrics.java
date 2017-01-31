package org.sonar.Items.plugin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * {@inheritDoc}
 */
public class ItemsMetrics implements Metrics {

  public static final Metric<Serializable> EXTERNAL = new Metric.Builder("Items-EXTERNAL", "External Items rules violations", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("Items")
      .create();
  public static final Metric<Serializable> SQUID = new Metric.Builder("Items-SQUID", "Items checks", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("Items")
      .create();
  public static final Metric<Serializable> DEPENDENCIES = new Metric.Builder("Items-DEPENDENCIES", "Cyclic dependency violations", Metric.ValueType.INT)
  	 .setDirection(Metric.DIRECTION_WORST)
  	 .setQualitative(true)
  	 .setDomain("Items")
  	 .create();

public List<Metric> getMetrics() {
    List<Metric> list = new ArrayList<Metric>();
    list.add(EXTERNAL);
    list.add(SQUID);
    return list;
  }
}
