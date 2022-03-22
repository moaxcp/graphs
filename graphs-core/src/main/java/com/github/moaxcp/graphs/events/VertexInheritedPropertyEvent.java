package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexInheritedPropertyEvent<ID> implements GraphEvent<ID> {
  private final ID graphId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
