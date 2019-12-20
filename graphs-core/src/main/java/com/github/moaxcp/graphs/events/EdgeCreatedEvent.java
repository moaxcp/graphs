package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgeCreatedEvent<K> {
  private final K graphId;
  private final K edgeId;
  @NonNull
  private final K fromId;
  @NonNull
  private final K toId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
