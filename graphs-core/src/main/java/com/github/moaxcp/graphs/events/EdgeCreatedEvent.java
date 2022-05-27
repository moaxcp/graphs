package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgeCreatedEvent<ID> implements GraphEvent<ID> {
  private final ID graphId;
  private final ID edgeId;
  @NonNull
  private final ID sourceId;
  @NonNull
  private final ID targetId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
