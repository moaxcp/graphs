package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;

import static java.util.Objects.*;

class BreadthFirstIterator<ID> extends GraphIterator<ID> {

  private final Queue<ID> queue;
  private final List<ID> start;

  BreadthFirstIterator(Graph<ID> graph, @NonNull ID... start) {
    super(graph);
    queue = new LinkedList<>();
    this.start = new ArrayList<>();
    for(ID id : start) {
      requireNonNull(id, "\"id\" in \"start\" must not be null.");
      if(graph.findVertex(id).isEmpty()) {
        throw new IllegalArgumentException("vertex \"" + id + "\" not found in graph.");
      }
      this.start.add(id);
    }
  }

  void goToNextVertex() {
    if(getCurrentId() != null) {
      queue.addAll(getTraverseVertices(getCurrentId()));
    }

    if(queue.isEmpty() && !start.isEmpty()) {
      ID id = start.remove(0);
      if(!getVisited().contains(id)) {
        queue.add(id);
      };
    }

    if(queue.isEmpty()) {
      getStart().ifPresent(queue::add);
    }

    while(!queue.isEmpty()) {
      setCurrentId(queue.poll());
      if(!getVisited().contains(getCurrentId())) {
        getVisited().add(getCurrentId());
        return;
      }
    }

    setCurrentId(null);
  }
}
