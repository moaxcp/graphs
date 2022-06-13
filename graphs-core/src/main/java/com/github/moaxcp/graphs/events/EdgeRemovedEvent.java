package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class EdgeRemovedEvent<ID> implements GraphEvent<ID> {
  ID graphId;
  ID edgeId;
  @NonNull
  ID sourceId;
  @NonNull
  ID targetId;
}
