package org.sonar.Persistence.plugin.squid;

import java.util.Map;

import org.sonar.api.design.Dependency;
import org.sonar.api.resources.Resource;
import org.sonar.graph.Dsm;
import org.sonar.graph.DsmCell;
import org.sonar.graph.Edge;

/**
* Created by fferrand on 12/06/14.
*/
public class DsmSerializer {
  private Dsm<? extends Resource> dsm;
  Map<Edge, Dependency> dependencyIndex;
  private StringBuilder json;

  DsmSerializer(Dsm<? extends Resource> dsm, Map<Edge, Dependency> dependencyIndex) {
    this.dsm = dsm;
    this.dependencyIndex = dependencyIndex;
    this.json = new StringBuilder();
  }

  private String serialize() {
    json.append('[');
    serializeRows();
    json.append(']');
    return json.toString();
  }

  private void serializeRows() {
    for (int y = 0; y < dsm.getDimension(); y++) {
      if (y > 0) {
        json.append(',');
      }
      serializeRow(y);
    }
  }

  private void serializeRow(int y) {
    Resource sonarResource = dsm.getVertex(y);

    json.append("{");
    if (sonarResource != null) {
      json.append("\"i\":");
      json.append(sonarResource.getId());
      json.append(",\"n\":\"");
      json.append(sonarResource.getName());
      json.append("\",\"q\":\"");
      json.append(sonarResource.getQualifier());
      json.append("\",\"v\":[");
      for (int x = 0; x < dsm.getDimension(); x++) {
        if (x > 0) {
          json.append(',');
        }
        serializeCell(y, x);
      }
      json.append("]");
    }
    json.append("}");
  }

  private void serializeCell(int y, int x) {
    DsmCell cell = dsm.getCell(x, y);
    json.append('{');
    if (cell.getEdge() != null && cell.getWeight() > 0) {
      json.append("\"i\":");
      json.append(dependencyIndex.get(cell.getEdge()).getId());
      json.append(",\"w\":");
      json.append(cell.getWeight());
    }
    json.append('}');
  }

  public static String serialize(Dsm<? extends Resource> dsm, Map<Edge, Dependency> dependencyIndex) {
    return new DsmSerializer(dsm, dependencyIndex).serialize();
  }
}
