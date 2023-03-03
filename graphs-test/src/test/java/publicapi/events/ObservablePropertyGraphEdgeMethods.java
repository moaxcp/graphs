package publicapi.events;

import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.events.EdgeCreatedEvent;
import com.github.moaxcp.graphs.events.EdgePropertyEvent;
import com.github.moaxcp.graphs.events.EdgeRemovedEvent;
import com.github.moaxcp.graphs.events.VertexCreatedEvent;
import com.github.moaxcp.graphs.testframework.TestObservablePropertyGraphs;
import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class ObservablePropertyGraphEdgeMethods {
  @TestObservablePropertyGraphs
  void createWithEdge(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.edge("A", "B"))
        .containsExactly(VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build(),
            VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build(),
            EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").build())
        .inOrder();
  }

  @TestObservablePropertyGraphs
  void createWithEdge1(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.edge("A", "B", "name1", "value1"))
        .containsExactly(VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build(),
            VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build(),
            EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").property("name1", "value1").build())
        .inOrder();
  }

  @TestObservablePropertyGraphs
  void createWithEdgeMap(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.edge("A", "B", Map.of("name1", "value1", "name2", "value2")))
        .containsExactly(VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build(),
            VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build(),
            EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").property("name1", "value1").property("name2", "value2").build())
        .inOrder();
  }

  @TestObservablePropertyGraphs
  void createWithGetEdgeMap(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2")))
        .containsExactly(VertexCreatedEvent.<String>builder().graphId("graph").vertexId("A").build(),
            VertexCreatedEvent.<String>builder().graphId("graph").vertexId("B").build(),
            EdgeCreatedEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").property("name1", "value1").property("name2", "value2").build())
        .inOrder();
  }

  @TestObservablePropertyGraphs
  void updateWithEdgeMap(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.edge("A", "B", "id", "edge", "name1", "A");
    assertThat(graph).withAction(g -> g.edge("A", "B", Map.of("name1", "value1", "name2", "value2")))
        .containsExactly(EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("B").property("name1", "value1").property("name2", "value2").build())
        .inOrder();
  }

  @TestObservablePropertyGraphs
  void notCreated(ObservablePropertyGraph<String> graph) {
    graph.id("graph").edge("A", "B", "id", "edge");
    assertThat(graph).withAction(g -> g.edge("A", "B")).isEmpty();
  }

  @TestObservablePropertyGraphs
  void removed(ObservablePropertyGraph<String> graph) {
    graph.id("graph").edge("A", "B", "id", "edge", "name1", "value1", "name2", "value2");
    assertThat(graph).withAction(g -> g.removeEdge("A", "B")).containsExactly(EdgeRemovedEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("B").build());
  }

  @TestObservablePropertyGraphs
  void removedWithId(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getEdge("A", "B").id("edge").property("name1", "value1", "name2", "value2");
    assertThat(graph).withAction(g -> g.removeEdge("edge")).containsExactly(EdgeRemovedEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("B").build());
  }
}
