package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.events.DirectedGraphCreatedEvent;

public class ObservableDirectedPropertyGraph<ID> extends ObservablePropertyGraph<ID> {

  public ObservableDirectedPropertyGraph() {
    super();
  }

  public ObservableDirectedPropertyGraph(ID id) {
    super(id);
  }

  @SafeVarargs
  public ObservableDirectedPropertyGraph(PropertyGraphObserver<ID>... observers) {
    super(observers);
    send(DirectedGraphCreatedEvent.<ID>builder().build());
  }

  @SafeVarargs
  public ObservableDirectedPropertyGraph(ID id, PropertyGraphObserver<ID>... observers) {
    super(id, observers);
    send(DirectedGraphCreatedEvent.<ID>builder().graphId(id).build());
  }


  @Override
  protected EdgeKey<ID> newEdgeKey(ID from, ID to) {
    return new DirectedEdgeKey<>(from, to);
  }

  @Override
  public boolean isDirected() {
    return true;
  }
}
