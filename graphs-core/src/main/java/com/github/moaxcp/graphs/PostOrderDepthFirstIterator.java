package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.Graph.*;
import lombok.*;

import java.util.*;

import static com.github.moaxcp.graphs.TraversalColor.*;
import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

class PostOrderDepthFirstIterator<ID> implements Iterator<Vertex<ID>> {

  @Value
  @Builder
  private static class Context<ID> {
    @NonNull
    private final ID id;
    @NonNull
    private final Iterator<ID> traverse;
  }

  private final Graph<ID> graph;
  private final List<ID> startNodes;
  private final Map<ID, TraversalColor> colors;
  private ID current;
  private final Deque<Context<ID>> stack;

  PostOrderDepthFirstIterator(@NonNull Graph<ID> graph, @NonNull ID... start) {
    this.graph = graph;
    startNodes = new ArrayList<>();
    for(ID id : start) {
      requireNonNull(id, "'id' in 'start' must not be null.");
      if(graph.findVertex(id).isEmpty()) {
        throw new IllegalArgumentException("vertex '" + id + "' not found in graph.");
      }
      startNodes.add(id);
    }
    colors = new HashMap<>();
    stack = new LinkedList<>();
  }

  private void goToNextVertex() {
    current = null;
    if(stack.isEmpty()) {
      Optional<ID> optional = getStart();
      if(optional.isEmpty()) {
        return;
      }
      ID id = optional.get();
      pushContext(id);
    }

    stackToNextBlack();
    Context<ID> context = stack.pop();
    current = context.getId();
    colors.put(current, BLACK);
  }

  private void pushContext(ID id) {
    colors.put(id, GRAY);
    Context<ID> context = Context.<ID>builder().id(id).traverse(getTraverse(id)).build();
    stack.push(context);
  }

  private void stackToNextBlack() {
    Context<ID> context = stack.peek();
    ID id = context.getId();
    Iterator<ID> traverse = context.getTraverse();
    if(!traverse.hasNext()) {
      return;
    } else {
      ID next = traverse.next();
      while(traverse.hasNext() && (colors.get(next) == GRAY || colors.get(next) == BLACK)) {
        next = traverse.next();
      }
      if(!colors.containsKey(next) || colors.get(next) == WHITE) {
        pushContext(next);
        stackToNextBlack();
      }
    }
  }

  private Set<Edge<ID>> getTraverseEdges(Vertex<ID> vertex) {
    if(graph.isDirected()) {
      return vertex.outEdges();
    } else {
      return vertex.adjacentEdges();
    }
  }

  private Iterator<ID> getTraverse(ID from) {
    Vertex<ID> vertex = graph.findVertex(from).orElseThrow();
    return getTraverseEdges(vertex).stream()
      .map(edge -> edge.getOppositeEndpoint(from))
      .collect(toList()).iterator();

  }

  private Optional<ID> getStart() {
    if(startNodes.isEmpty()) {
      return graph.getVertices().keySet().stream()
        .filter(id -> !colors.containsKey(id) || colors.get(id) == WHITE)
        .findFirst();
    }
    return Optional.of(startNodes.remove(0));
  }

  @Override
  public boolean hasNext() {
    if(current == null) {
      goToNextVertex();
      if(current == null) {
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
    if(current == null) {
      goToNextVertex();
      if(current == null) {
        throw new NoSuchElementException("Could not find next element.");
      }
    }
    var previous = current;
    goToNextVertex();
    return graph.findVertex(previous).orElseThrow(() -> new NoSuchElementException("Could not find next element: \"" + current + "\"."));
  }
}
