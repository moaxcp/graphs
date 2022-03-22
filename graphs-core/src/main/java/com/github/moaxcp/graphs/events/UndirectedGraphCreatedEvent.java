package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class UndirectedGraphCreatedEvent<ID> implements GraphEvent<ID> {
  private final ID graphId;
}
