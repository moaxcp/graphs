package com.github.moaxcp.graphs.newevents;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexInheritedPropertyEvent<K> {
  private final K graphId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
