package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.newevents.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.Events.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class EventGraphEdgeMethods {
  @EventSimpleGraphs
  void createdWithEdge(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B").build();

    assertThat(bus).withAction(() -> graph.edge("A", "B"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @EventSimpleGraphs
  void createdWithEdge1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
      .properties(Map.of("name1", "value1")).build();

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @EventSimpleGraphs
  void createdWithEdge2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2")).build();

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @EventSimpleGraphs
  void createdWithEdge3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3")).build();

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @EventSimpleGraphs
  void createdWithEdge4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4")).build();

    assertThat(bus)
      .withAction(() -> graph
        .edge("A", "B",
          "name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @EventSimpleGraphs
  void createdWithEdge5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5")).build();

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

  @EventSimpleGraphs
  void createdWithEdge6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
      .properties(Map.of(
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6")).build();

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

  @EventSimpleGraphs
  void createdWithEdge7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithEdge8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithEdge9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
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
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
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
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdge(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B").build();

    assertThat(bus).withAction(() -> graph.getEdge("A", "B"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @EventSimpleGraphs
  void createdWithGetEdge1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
      .properties(Map.of("name1", "value1")).build();

    assertThat(bus)
      .withAction(() -> graph
        .getEdge("A", "B",
          "name1", "value1"))
      .containsExactly(expected1, expected2, expected3).inOrder();
  }

  @EventSimpleGraphs
  void createdWithGetEdge2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdge3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdge4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdge5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdge6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdge7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdge8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdge9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
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

    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void createdWithGetEdgeMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");

    var expected1 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build();
    var expected2 = VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build();
    var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B")
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

  @EventSimpleGraphs
  void updatedWithEdgeMap(EventGraph<String> graph, EventBus bus) {
      graph.id("graph").getEdge("A", "B").id("edge");
      graph.getEdge("A", "B").property("name1", "A");

      var expected = edgePropertyUpdatedEvents(1);

      assertThat(bus)
        .withAction(() -> graph.edge("A", "B", Map.of(
          "name1", "value1")))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void notCreated(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");
    assertThat(bus).withAction(() -> graph.edge("A", "B")).isEmpty();
  }

  @EventSimpleGraphs
  void removed(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name1", "value1", "name2", "value2");

    var expected = new EdgeRemoved.Builder<String>().graphId("graph").edgeId("edge").from("A").to("B").properties(Map.of("name1", "value1", "name2", "value2")).build();

    assertThat(bus).withAction(() -> graph.removeEdge("A", "B")).containsExactly(expected);
  }

  @EventSimpleGraphs
  void removeWithId(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = new EdgeRemoved.Builder<String>().graphId("graph").edgeId("edge").from("A").to("B").build();

    assertThat(bus).withAction(() -> graph.removeEdge("edge")).containsExactly(expected);
  }
}
