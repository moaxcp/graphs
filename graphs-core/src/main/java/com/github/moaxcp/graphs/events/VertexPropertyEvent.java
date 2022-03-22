package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexPropertyEvent<ID> implements GraphEvent<ID> {
  private final ID graphId;
  @NonNull
  private final ID vertexId;
  private final ID newId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
