package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class EdgeRemovedEvent<ID> {
  private final ID graphId;
  private final ID edgeId;
  @NonNull
  private final ID fromId;
  @NonNull
  private final ID toId;
}
