package com.github.moaxcp.graphs.newevents;

import lombok.*;

import java.util.*;

@Value
public class VertexInheritedPropertyEvent<K> {
  private final K graphId;
  private final Map<String, Object> properties;
}
