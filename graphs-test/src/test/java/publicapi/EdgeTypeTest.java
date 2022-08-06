package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestDirectedGraphs;
import com.github.moaxcp.graphs.testframework.TestGraphs;
import java.util.NoSuchElementException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EdgeTypeTest {

  @TestGraphs
  void nullStart(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.edgeTypeIterator((String[]) null));
    assertThat(exception).hasMessageThat().isEqualTo("start is marked non-null but is null");
  }

  @TestGraphs
  void nullInOther(PropertyGraph<String> graph) {
    graph.vertex("A").vertex("B");
    var exception = assertThrows(NullPointerException.class, () -> graph.edgeTypeIterator("A", null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("\"id\" in \"start\" must not be null.");
  }

  @TestGraphs
  void startNotInGraph(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.edgeTypeIterator("A"));
    assertThat(exception).hasMessageThat().isEqualTo("vertex \"A\" not found in graph.");
  }

  @TestGraphs
  void hasNext_EmptyGraph(PropertyGraph<String> graph) {
    var iterator = graph.edgeTypeIterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @TestGraphs
  void next_EmptyGraph(PropertyGraph<String> graph) {
    var iterator = graph.edgeTypeIterator();
    var exception = assertThrows(NoSuchElementException.class, () -> iterator.next());
    assertThat(exception).hasMessageThat().isEqualTo("Could not find next element.");
  }

  @TestGraphs
  void hasNext_beforeIteration(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var iterator = graph.edgeTypeIterator();
    assertThat(iterator.hasNext()).isTrue();
  }

  @TestGraphs
  void hasNext_MultipleBeforeIteration(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var iterator = graph.edgeTypeIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
  }

  @TestDirectedGraphs
  void oneEdge(PropertyGraph<String> graph) {
    graph.edge("A", "B");

    graph.edgeTypeIterator();
  }
}
