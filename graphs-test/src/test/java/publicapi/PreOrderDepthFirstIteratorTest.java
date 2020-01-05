package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.Graph.*;
import com.github.moaxcp.graphs.testframework.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.testframework.PathOrder.*;
import static com.google.common.truth.Truth.*;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

class PreOrderDepthFirstIteratorTest {

  @SimpleGraphs
  void nullStart(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.preOrderIterator((String[]) null));
    assertThat(exception).hasMessageThat().isEqualTo("start is marked non-null but is null");
  }

  @SimpleGraphs
  void nullInOther(Graph<String> graph) {
    graph.vertex("A").vertex("B");
    var exception = assertThrows(NullPointerException.class, () -> graph.preOrderIterator("A", null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("'id' in 'start' must not be null.");
  }

  @SimpleGraphs
  void startNotInGraph(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.preOrderIterator("A"));
    assertThat(exception).hasMessageThat().isEqualTo("vertex 'A' not found in graph.");
  }

  @SimpleGraphs
  void hasNext_EmptyGraph(Graph<String> graph) {
    var iterator = graph.preOrderIterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @SimpleGraphs
  void next_EmptyGraph(Graph<String> graph) {
    var iterator = graph.preOrderIterator();
    var exception = assertThrows(NoSuchElementException.class, () -> iterator.next());
    assertThat(exception).hasMessageThat().isEqualTo("Could not find next element.");
  }

  @SimpleGraphs
  void hasNext_beforeIteration(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.preOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsPreOrder")
  @DisplayName("preOrderIterator matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void preOrderIterator(String name, Graph<String> graph, String[] start, List<String> expectedOrder) {
    var iterator = graph.preOrderIterator(start);
    var result = new ArrayList<String>();
    while(iterator.hasNext()) {
      result.add(iterator.next().getId());
    }
    assertThat(result).containsExactlyElementsIn(expectedOrder);
  }

  @DirectedSimpleGraphs
  void preOrderIteratorStart(Graph<String> graph) {
    complexTwoComponents(graph, POST_ORDER);
    var result = new ArrayList<String>();
    var iterator = graph.preOrderIterator("D", "G", "W");
    while(iterator.hasNext()) {
      result.add(iterator.next().getId());
    }

    assertThat(result).containsExactly("D", "E", "C", "A", "B", "G", "W", "F", "X", "Y", "Z").inOrder();
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsPreOrder")
  @DisplayName("preOrderStream matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void preOrderStream(String name, Graph<String> graph, String[] start, List<String> expectedOrder) {
    var result = graph.preOrderStream(start)
    .map(Vertex::getId)
    .collect(toList());

    assertThat(result).isEqualTo(expectedOrder);
  }

  @DirectedSimpleGraphs
  void preOrderStreamStart(Graph<String> graph) {
    complexTwoComponents(graph, POST_ORDER);
    var result = graph.preOrderStream("D", "G", "W")
      .map(Vertex::getId)
      .collect(toList());

    assertThat(result).containsExactly("D", "E", "C", "A", "B", "G", "W", "F", "X", "Y", "Z").inOrder();
  }
}
