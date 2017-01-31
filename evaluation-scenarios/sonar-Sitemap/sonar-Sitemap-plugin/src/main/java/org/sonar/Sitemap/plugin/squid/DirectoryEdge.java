package org.sonar.Sitemap.plugin.squid;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.sonar.api.resources.Directory;
import org.sonar.graph.Edge;

class DirectoryEdge implements Edge<Directory> {
  private Directory from;
  private Directory to;
  private Set<FileEdge> rootEdges;

  public DirectoryEdge(Directory from, Directory to) {
    this.from = from;
    this.to = to;
    this.rootEdges = new HashSet<FileEdge>();
  }

  public void addRootEdge(FileEdge edge) {
    rootEdges.add(edge);
  }

  public Collection<FileEdge> getRootEdges() {
    return rootEdges;
  }

  public int getWeight() {
    return rootEdges.size();
  }

  public Directory getFrom() {
    return from;
  }

  public Directory getTo() {
    return to;
  }
}
