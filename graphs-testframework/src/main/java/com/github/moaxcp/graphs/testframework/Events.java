package com.github.moaxcp.graphs.testframework;

import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.newevents.VertexPropertyEvent;
import com.github.moaxcp.graphs.newevents.*;
import lombok.experimental.*;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

@UtilityClass
public class Events {

  public static VertexPropertyEvent<String> vertexPropertyEvents(int count) {
    var builder = VertexPropertyEvent.<String>builder()
      .graphId("graph")
      .vertexId("A");

    for(int i = 0; i < count; i++) {
      builder.property("name" + (i + 1), "value" + (i + 1));
    }

    return builder.build();
  }

  public static VertexCreatedEvent<String> vertexCreatedEvents(int count) {
    var builder = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A");

    for(int i = 0; i < count; i++) {
      builder.property("name" + (i + 1), "value" + (i + 1));
    }

    return builder.build();
  }

  public static VertexPropertyAdded<String> vertexPropertyAddedEvent(String graph, String id, String name, String value) {
    return new VertexPropertyAdded.Builder<String>().graphId(graph).vertexId(id).name(name).value(value).build();
  }

  public static List<VertexPropertyUpdated<String>> vertexPropertyUpdatedEvents(int count) {
    return IntStream.rangeClosed(1, count)
      .mapToObj(i -> vertexPropertyUpdatedEvent("graph", "A", "name" + i, "value" + i, String.valueOf((char)('A' + i - 1))))
      .collect(toList());
  }

  public static VertexPropertyUpdated<String> vertexPropertyUpdatedEvent(String graph, String id, String name, String value, String oldValue) {
    return new VertexPropertyUpdated.Builder<String>().graphId(graph).vertexId(id).name(name).value(value).oldValue(oldValue).build();
  }

  public static List<EdgePropertyAdded<String>> edgePropertyAddedEvents(int count) {
    return IntStream.rangeClosed(1, count)
      .mapToObj(i -> edgePropertyAddedEvent("graph", "edge", "A", "B", "name" + i, "value" + i))
      .collect(toList());
  }

  public static EdgePropertyAdded<String> edgePropertyAddedEvent(String graphId, String edgeId, String from, String to, String name, String value) {
    return new EdgePropertyAdded.Builder<String>().graphId(graphId).edgeId(edgeId).from(from).to(to).name(name).value(value).build();
  }

  public static List<EdgePropertyUpdated<String>> edgePropertyUpdatedEvents(int count) {
    return IntStream.rangeClosed(1, count)
      .mapToObj(i -> edgePropertyUpdatedEvent("graph", "edge", "A", "B", "name" + i, "value" + i, String.valueOf((char)('A' + i - 1))))
      .collect(toList());
  }

  public static EdgePropertyUpdated<String> edgePropertyUpdatedEvent(String graphId, String edgeId, String from, String to, String name, String value, String oldValue) {
    return new EdgePropertyUpdated.Builder<String>().graphId(graphId).edgeId(edgeId).from(from).to(to).name(name).value(value).oldValue(oldValue).build();
  }
}
