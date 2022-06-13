package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexCreatedEvent<ID> implements GraphEvent<ID> {
  ID graphId;
  @NonNull
  ID vertexId;
  @Singular
  @NonNull
  Map<String, Object> properties;
}
