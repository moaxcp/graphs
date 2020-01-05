package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.Graph.*;
import lombok.*;

import java.util.*;

import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

class PreOrderDepthFirstIterator<ID> implements Iterator<Vertex<ID>> {

  private final Graph<ID> graph;
  private final Set<ID> visited;
  private final Deque<ID> stack;
  private ID currentId;

  PreOrderDepthFirstIterator(@NonNull Graph<ID> graph, @NonNull ID... start) {
    this.graph = graph;
    visited = new HashSet<>();
    stack = new LinkedList<>();
    for(ID id : start) {
      requireNonNull(id, "'id' in 'start' must not be null.");
      if(graph.findVertex(id).isEmpty()) {
        throw new IllegalArgumentException("vertex '" + id + "' not found in graph.");
      }
      stack.addLast(id);
    }
  }

  private void goToNextVertex() {
    if(currentId != null) {
      List<ID> traverse = getTraverseEdges();
      //add to stack in reverse to maintain insertion order for Linked sets
      for(int i = traverse.size() - 1; i >= 0; i--) {
        stack.push(traverse.get(i));
      }
    }

    if(stack.isEmpty()) {
      getStart().ifPresent(stack::push);
    }

    while(!stack.isEmpty()) {
      currentId = stack.pop();
      if(!visited.contains(currentId)) {
        visited.add(currentId);
        return;
      }
    }
    currentId = null;
  }

  private List<ID> getTraverseEdges() {
    Vertex<ID> vertex = graph.findVertex(currentId).orElseThrow();
    Set<Edge<ID>> edges;
    if(graph.isDirected()) {
      edges = vertex.outEdges();
    } else {
      edges = vertex.adjacentEdges();
    }
    return edges.stream()
      .map(e -> e.getOppositeEndpoint(currentId))
      .filter(id -> !visited.contains(id))
      .collect(toList());
  }

  private Optional<ID> getStart() {
    return graph.getVertices().keySet().stream()
      .filter(id -> !visited.contains(id))
      .findFirst();
  }

  @Override
  public boolean hasNext() {
    if(currentId == null) {
      goToNextVertex();
      if(currentId == null) {
        return false;
      } else {
        return true;
      }
    } else {
      return true;
    }
  }

  @Override
  public Vertex<ID> next() {
    if(currentId == null) {
      goToNextVertex();
      if(currentId == null) {
        throw new NoSuchElementException("Could not find next element.");
      }
    }
    var previous = currentId;
    goToNextVertex();
    return graph.findVertex(previous).orElseThrow(() -> new NoSuchElementException("Could not find next element: \"" + currentId + "\"."));
  }
}
