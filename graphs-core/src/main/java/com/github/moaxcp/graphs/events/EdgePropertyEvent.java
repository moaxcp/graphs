package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgePropertyEvent<ID> {
  private final ID graphId;
  private final ID edgeId;
  private final ID newEdgeId;

  @NonNull
  private final ID fromId;
  private final ID newFromId;
  @NonNull
  private final ID toId;
  private final ID newToId;

  @NonNull
  @Singular
  private final Map<String, Object> properties;
}
