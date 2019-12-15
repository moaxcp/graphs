package com.github.moaxcp.graphs.newevents;

import lombok.*;

@Value
@Builder
public class EdgeRemovedEvent<K> {
  private final K graphId;
  private final K edgeId;
  @NonNull
  private final K fromId;
  @NonNull
  private final K toId;
}
