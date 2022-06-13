package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class EdgeCreatedEvent<ID> implements GraphEvent<ID> {
  ID graphId;
  ID edgeId;
  @NonNull ID sourceId;
  @NonNull ID targetId;
  @Singular
  @NonNull Map<String, Object> properties;
}
