package com.github.moaxcp.graphs.testframework;

import com.github.moaxcp.graphs.events.*;
import lombok.experimental.*;

@UtilityClass
public class Events {

  public static VertexPropertyEvent<String> vertexPropertyEvent(int count) {
    var builder = VertexPropertyEvent.<String>builder()
      .graphId("graph")
      .vertexId("A");

    for(int i = 0; i < count; i++) {
      builder.property("name" + (i + 1), "value" + (i + 1));
    }

    return builder.build();
  }

  public static VertexCreatedEvent<String> vertexCreatedEvent(int count) {
    var builder = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A");

    for(int i = 0; i < count; i++) {
      builder.property("name" + (i + 1), "value" + (i + 1));
    }

    return builder.build();
  }

  public static EdgePropertyEvent<String> edgePropertyEvent(int count) {
    var builder = EdgePropertyEvent.<String>builder()
      .graphId("graph")
      .edgeId("edge")
      .fromId("A")
      .toId("B");

    for(int i = 0; i < count; i++) {
      builder.property("name" + (i + 1), "value" + (i + 1));
    }

    return builder.build();
  }

  public static EdgeCreatedEvent<String> edgeCreatedEvent(int count) {
    var builder = EdgeCreatedEvent.<String>builder()
      .graphId("graph")
      .fromId("A")
      .toId("B");

    for(int i = 0; i < count; i++) {
      builder.property("name" + (i + 1), "value" + (i + 1));
    }

    return builder.build();
  }
}
