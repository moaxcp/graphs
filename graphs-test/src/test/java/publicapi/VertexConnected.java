package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.testframework.PathOrder.*;
import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.*;

public class VertexConnected {
  @TestGraphs
  void connectedNullId(Graph<String> graph) {
    var vertex = graph.getVertex("A");
    var exception = assertThrows(NullPointerException.class, () -> vertex.connected(null));
    assertThat(exception).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestGraphs
  void connectedTwoVerticesFalse(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    assertThat(graph.connected("A", "B")).isFalse();
  }

  @TestGraphs
  void connectedOneEdgeSameDirection(Graph<String> graph) {
    graph.edge("A", "B");
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("B")).isTrue();
  }

  @TestUndirectedGraphs
  void connectedOneEdgeOppositeDirectionUndirected(Graph<String> graph) {
    graph.edge("A", "B");
    var vertex = graph.getVertex("B");
    assertThat(vertex.connected("A")).isTrue();
  }

  @TestDirectedGraphs
  void connectedOneEdgeOppositeDirectionDirected(Graph<String> graph) {
    graph.edge("A", "B");
    var vertex = graph.getVertex("B");
    assertThat(vertex.connected("A")).isFalse();
  }

  @TestGraphs
  void connectedSameVertex(Graph<String> graph) {
    graph.vertex("A");
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("A")).isFalse();
  }

  @TestGraphs
  void connectedTwoEdges(Graph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("C")).isTrue();
  }

  @TestUndirectedGraphs
  void connectedTwoEdgesUndirected(Graph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    var vertex = graph.getVertex("C");
    assertThat(vertex.connected("C")).isTrue();
  }

  @TestGraphs
  void connectedTwoComponents(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("C", "D");
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("D")).isFalse();
  }

  @TestDirectedGraphs
  void connectedComplex(Graph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("C")).isTrue();
  }

  @TestDirectedGraphs
  void connectedComplexFalse(Graph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("X")).isFalse();
  }

}
