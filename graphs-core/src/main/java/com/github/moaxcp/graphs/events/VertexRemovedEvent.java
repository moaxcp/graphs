package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class VertexRemovedEvent<ID> implements GraphEvent<ID> {
  private final ID graphId;
  @NonNull
  private final ID vertexId;
}
