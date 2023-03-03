package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.events.UndirectedGraphCreatedEvent;

public class ObservableUndirectedPropertyGraph<ID> extends ObservablePropertyGraph<ID> {

  public ObservableUndirectedPropertyGraph() {
    super();
  }

  public ObservableUndirectedPropertyGraph(ID id) {
    super(id);
  }

  @SafeVarargs
  public ObservableUndirectedPropertyGraph(PropertyGraphObserver<ID>... observers) {
    super(observers);
    send(UndirectedGraphCreatedEvent.<ID>builder().build());
  }

  @SafeVarargs
  public ObservableUndirectedPropertyGraph(ID id, PropertyGraphObserver<ID>... observers) {
    super(id, observers);
    send(UndirectedGraphCreatedEvent.<ID>builder().graphId(id).build());
  }

  @Override
  protected EdgeKey<ID> newEdgeKey(ID source, ID target) {
    return new UndirectedEdgeKey<>(source, target);
  }

  @Override
  public boolean isDirected() {
    return false;
  }
}
