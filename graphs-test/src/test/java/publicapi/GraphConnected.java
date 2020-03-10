package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.*;

public class GraphConnected {
  @SimpleGraphs
  void connectedNullFrom(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.connected(null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("from is marked non-null but is null");
  }

  @SimpleGraphs
  void connectedNullTo(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.connected("A", null));
    assertThat(exception).hasMessageThat().isEqualTo("to is marked non-null but is null");
  }

  @SimpleGraphs
  void connectedTwoVerticesFalse(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    assertThat(graph.connected("A", "B")).isFalse();
  }

  @SimpleGraphs
  void connectedOneEdgeSameDirection(Graph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("A", "B")).isTrue();
  }

  @UndirectedSimpleGraphs
  void connectedOneEdgeOppositeDirectionUndirected(Graph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("B", "A")).isTrue();
  }

  @DirectedSimpleGraphs
  void connectedOneEdgeOppositeDirectionDirected(Graph<String> graph) {
    graph.edge("A", "B");
    assertThat(graph.connected("B", "A")).isFalse();
  }

  @SimpleGraphs
  void connectedSameVertex(Graph<String> graph) {
    graph.vertex("A");
    assertThat(graph.connected("A", "A")).isFalse();
  }

  @SimpleGraphs
  void connectedTwoEdges(Graph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    assertThat(graph.connected("A", "C")).isTrue();
  }

  @UndirectedSimpleGraphs
  void connectedTwoEdgesUndirected(Graph<String> graph) {
    graph.edge("A", "B").edge("B", "C");
    assertThat(graph.connected("C", "A")).isTrue();
  }

  @SimpleGraphs
  void connectedTwoComponents(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("C", "D");
    assertThat(graph.connected("A", "D")).isFalse();
  }

}
