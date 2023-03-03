package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import com.github.moaxcp.graphs.testframework.TestPropertyGraphs;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.google.common.truth.Truth.assertThat;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReversePostOrderDepthFirstIteratorTest {

  @TestPropertyGraphs
  void nullStart(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.reversePostOrderIterator((String[]) null));
    assertThat(exception).hasMessageThat().isEqualTo("start is marked non-null but is null");
  }

  @TestPropertyGraphs
  void nullInOther(PropertyGraph<String> graph) {
    graph.vertex("A").vertex("B");
    var exception = assertThrows(NullPointerException.class, () -> graph.reversePostOrderIterator("A", null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("\"id\" in \"start\" must not be null.");
  }

  @TestPropertyGraphs
  void startNotInGraph(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.reversePostOrderIterator("A"));
    assertThat(exception).hasMessageThat().isEqualTo("vertex \"A\" not found in graph.");
  }

  @TestPropertyGraphs
  void hasNext_EmptyGraph(PropertyGraph<String> graph) {
    var iterator = graph.reversePostOrderIterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @TestPropertyGraphs
  void next_EmptyGraph(PropertyGraph<String> graph) {
    var iterator = graph.reversePostOrderIterator();
    var exception = assertThrows(NoSuchElementException.class, () -> iterator.next());
    assertThat(exception).hasMessageThat().isEqualTo("Could not find next element.");
  }

  @TestPropertyGraphs
  void hasNext_beforeIteration(PropertyGraph<String> graph) {
    graph.vertex("A");
    var iterator = graph.reversePostOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
  }

  @TestPropertyGraphs
  void hasNext_MultipleBeforeIteration(PropertyGraph<String> graph) {
    graph.vertex("A");
    var iterator = graph.reversePostOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
  }

  @TestPropertyGraphs
  void hasNext_MultipleBetweenComponents(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    var iterator = graph.reversePostOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("B");
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
  }

  @TestPropertyGraphs
  void next_withoutCallingHasNext(PropertyGraph<String> graph) {
    graph.vertex("A");
    var iterator = graph.reversePostOrderIterator();
    assertThat(iterator.next().getId()).isEqualTo("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsReversePostOrder")
  @DisplayName("reversePostOrderIterator matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void reversePostOrderIterator(String name, PropertyGraph<String> graph, String[] start, List<String> expectedOrder) {
    var iterator = graph.reversePostOrderIterator(start);
    for(String expected : expectedOrder) {
      String result = iterator.next().getId();
      assertThat(result).isEqualTo(expected);
    }
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsReversePostOrder")
  @DisplayName("reversePostOrderStream matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void reversePostOrderStream(String name, PropertyGraph<String> graph, String[] start, List<String> expectedOrder) {
    var result = graph.reversePostOrderStream(start)
    .map(Vertex::getId)
    .collect(toList());

    assertThat(result).isEqualTo(expectedOrder);
  }
}
