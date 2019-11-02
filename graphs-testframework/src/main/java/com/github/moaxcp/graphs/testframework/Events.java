package com.github.moaxcp.graphs.testframework;

import com.github.moaxcp.graphs.events.*;
import lombok.experimental.*;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

@UtilityClass
public class Events {

  public static List<VertexPropertyAdded<String>> propertyAddedEvents(int count) {
    return IntStream.rangeClosed(1, count)
      .mapToObj(i -> propertyAddedEvent("graph", "A", "name" + i, "value" + i))
      .collect(toList());
  }

  public static VertexPropertyAdded<String> propertyAddedEvent(String graph, String id, String name, String value) {
    return new VertexPropertyAdded.Builder<String>().graphId(graph).vertexId(id).name(name).value(value).build();
  }

  public static List<VertexPropertyUpdated<String>> propertyUpdatedEvents(int count) {
    return IntStream.rangeClosed(1, count)
      .mapToObj(i -> propertyUpdatedEvent("graph", "A", "name" + i, "value" + i, String.valueOf((char)('A' + i - 1))))
      .collect(toList());
  }

  public static VertexPropertyUpdated<String> propertyUpdatedEvent(String graph, String id, String name, String value, String oldValue) {
    return new VertexPropertyUpdated.Builder<String>().graphId(graph).vertexId(id).name(name).value(value).oldValue(oldValue).build();
  }
}
