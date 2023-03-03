package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.testframework.PathOrder.*;
import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.*;

public class VertexConnected {
  @TestPropertyGraphs
  void connectedNullId(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A");
    var exception = assertThrows(NullPointerException.class, () -> vertex.connected(null));
    assertThat(exception).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestPropertyGraphs
  void connectedTwoVerticesFalse(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    assertThat(graph.connected("A", "B")).isFalse();
  }

  @TestPropertyGraphs
  void connectedOneEdgeSameDirection(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("B")).isTrue();
  }

  @TestUndirectedPropertyGraphs
  void connectedOneEdgeOppositeDirectionUndirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var vertex = graph.getVertex("B");
    assertThat(vertex.connected("A")).isTrue();
  }

  @TestDirectedPropertyGraphs
  void connectedOneEdgeOppositeDirectionDirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var vertex = graph.getVertex("B");
    assertThat(vertex.connected("A")).isFalse();
  }

  @TestPropertyGraphs
  void connectedSameVertex(PropertyGraph<String> graph) {
    graph.vertex("A");
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("A")).isFalse();
  }

  @TestPropertyGraphs
  void connectedTwoEdges(PropertyGraph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("C")).isTrue();
  }

  @TestUndirectedPropertyGraphs
  void connectedTwoEdgesUndirected(PropertyGraph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    var vertex = graph.getVertex("C");
    assertThat(vertex.connected("C")).isTrue();
  }

  @TestPropertyGraphs
  void connectedTwoComponents(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("C", "D");
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("D")).isFalse();
  }

  @TestDirectedPropertyGraphs
  void connectedComplex(PropertyGraph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("C")).isTrue();
  }

  @TestDirectedPropertyGraphs
  void connectedComplexFalse(PropertyGraph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    var vertex = graph.getVertex("A");
    assertThat(vertex.connected("X")).isFalse();
  }

}
