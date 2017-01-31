package org.sonar.Persistence.plugin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * {@inheritDoc}
 */
public class PersistenceMetrics implements Metrics {

  public static final Metric<Serializable> EXTERNAL = new Metric.Builder("Persistence-EXTERNAL", "External Persistence rules violations", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("Persistence")
      .create();
  public static final Metric<Serializable> SQUID = new Metric.Builder("Persistence-SQUID", "Persistence checks", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("Persistence")
      .create();
  public static final Metric<Serializable> DEPENDENCIES = new Metric.Builder("Persistence-DEPENDENCIES", "Cyclic dependency violations", Metric.ValueType.INT)
  	 .setDirection(Metric.DIRECTION_WORST)
  	 .setQualitative(true)
  	 .setDomain("Persistence")
  	 .create();

public List<Metric> getMetrics() {
    List<Metric> list = new ArrayList<Metric>();
    list.add(EXTERNAL);
    list.add(SQUID);
    return list;
  }
}
