package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class DirectedGraphCreatedEvent<ID> implements GraphEvent<ID> {
  ID graphId;
}
