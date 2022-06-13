package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@Value
@Builder
public class VertexInheritedPropertyEvent<ID> implements GraphEvent<ID> {
  ID graphId;
  @Singular
  @NonNull
  Map<String, Object> properties;
}
