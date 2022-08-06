package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.PropertyGraph.Edge;

public record EdgeType<ID>(Edge<ID> edge, Type type) {
  public enum Type {
    TREE, BACK, FORWARD, CROSS
  }
}
