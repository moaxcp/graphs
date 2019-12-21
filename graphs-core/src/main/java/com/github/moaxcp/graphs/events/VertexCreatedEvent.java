package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexCreatedEvent<ID> {
  private final ID graphId;
  @NonNull
  private final ID vertexId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
