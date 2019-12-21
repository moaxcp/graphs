package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class DirectedGraphCreatedEvent<ID> {
  private final ID graphId;
}
