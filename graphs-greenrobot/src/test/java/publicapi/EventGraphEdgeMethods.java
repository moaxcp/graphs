package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.Events.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class EventGraphEdgeMethods {
  @TestEventGraphs
  void createdWithEdge(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B").build();

    assertThat(bus).withAction(() -> graph.edge("A", "B"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(1);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(2);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(3);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(4);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(5);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(6);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(7);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(8);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = edgeCreatedEvent(9);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdge10(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();
    var expected2 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("B")
      .build();
    var expected3 = edgeCreatedEvent(10);

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
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
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithEdgeMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();
    var expected2 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("B")
      .build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2")).build();

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          Map.of("name1", "value1",
            "name2", "value2")))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B").build();

    assertThat(bus).withAction(() -> graph.getEdge("A", "B"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of("name1", "value1")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1",
          "name2", "value2"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8",
        "name9", "value9")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdge10(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();

    var expected2 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("B")
      .build();

    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8",
        "name9", "value9",
        "name10", "value10")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
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
      . containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void createdWithGetEdgeMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").fromId("A").toId("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          Map.of("name1", "value1",
            "name2", "value2")))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestEventGraphs
  void updatedWithEdgeMap(EventGraph<String> graph, EventBus bus) {
      graph.id("graph").getEdge("A", "B").id("edge");
      graph.getEdge("A", "B").property("name1", "A");

      var expected = edgePropertyEvent(1);

      assertThat(bus)
        .withAction(() -> graph.edge("A", "B", Map.of(
          "name1", "value1")))
      .containsExactly(expected);
  }

  @TestEventGraphs
  void notCreated(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");
    assertThat(bus).withAction(() -> graph.edge("A", "B")).isEmpty();
  }

  @TestEventGraphs
  void removed(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name1", "value1", "name2", "value2");

    var expected = EdgeRemovedEvent.<String>builder().graphId("graph").edgeId("edge").fromId("A").toId("B").build();

    assertThat(bus).withAction(() -> graph.removeEdge("A", "B")).containsExactly(expected);
  }

  @TestEventGraphs
  void removeWithId(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = EdgeRemovedEvent.<String>builder().graphId("graph").edgeId("edge").fromId("A").toId("B").build();

    assertThat(bus).withAction(() -> graph.removeEdge("edge")).containsExactly(expected);
  }
}
