package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexCreatedEvent<K> {
  private final K graphId;
  @NonNull
  private final K vertexId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
