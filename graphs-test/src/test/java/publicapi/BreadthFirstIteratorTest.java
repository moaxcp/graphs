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

class BreadthFirstIteratorTest {

  @SimpleGraphs
  void nullStart(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.breadthFirstIterator((String[]) null));
    assertThat(exception).hasMessageThat().isEqualTo("start is marked non-null but is null");
  }

  @SimpleGraphs
  void nullInOther(Graph<String> graph) {
    graph.vertex("A").vertex("B");
    var exception = assertThrows(NullPointerException.class, () -> graph.breadthFirstIterator("A", null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("\"id\" in \"start\" must not be null.");
  }

  @SimpleGraphs
  void startNotInGraph(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.breadthFirstIterator("A"));
    assertThat(exception).hasMessageThat().isEqualTo("vertex \"A\" not found in graph.");
  }

  @SimpleGraphs
  void hasNext_EmptyGraph(Graph<String> graph) {
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @SimpleGraphs
  void next_EmptyGraph(Graph<String> graph) {
    var iterator = graph.breadthFirstIterator();
    var exception = assertThrows(NoSuchElementException.class, () -> iterator.next());
    assertThat(exception).hasMessageThat().isEqualTo("Could not find next element.");
  }

  @SimpleGraphs
  void hasNext_beforeIteration(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.hasNext()).isTrue();
  }

  @SimpleGraphs
  void hasNext_MultipleBeforeIteration(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
  }

  @SimpleGraphs
  void hasNext_MultipleBetweenComponents(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("B");
  }

  @SimpleGraphs
  void next_withoutCallingHasNext(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.next().getId()).isEqualTo("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsBreadthFirst")
  @DisplayName("breadthFirstIterator matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void breadthFirstIterator(String name, Graph<String> graph, String[] start, List<String> expectedOrder) {
    var iterator = graph.breadthFirstIterator(start);
    var result = new ArrayList<String>();
    while(iterator.hasNext()) {
      result.add(iterator.next().getId());
    }
    assertThat(result).containsExactlyElementsIn(expectedOrder);
  }

  @DirectedSimpleGraphs
  void breadthFirstIteratorStart(Graph<String> graph) {
    complexTwoComponents(graph, POST_ORDER);
    var result = new ArrayList<String>();
    var iterator = graph.breadthFirstIterator("D", "G", "W");
    while(iterator.hasNext()) {
      result.add(iterator.next().getId());
    }

    assertThat(result).containsExactly("D", "E", "C", "A", "B", "G", "W", "F", "X", "Y", "Z").inOrder();
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsBreadthFirst")
  @DisplayName("breadthFirstStream matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void breadthFirstStream(String name, Graph<String> graph, String[] start, List<String> expectedOrder) {
    var result = graph.breadthFirstStream(start)
    .map(Vertex::getId)
    .collect(toList());

    assertThat(result).isEqualTo(expectedOrder);
  }

  @DirectedSimpleGraphs
  void breadthFirstStreamStart(Graph<String> graph) {
    complexTwoComponents(graph, POST_ORDER);
    var result = graph.breadthFirstStream("D", "G", "W")
      .map(Vertex::getId)
      .collect(toList());

    assertThat(result).containsExactly("D", "E", "C", "A", "B", "G", "W", "F", "X", "Y", "Z").inOrder();
  }
}
