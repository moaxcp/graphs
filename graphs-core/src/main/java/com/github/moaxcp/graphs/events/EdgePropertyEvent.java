package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgePropertyEvent<ID> implements GraphEvent<ID> {
  private final ID graphId;
  private final ID edgeId;
  private final ID newEdgeId;

  @NonNull
  private final ID sourceId;
  private final ID newSourceId;
  @NonNull
  private final ID targetId;
  private final ID newTargetId;

  @NonNull
  @Singular
  private final Map<String, Object> properties;
}
