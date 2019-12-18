package com.github.moaxcp.graphs.newevents;

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
  @NonNull
  private final K newFromId;
  @NonNull
  private final K toId;
  @NonNull
  private final K newToId;

  @NonNull
  @Singular
  private final Map<String, Object> properties;
}
