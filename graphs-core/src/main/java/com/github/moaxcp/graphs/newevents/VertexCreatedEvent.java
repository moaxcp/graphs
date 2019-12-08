package com.github.moaxcp.graphs.newevents;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexCreatedEvent<K> {
  private final K graphId;
  @Singular
  private final Map<String, Object> properties;
}
