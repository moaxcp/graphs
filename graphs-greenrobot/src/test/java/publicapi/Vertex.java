package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.Events.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class Vertex {

  @TestEventGraphs
  void updateId(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = VertexPropertyEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .newId("B")
      .build();

    assertThat(bus).withAction(() -> graph.getVertex("A").id("B"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty1(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(1);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty2(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(2);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty3(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(3);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty4(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(4);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty5(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(5);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty6(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(6);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6")).containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty7(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(7);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7")).containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty8(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(8);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8")).containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty9(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(9);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9")).containsExactly(expected);
  }

  @TestEventGraphs
  void addProperty10(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = vertexPropertyEvent(10);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9",
          "name10", "value10")).containsExactly(expected);
  }

  @TestEventGraphs
  void addPropertyMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = VertexPropertyEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .property("name1", "value1")
      .build();

    assertThat(bus)
      .withAction(() -> graph.getVertex("A").property(Map.of("name1", "value1")))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty1(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A");

    var expected = vertexPropertyEvent(1);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty2(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B");

    var expected = vertexPropertyEvent(2);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty3(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C");

    var expected = vertexPropertyEvent(3);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty4(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A",
      "name2", "B", "name3",
      "C", "name4", "D");

    var expected = vertexPropertyEvent(4);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty5(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E");

    var expected = vertexPropertyEvent(5);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty6(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F");

    var expected = vertexPropertyEvent(6);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty7(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G");

    var expected = vertexPropertyEvent(7);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty8(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H");

    var expected = vertexPropertyEvent(8);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty9(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H", "name9", "I");

    var expected = vertexPropertyEvent(9);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9")).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty10(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H", "name9", "I", "name10", "J");

    var expected = vertexPropertyEvent(10);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9",
          "name10", "value10")).containsExactly(expected);
  }

  @TestEventGraphs
  void updatePropertyMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property(Map.of("name1", "A", "name2", "B"));

    var expected = vertexPropertyEvent(2);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property(Map.of("name1", "value1", "name2", "value2"))).containsExactly(expected);
  }

  @TestEventGraphs
  void updateProperty1Remove(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A", "name1", "value1");

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", null))
      .containsExactly(VertexPropertyEvent.<String>builder()
        .graphId("graph")
        .vertexId("A")
        .property("name1", null)
        .build());
  }

  @TestEventGraphs
  void updatePropertyAlreadyExists(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "value1");

    var expected = VertexPropertyEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .property("name1", "value1")
      .build();

    assertThat(bus)
      .withAction(() -> graph.getVertex("A").property("name1", "value1"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void removeProperty(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name", "value");

    var expected = VertexPropertyEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .property("name", null)
      .build();

    assertThat(bus)
      .withAction(() -> graph.getVertex("A").removeProperty("name"))
      .containsExactly(expected);
  }
}
