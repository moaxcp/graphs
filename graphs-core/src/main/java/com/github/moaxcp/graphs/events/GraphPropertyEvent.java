package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class GraphPropertyEvent<ID> implements GraphEvent<ID> {
  ID graphId;
  ID newId;
  @Singular
  @NonNull
  Map<String, Object> properties;
}
