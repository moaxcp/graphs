package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class DirectedGraphCreatedEvent<K> {
  private final K graphId;
}
