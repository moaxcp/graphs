package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.PropertyGraphObserver;
import com.github.moaxcp.graphs.events.GraphEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Observer<ID> implements PropertyGraphObserver<ID> {
  private List<GraphEvent<ID>> events = new ArrayList<>();

  public List<GraphEvent<ID>> getEvents() {
    return Collections.unmodifiableList(events);
  }

  @Override
  public void graphEvent(GraphEvent<ID> event) {
    events.add(event);
  }
}
