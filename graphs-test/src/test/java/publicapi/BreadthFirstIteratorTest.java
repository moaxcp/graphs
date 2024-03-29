package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.PropertyGraph.*;
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

  @TestPropertyGraphs
  void nullStart(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.breadthFirstIterator((String[]) null));
    assertThat(exception).hasMessageThat().isEqualTo("start is marked non-null but is null");
  }

  @TestPropertyGraphs
  void nullInOther(PropertyGraph<String> graph) {
    graph.vertex("A").vertex("B");
    var exception = assertThrows(NullPointerException.class, () -> graph.breadthFirstIterator("A", null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("\"id\" in \"start\" must not be null.");
  }

  @TestPropertyGraphs
  void startNotInGraph(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.breadthFirstIterator("A"));
    assertThat(exception).hasMessageThat().isEqualTo("vertex \"A\" not found in graph.");
  }

  @TestPropertyGraphs
  void hasNext_EmptyGraph(PropertyGraph<String> graph) {
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @TestPropertyGraphs
  void next_EmptyGraph(PropertyGraph<String> graph) {
    var iterator = graph.breadthFirstIterator();
    var exception = assertThrows(NoSuchElementException.class, () -> iterator.next());
    assertThat(exception).hasMessageThat().isEqualTo("Could not find next element.");
  }

  @TestPropertyGraphs
  void hasNext_beforeIteration(PropertyGraph<String> graph) {
    graph.vertex("A");
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.hasNext()).isTrue();
  }

  @TestPropertyGraphs
  void hasNext_MultipleBeforeIteration(PropertyGraph<String> graph) {
    graph.vertex("A");
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
  }

  @TestPropertyGraphs
  void hasNext_MultipleBetweenComponents(PropertyGraph<String> graph) {
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

  @TestPropertyGraphs
  void next_withoutCallingHasNext(PropertyGraph<String> graph) {
    graph.vertex("A");
    var iterator = graph.breadthFirstIterator();
    assertThat(iterator.next().getId()).isEqualTo("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsBreadthFirst")
  @DisplayName("breadthFirstIterator matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void breadthFirstIterator(String name, PropertyGraph<String> graph, String[] start, List<String> expectedOrder) {
    var iterator = graph.breadthFirstIterator(start);
    var result = new ArrayList<String>();
    while(iterator.hasNext()) {
      result.add(iterator.next().getId());
    }
    assertThat(result).containsExactlyElementsIn(expectedOrder);
  }

  @TestDirectedPropertyGraphs
  void breadthFirstIteratorStart(PropertyGraph<String> graph) {
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
  void breadthFirstStream(String name, PropertyGraph<String> graph, String[] start, List<String> expectedOrder) {
    var result = graph.breadthFirstStream(start)
    .map(Vertex::getId)
    .collect(toList());

    assertThat(result).isEqualTo(expectedOrder);
  }

  @TestDirectedPropertyGraphs
  void breadthFirstStreamStart(PropertyGraph<String> graph) {
    complexTwoComponents(graph, POST_ORDER);
    var result = graph.breadthFirstStream("D", "G", "W")
      .map(Vertex::getId)
      .collect(toList());

    assertThat(result).containsExactly("D", "E", "C", "A", "B", "G", "W", "F", "X", "Y", "Z").inOrder();
  }
}
