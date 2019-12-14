package com.github.moaxcp.graphs.newevents;

import lombok.*;

@Value
@Builder
public class UndirectedGraphCreatedEvent<K> {
  private final K graphId;
}
