package com.github.moaxcp.graphs.newevents;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexCreatedEvent<K> {
  private final K graphId;
  @NonNull
  private final K vertexId;
  @Singular
  private final Map<String, Object> properties;
}
