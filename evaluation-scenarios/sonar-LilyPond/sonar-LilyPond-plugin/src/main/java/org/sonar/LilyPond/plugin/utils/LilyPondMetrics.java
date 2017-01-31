package org.sonar.LilyPond.plugin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * {@inheritDoc}
 */
public class LilyPondMetrics implements Metrics {

  public static final Metric<Serializable> EXTERNAL = new Metric.Builder("LilyPond-EXTERNAL", "External LilyPond rules violations", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("LilyPond")
      .create();
  public static final Metric<Serializable> SQUID = new Metric.Builder("LilyPond-SQUID", "LilyPond checks", Metric.ValueType.INT)
      .setDirection(Metric.DIRECTION_WORST)
      .setQualitative(true)
      .setDomain("LilyPond")
      .create();
  public static final Metric<Serializable> DEPENDENCIES = new Metric.Builder("LilyPond-DEPENDENCIES", "Cyclic dependency violations", Metric.ValueType.INT)
  	 .setDirection(Metric.DIRECTION_WORST)
  	 .setQualitative(true)
  	 .setDomain("LilyPond")
  	 .create();

public List<Metric> getMetrics() {
    List<Metric> list = new ArrayList<Metric>();
    list.add(EXTERNAL);
    list.add(SQUID);
    return list;
  }
}
