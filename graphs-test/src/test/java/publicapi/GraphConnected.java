package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.testframework.PathOrder.*;
import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.*;

public class GraphConnected {
  @TestGraphs
  void connectedNullFrom(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.connected(null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("from is marked non-null but is null");
  }

  @TestGraphs
  void connectedNullTo(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.connected("A", null));
    assertThat(exception).hasMessageThat().isEqualTo("to is marked non-null but is null");
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
    assertThat(graph.connected("A", "B")).isTrue();
  }

  @TestUndirectedGraphs
  void connectedOneEdgeOppositeDirectionUndirected(Graph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("B", "A")).isTrue();
  }

  @TestDirectedGraphs
  void connectedOneEdgeOppositeDirectionDirected(Graph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("B", "A")).isFalse();
  }

  @TestGraphs
  void connectedSameVertex(Graph<String> graph) {
    graph.vertex("A");
    assertThat(graph.connected("A", "A")).isFalse();
  }

  @TestGraphs
  void connectedTwoEdges(Graph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    assertThat(graph.connected("A", "C")).isTrue();
  }

  @TestUndirectedGraphs
  void connectedTwoEdgesUndirected(Graph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    assertThat(graph.connected("C", "A")).isTrue();
  }

  @TestGraphs
  void connectedTwoComponents(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("C", "D");
    assertThat(graph.connected("A", "D")).isFalse();
  }

  @TestDirectedGraphs
  void connectedComplex(Graph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    assertThat(graph.connected("A", "C")).isTrue();
  }

  @TestDirectedGraphs
  void connectedComplexFalse(Graph<String> graph) {
    complexTwoComponents(graph, PRE_ORDER);
    assertThat(graph.connected("A", "X")).isFalse();
  }

}
