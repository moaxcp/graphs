package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class UndirectedGraphCreatedEvent<K> {
  private final K graphId;
}
