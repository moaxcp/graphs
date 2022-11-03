package publicapi.events;

import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.events.EdgePropertyEvent;
import com.github.moaxcp.graphs.events.VertexCreatedEvent;
import com.github.moaxcp.graphs.testframework.TestObservablePropertyGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class EdgeIdToFrom {

  @TestObservablePropertyGraphs
  void edgeIdAdded(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.edge("A", "B");

    var expected = EdgePropertyEvent.<String>builder().graphId("graph").newEdgeId("edge").sourceId("A").targetId("B").build();

    assertThat(graph).withAction(g -> g.getEdge("A", "B").id("edge")).containsExactly(expected);
  }

  @TestObservablePropertyGraphs
  void edgeIdRemovedNoId(ObservablePropertyGraph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph).withAction(g -> g.getEdge("A", "B").id(null)).isEmpty();
  }

  @TestObservablePropertyGraphs
  void edgeIdRemoved(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").newEdgeId(null).sourceId("A").targetId("B").build();

    assertThat(graph).withAction(g -> g.getEdge("A", "B").id(null)).containsExactly(expected);
  }

  @TestObservablePropertyGraphs
  void edgeIdUpdated(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.getEdge("A", "B").id("oldEdge");

    var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("oldEdge").newEdgeId("edge").sourceId("A").targetId("B").build();

    assertThat(graph).withAction(g -> g.getEdge("A", "B").id("edge")).containsExactly(expected);
  }

  @TestObservablePropertyGraphs
  void edgeFromUpdatedCreatedVertex(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.getEdge("oldFrom", "B").id("edge");

    var expected1 = VertexCreatedEvent.<String>builder()
        .graphId("graph")
        .vertexId("A")
        .build();

    var expected2 = EdgePropertyEvent.<String>builder()
        .graphId("graph")
        .edgeId("edge")
        .sourceId("oldFrom")
        .newSourceId("A")
        .targetId("B")
        .build();

    assertThat(graph)
        .withAction(g -> g.getEdge("oldFrom", "B").setSource("A"))
        .containsExactly(expected1, expected2).inOrder();
  }

  @TestObservablePropertyGraphs
  void edgeToUpdatedCreatedVertex(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.getEdge("A", "oldTo").id("edge");

    var expected1 = VertexCreatedEvent.<String>builder()
        .graphId("graph")
        .vertexId("B")
        .build();

    var expected2 = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("oldTo").newTargetId("B").build();

    assertThat(graph)
        .withAction(g -> g.getEdge("A", "oldTo").setTarget("B"))
        .containsExactly(expected1, expected2);
  }

  @TestObservablePropertyGraphs
  void edgeFromUpdated(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertex("A");
    graph.getEdge("oldFrom", "B").id("edge");

    var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("oldFrom").newSourceId("A").targetId("B").build();

    assertThat(graph).withAction(g -> g.getEdge("oldFrom", "B").setSource("A")).containsExactly(expected);
  }

  @TestObservablePropertyGraphs
  void edgeToUpdated(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertex("B");
    graph.getEdge("A", "oldTo").id("edge");

    var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("oldTo").newTargetId("B").build();

    assertThat(graph).withAction(g -> g.getEdge("A", "oldTo").setTarget("B")).containsExactly(expected);
  }
}
