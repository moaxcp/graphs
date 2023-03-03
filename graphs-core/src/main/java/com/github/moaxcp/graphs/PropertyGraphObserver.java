package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.events.GraphEvent;

public interface PropertyGraphObserver<ID> {
  void graphEvent(GraphEvent<ID> event);
}
