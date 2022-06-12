package publicapi;

import com.github.moaxcp.graphs.EventPropertyGraph;
import com.github.moaxcp.graphs.events.EdgeCreatedEvent;
import com.github.moaxcp.graphs.events.EdgeRemovedEvent;
import com.github.moaxcp.graphs.events.VertexCreatedEvent;
import com.github.moaxcp.graphs.testframework.TestGuavaEventGraphs;
import com.google.common.eventbus.EventBus;
import java.util.Map;

import static com.github.moaxcp.graphs.testframework.Events.edgeCreatedEvent;
import static com.github.moaxcp.graphs.testframework.Events.edgePropertyEvent;
import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class EventPropertyGraphEdgeMethods {
  @TestGuavaEventGraphs
  void createdWithEdge(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").build();

    assertThat(bus).withAction(() -> graph.edge("A", "B"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestGuavaEventGraphs
  void createdWithEdge1(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge2(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge3(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge4(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge5(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge6(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge7(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge8(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge9(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdge10(EventPropertyGraph<String> graph, EventBus bus) {
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

  @TestGuavaEventGraphs
  void createdWithEdgeMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();
    var expected2 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("B")
      .build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").build();

    assertThat(bus).withAction(() -> graph.getEdge("A", "B"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestGuavaEventGraphs
  void createdWithGetEdge1(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
      .properties(Map.of("name1", "value1")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @TestGuavaEventGraphs
  void createdWithGetEdge2(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge3(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge4(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge5(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge6(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge7(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge8(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge9(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdge10(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();

    var expected2 = VertexCreatedEvent.<String>builder()
      .graphId("graph")
      .vertexId("B")
      .build();

    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void createdWithGetEdgeMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B")
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

  @TestGuavaEventGraphs
  void updatedWithEdgeMap(EventPropertyGraph<String> graph, EventBus bus) {
      graph.id("graph").getEdge("A", "B").id("edge");
      graph.getEdge("A", "B").property("name1", "A");

      var expected = edgePropertyEvent(1);

      assertThat(bus)
        .withAction(() -> graph.edge("A", "B", Map.of(
          "name1", "value1")))
      .containsExactly(expected);
  }

  @TestGuavaEventGraphs
  void notCreated(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");
    assertThat(bus).withAction(() -> graph.edge("A", "B")).isEmpty();
  }

  @TestGuavaEventGraphs
  void removed(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name1", "value1", "name2", "value2");

    var expected = EdgeRemovedEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("B").build();

    assertThat(bus).withAction(() -> graph.removeEdge("A", "B")).containsExactly(expected);
  }

  @TestGuavaEventGraphs
  void removeWithId(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = EdgeRemovedEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("B").build();

    assertThat(bus).withAction(() -> graph.removeEdge("edge")).containsExactly(expected);
  }
}
