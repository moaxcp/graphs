package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexPropertyEvent<ID> implements GraphEvent<ID> {
  ID graphId;
  @NonNull
  ID vertexId;
  ID newId;
  @Singular
  @NonNull
  Map<String, Object> properties;
}
