package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgeInheritedPropertyEvent<K> {
  private final K graphId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
