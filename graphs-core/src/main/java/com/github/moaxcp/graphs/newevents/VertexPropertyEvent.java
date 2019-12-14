package com.github.moaxcp.graphs.newevents;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexPropertyEvent<K> {
  private final K graphId;
  @NonNull
  private final K vertexId;
  private final K newId;
  @Singular
  private final Map<String, Object> properties;
}
