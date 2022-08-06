package com.github.moaxcp.graphs;

import java.util.*;
import lombok.NonNull;

import static java.util.Objects.requireNonNull;

public class EdgeTypeIterator<ID> implements Iterator<EdgeType<ID>> {
  private final PropertyGraph<ID> graph;
  private final Set<ID> visited = new HashSet<>();
  private ID currentId;
  private final Deque<ID> stack;

  public EdgeTypeIterator(PropertyGraph<ID> graph, @NonNull ID... start) {
    this.graph = graph;
    stack = new LinkedList<>();
    for(ID id : start) {
      requireNonNull(id, "\"id\" in \"start\" must not be null.");
      if(graph.findVertex(id).isEmpty()) {
        throw new IllegalArgumentException("vertex \"" + id + "\" not found in graph.");
      }
      stack.addLast(id);
    }
  }

  @Override
  public boolean hasNext() {
    if(!graph.isEmpty()) {
      return true;
    }
    return false;
  }

  @Override
  public EdgeType<ID> next() {
    throw new NoSuchElementException("Could not find element.");
  }

}
