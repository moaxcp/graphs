package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.PropertyGraph.*;
import lombok.*;

import java.util.*;

import static java.util.stream.Collectors.*;

abstract class GraphIterator<ID> implements Iterator<Vertex<ID>> {

  private final PropertyGraph<ID> graph;
  private final Set<ID> visited;
  private ID currentId;

  GraphIterator(@NonNull PropertyGraph<ID> graph) {
    this.graph = graph;
    visited = new HashSet<>();
  }

  final ID getCurrentId() {
    return currentId;
  }

  final void setCurrentId(ID currentId) {
    this.currentId = currentId;
  }

  final Set<ID> getVisited() {
    return visited;
  }

  abstract void goToNextVertex();

  final List<ID> getTraverseVertices(ID vertexId) {
    Vertex<ID> vertex = graph.findVertex(vertexId).orElseThrow();
    Set<Edge<ID>> edges;
    if(graph.isDirected()) {
      edges = vertex.outEdges();
    } else {
      edges = vertex.adjacentEdges();
    }
    return edges.stream()
      .map(e -> e.getOppositeEndpoint(vertexId))
      .filter(id -> !visited.contains(id))
      .collect(toList());
  }

  final Optional<ID> getStart() {
    return graph.getVertices().keySet().stream()
      .filter(id -> !visited.contains(id))
      .findFirst();
  }

  @Override
  final public boolean hasNext() {
    if(currentId == null) {
      goToNextVertex();
      return currentId != null;
    } else {
      return true;
    }
  }

  @Override
  final public Vertex<ID> next() {
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
