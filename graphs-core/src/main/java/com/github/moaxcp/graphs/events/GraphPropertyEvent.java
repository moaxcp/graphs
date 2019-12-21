package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class GraphPropertyEvent<ID> {
  private final ID graphId;
  private final ID newId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
