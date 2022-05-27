package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.Events.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class EventPropertyGraphVertexMethods {
  @TestEventGraphs
  void createdWithVertex(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(0);
    assertThat(bus)
      .withAction(() -> graph.vertex("A"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex1(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(1);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex2(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(2);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex3(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(3);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex4(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(4);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex5(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(5);

    assertThat(bus).withAction(() -> graph.vertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex6(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(6);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex7(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(7);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex8(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(8);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex9(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(9);

    assertThat(bus).withAction(() -> graph.vertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5",
      "name6", "value6",
      "name7", "value7",
      "name8", "value8",
      "name9", "value9"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertex10(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(10);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
      "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8",
        "name9", "value9",
        "name10", "value10"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithVertexMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(1);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        Map.of("name1", "value1")))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex1(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A");
    var expected = vertexPropertyEvent(1);

    assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex1Remove(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A", "name1", "value1");

    assertThat(bus)
      .withAction(() -> graph.vertex("A", "name1", null))
      .containsExactly(VertexPropertyEvent.<String>builder()
        .graphId("graph")
        .vertexId("A")
        .property("name1", null)
        .build());
  }

  @TestEventGraphs
  void updatedWithVertex2(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B");
    var expected = vertexPropertyEvent(2);

    assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex3(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C");
    var expected = vertexPropertyEvent(3);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex4(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D");
    var expected = vertexPropertyEvent(4);

    assertThat(bus).withAction(() -> graph.vertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex5(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
      "name5", "E");
    var expected = vertexPropertyEvent(5);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex6(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F");
    var expected = vertexPropertyEvent(6);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex7(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
      "name7", "G");
    var expected = vertexPropertyEvent(7);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex8(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G",
        "name8", "H");
    var expected = vertexPropertyEvent(8);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex9(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G",
        "name8", "H",
        "name9", "I");
    var expected = vertexPropertyEvent(9);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8",
        "name9", "value9"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertex10(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G",
        "name8", "H",
        "name9", "I",
        "name10", "J");
    var expected = vertexPropertyEvent(10);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8",
        "name9", "value9",
        "name10", "value10"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithVertexMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B");
    var expected = vertexPropertyEvent(2);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        Map.of("name1", "value1", "name2", "value2")))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(0);
    assertThat(bus)
      .withAction(() -> graph.getVertex("A"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex1(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(1);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A", "name1", "value1"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex2(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(2);

    assertThat(bus)
      .withAction(() -> graph.getVertex(
        "A", "name1", "value1",
        "name2", "value2"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex3(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(3);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex4(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(4);

    assertThat(bus).withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex5(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(5);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex6(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(6);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5",
      "name6", "value6"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex7(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(7);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex8(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(8);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5",
      "name6", "value6",
      "name7", "value7",
      "name8", "value8"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex9(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(9);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8",
        "name9", "value9"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertex10(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(10);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5",
      "name6", "value6",
      "name7", "value7",
      "name8", "value8",
      "name9", "value9",
      "name10", "value10"))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void createdWithGetVertexMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvent(1);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
        Map.of("name1", "value1")))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void updatedWithGetVertex1Remove(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A", "name1", "value1");

    assertThat(bus)
      .withAction(() -> graph.getVertex("A", "name1", null))
      .containsExactly(VertexPropertyEvent.<String>builder()
        .graphId("graph")
        .vertexId("A")
        .property("name1", null)
        .build());
  }

  @TestEventGraphs
  void notCreated(EventPropertyGraph<String> graph, EventBus bus) {
    graph.vertex("A");
    assertThat(bus).withAction(() -> graph.vertex("A")).isEmpty();
  }

  @TestEventGraphs
  void remove(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = VertexRemovedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();

    assertThat(bus).withAction(() -> graph.removeVertex("A")).containsExactly(expected);
  }

  @TestEventGraphs
  void removeWithProperties(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A", "name1", "value1");

    var expected = VertexRemovedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();

    assertThat(bus).withAction(() -> graph.removeVertex("A")).containsExactly(expected);
  }

  @TestEventGraphs
  void removeWithEdges(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.edge("A", "B");
    graph.edge("A", "C");
    graph.edge("A", "D");

    var expected1 = EdgeRemovedEvent.<String>builder().graphId("graph").fromId("A").toId("B").build();
    var expected2 = EdgeRemovedEvent.<String>builder().graphId("graph").fromId("A").toId("C").build();
    var expected3 = EdgeRemovedEvent.<String>builder().graphId("graph").fromId("A").toId("D").build();
    var expected4 = VertexRemovedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();

    assertThat(bus).withAction(() -> graph.removeVertex("A"))
      .containsExactly(expected1, expected2, expected3, expected4).inOrder();
  }
}
