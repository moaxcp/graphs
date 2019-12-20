package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class VertexRemovedEvent<K> {
  private final K graphId;
  @NonNull
  private final K vertexId;
}
