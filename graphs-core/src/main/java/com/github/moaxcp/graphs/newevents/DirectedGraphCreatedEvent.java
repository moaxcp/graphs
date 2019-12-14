package com.github.moaxcp.graphs.newevents;

import lombok.*;

@Value
@Builder
public class DirectedGraphCreatedEvent<K> {
  private final K graphId;
}
