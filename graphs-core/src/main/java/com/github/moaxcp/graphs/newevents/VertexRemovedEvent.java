package com.github.moaxcp.graphs.newevents;

import lombok.*;

@Value
@Builder
public class VertexRemovedEvent<K> {
  private final K graphId;
  @NonNull
  private final K vertexId;
}
