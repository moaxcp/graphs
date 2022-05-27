package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.testframework.PathOrder.*;
import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyGraphConnected {
  @TestGraphs
  void connectedNullFrom(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.connected(null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("from is marked non-null but is null");
  }

  @TestGraphs
  void connectedNullTo(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.connected("A", null));
    assertThat(exception).hasMessageThat().isEqualTo("to is marked non-null but is null");
  }

  @TestGraphs
  void connectedTwoVerticesFalse(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    assertThat(graph.connected("A", "B")).isFalse();
  }

  @TestGraphs
  void connectedOneEdgeSameDirection(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("A", "B")).isTrue();
  }

  @TestUndirectedGraphs
  void connectedOneEdgeOppositeDirectionUndirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("B", "A")).isTrue();
  }

  @TestDirectedGraphs
  void connectedOneEdgeOppositeDirectionDirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("B", "A")).isFalse();
  }

  @TestGraphs
  void connectedSameVertex(PropertyGraph<String> graph) {
    graph.vertex("A");
    assertThat(graph.connected("A", "A")).isFalse();
  }

  @TestGraphs
  void connectedTwoEdges(PropertyGraph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    assertThat(graph.connected("A", "C")).isTrue();
  }

  @TestUndirectedGraphs
  void connectedTwoEdgesUndirected(PropertyGraph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    assertThat(graph.connected("C", "A")).isTrue();
  }

  @TestGraphs
  void connectedTwoComponents(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("C", "D");
    assertThat(graph.connected("A", "D")).isFalse();
  }

  @TestDirectedGraphs
  void connectedComplex(PropertyGraph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    assertThat(graph.connected("A", "C")).isTrue();
  }

  @TestDirectedGraphs
  void connectedComplexFalse(PropertyGraph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    assertThat(graph.connected("A", "X")).isFalse();
  }

}
