package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.testframework.PathOrder.*;
import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyGraphConnected {
  @TestPropertyGraphs
  void connectedNullFrom(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.connected(null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("source is marked non-null but is null");
  }

  @TestPropertyGraphs
  void connectedNullTo(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.connected("A", null));
    assertThat(exception).hasMessageThat().isEqualTo("target is marked non-null but is null");
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
    assertThat(graph.connected("A", "B")).isTrue();
  }

  @TestUndirectedPropertyGraphs
  void connectedOneEdgeOppositeDirectionUndirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("B", "A")).isTrue();
  }

  @TestDirectedPropertyGraphs
  void connectedOneEdgeOppositeDirectionDirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("B", "A")).isFalse();
  }

  @TestPropertyGraphs
  void connectedSameVertex(PropertyGraph<String> graph) {
    graph.vertex("A");
    assertThat(graph.connected("A", "A")).isFalse();
  }

  @TestPropertyGraphs
  void connectedTwoEdges(PropertyGraph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    assertThat(graph.connected("A", "C")).isTrue();
  }

  @TestUndirectedPropertyGraphs
  void connectedTwoEdgesUndirected(PropertyGraph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    assertThat(graph.connected("C", "A")).isTrue();
  }

  @TestPropertyGraphs
  void connectedTwoComponents(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("C", "D");
    assertThat(graph.connected("A", "D")).isFalse();
  }

  @TestDirectedPropertyGraphs
  void connectedComplex(PropertyGraph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    assertThat(graph.connected("A", "C")).isTrue();
  }

  @TestDirectedPropertyGraphs
  void connectedComplexFalse(PropertyGraph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    assertThat(graph.connected("A", "X")).isFalse();
  }

}
