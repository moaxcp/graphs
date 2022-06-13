package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgePropertyEvent<ID> implements GraphEvent<ID> {
  ID graphId;
  ID edgeId;
  ID newEdgeId;

  @NonNull
  ID sourceId;
  ID newSourceId;
  @NonNull
  ID targetId;
  ID newTargetId;

  @NonNull
  @Singular
  Map<String, Object> properties;
}
