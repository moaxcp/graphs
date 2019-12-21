package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgeInheritedPropertyEvent<ID> {
  private final ID graphId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
