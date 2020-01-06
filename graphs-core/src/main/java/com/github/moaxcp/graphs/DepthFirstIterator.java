package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;

import static java.util.Objects.*;

abstract class DepthFirstIterator<ID> extends GraphIterator<ID> {

  private final Deque<ID> stack;

  DepthFirstIterator(@NonNull Graph<ID> graph, @NonNull ID... start) {
    super(graph);
    stack = new LinkedList<>();
    for(ID id : start) {
      requireNonNull(id, "\"id\" in \"start\" must not be null.");
      if(graph.findVertex(id).isEmpty()) {
        throw new IllegalArgumentException("vertex \"" + id + "\" not found in graph.");
      }
      stack.addLast(id);
    }
  }

  Deque<ID> getStack() {
    return stack;
  }
}
