package publicapi.events;

import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.events.EdgeRemovedEvent;
import com.github.moaxcp.graphs.events.VertexCreatedEvent;
import com.github.moaxcp.graphs.events.VertexPropertyEvent;
import com.github.moaxcp.graphs.events.VertexRemovedEvent;
import com.github.moaxcp.graphs.testframework.TestObservablePropertyGraphs;
import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class ObservablePropertyGraphVertexMethods {

  @TestObservablePropertyGraphs
  void vertexCreated(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A"))
        .containsExactly(VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedOneProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A", "name", "value"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name", "value").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedTwoProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedThreeProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedFourProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedFiveProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedSixProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedSevenProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6",
            "name7", "value7"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedEightProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6",
            "name7", "value7",
            "name8", "value8"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7")
            .property("name8", "value8").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedNineProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6",
            "name7", "value7",
            "name8", "value8",
            "name9", "value9"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7")
            .property("name8", "value8")
            .property("name9", "value9").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedTenProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A",
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
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7")
            .property("name8", "value8")
            .property("name9", "value9")
            .property("name10", "value10").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedMapProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.vertex("A", Map.of(
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6",
            "name7", "value7",
            "name8", "value8",
            "name9", "value9",
            "name10", "value10")))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7")
            .property("name8", "value8")
            .property("name9", "value9")
            .property("name10", "value10").build());
  }

  @TestObservablePropertyGraphs
  void updatedWithVertex1(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertex("A", "name1", "A");
    assertThat(graph).withAction(g -> g.vertex("A", "name1", "value1"))
        .containsExactly(VertexPropertyEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .build());
  }

  @TestObservablePropertyGraphs
  void updatedWithVertex1Remove(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertex("A", "name1", "value1");
    assertThat(graph).withAction(g -> g.vertex("A", "name1", null))
        .containsExactly(VertexPropertyEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", null)
            .build());
  }

  @TestObservablePropertyGraphs
  void updatedWithVertexMap(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertex("A", "name1", "A", "name2", "B");
    assertThat(graph).withAction(g -> g.vertex("A", "name1", "value1", "name2", "value2"))
        .containsExactly(VertexPropertyEvent.<String>builder()
            .graphId("graph")
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .build());
  }

  @TestObservablePropertyGraphs
  void notCreated(ObservablePropertyGraph<String> graph) {
    graph.vertex("A");
    assertThat(graph).withAction(g -> g.vertex("A")).isEmpty();
  }

  @TestObservablePropertyGraphs
  void remove(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertex("A");
    assertThat(graph).withAction(g -> g.removeVertex("A")).containsExactly(VertexRemovedEvent.<String>builder()
        .graphId("graph")
        .vertexId("A")
        .build());
  }

  @TestObservablePropertyGraphs
  void removeWithProperties(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertex("A", "name1", "value1");
    assertThat(graph).withAction(g -> g.removeVertex("A")).containsExactly(VertexRemovedEvent.<String>builder()
        .graphId("graph")
        .vertexId("A")
        .build());
  }

  @TestObservablePropertyGraphs
  void removeWithEdges(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.edge("A", "B");
    graph.edge("A", "C");
    graph.edge("A", "D");

    assertThat(graph).withAction(g -> g.removeVertex("A"))
        .containsExactly(EdgeRemovedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").build(),
            EdgeRemovedEvent.<String>builder().graphId("graph").sourceId("A").targetId("C").build(),
            EdgeRemovedEvent.<String>builder().graphId("graph").sourceId("A").targetId("D").build(),
            VertexRemovedEvent.<String>builder().graphId("graph").vertexId("A").build())
        .inOrder();
  }
}
