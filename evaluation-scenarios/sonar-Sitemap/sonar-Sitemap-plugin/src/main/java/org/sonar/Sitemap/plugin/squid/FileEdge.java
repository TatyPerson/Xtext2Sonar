package org.sonar.Sitemap.plugin.squid;

import org.sonar.api.resources.File;
import org.sonar.graph.Edge;

class FileEdge implements Edge<File> {
  private final File from;
  private final File to;
  private final int line;

  public FileEdge(File from, File to, int line) {
    this.from = from;
    this.to = to;
    this.line = line;
  }

  public int getWeight() {
    return 1;
  }

  public File getFrom() {
    return from;
  }

  public File getTo() {
    return to;
  }

  public int getLine() {
    return line;
  }
}
