package com.github.moaxcp.graphs.events;

import lombok.*;

@Value
@Builder
public class VertexRemovedEvent<ID> implements GraphEvent<ID> {
  ID graphId;
  @NonNull
  ID vertexId;
}
