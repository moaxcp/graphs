package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgePropertyEvent<K> {
  private final K graphId;
  private final K edgeId;
  private final K newEdgeId;

  @NonNull
  private final K fromId;
  private final K newFromId;
  @NonNull
  private final K toId;
  private final K newToId;

  @NonNull
  @Singular
  private final Map<String, Object> properties;
}
