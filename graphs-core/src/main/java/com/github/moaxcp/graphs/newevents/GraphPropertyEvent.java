package com.github.moaxcp.graphs.newevents;

import lombok.*;

import java.util.*;

@Value
@Builder
public class GraphPropertyEvent<K> {
  private final K graphId;
  private final K newId;
  @Singular
  @NonNull
  private final Map<String, Object> properties;
}
