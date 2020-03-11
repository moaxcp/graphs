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

  @TestGraphs
  void nullStart(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.preOrderIterator((String[]) null));
    assertThat(exception).hasMessageThat().isEqualTo("start is marked non-null but is null");
  }

  @TestGraphs
  void nullInOther(Graph<String> graph) {
    graph.vertex("A").vertex("B");
    var exception = assertThrows(NullPointerException.class, () -> graph.preOrderIterator("A", null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("\"id\" in \"start\" must not be null.");
  }

  @TestGraphs
  void startNotInGraph(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.preOrderIterator("A"));
    assertThat(exception).hasMessageThat().isEqualTo("vertex \"A\" not found in graph.");
  }

  @TestGraphs
  void hasNext_EmptyGraph(Graph<String> graph) {
    var iterator = graph.preOrderIterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @TestGraphs
  void next_EmptyGraph(Graph<String> graph) {
    var iterator = graph.preOrderIterator();
    var exception = assertThrows(NoSuchElementException.class, () -> iterator.next());
    assertThat(exception).hasMessageThat().isEqualTo("Could not find next element.");
  }

  @TestGraphs
  void hasNext_beforeIteration(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.preOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
  }

  @TestGraphs
  void hasNext_MultipleBeforeIteration(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.preOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
  }

  @TestGraphs
  void hasNext_MultipleBetweenComponents(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    var iterator = graph.preOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("B");
  }

  @TestGraphs
  void next_withoutCallingHasNext(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.preOrderIterator();
    assertThat(iterator.next().getId()).isEqualTo("A");
    assertThat(iterator.hasNext()).isFalse();
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

  @TestDirectedGraphs
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

  @TestDirectedGraphs
  void preOrderStreamStart(Graph<String> graph) {
    complexTwoComponents(graph, POST_ORDER);
    var result = graph.preOrderStream("D", "G", "W")
      .map(Vertex::getId)
      .collect(toList());

    assertThat(result).containsExactly("D", "E", "C", "A", "B", "G", "W", "F", "X", "Y", "Z").inOrder();
  }
}
