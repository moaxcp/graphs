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

public class PostOrderDepthFirstIteratorTest {

  @SimpleGraphs
  void nullStart(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.postOrderIterator((String[]) null));
    assertThat(exception).hasMessageThat().isEqualTo("start is marked non-null but is null");
  }

  @SimpleGraphs
  void nullInOther(Graph<String> graph) {
    graph.vertex("A").vertex("B");
    var exception = assertThrows(NullPointerException.class, () -> graph.postOrderIterator("A", null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("\"id\" in \"start\" must not be null.");
  }

  @SimpleGraphs
  void startNotInGraph(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.postOrderIterator("A"));
    assertThat(exception).hasMessageThat().isEqualTo("vertex \"A\" not found in graph.");
  }

  @SimpleGraphs
  void hasNext_EmptyGraph(Graph<String> graph) {
    var iterator = graph.postOrderIterator();
    assertThat(iterator.hasNext()).isFalse();
  }

  @SimpleGraphs
  void next_EmptyGraph(Graph<String> graph) {
    var iterator = graph.postOrderIterator();
    var exception = assertThrows(NoSuchElementException.class, () -> iterator.next());
    assertThat(exception).hasMessageThat().isEqualTo("Could not find next element.");
  }

  @SimpleGraphs
  void hasNext_beforeIteration(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.postOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
  }

  @SimpleGraphs
  void hasNext_MultipleBeforeIteration(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.postOrderIterator();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next().getId()).isEqualTo("A");
  }

  @SimpleGraphs
  void hasNext_MultipleBetweenComponents(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    var iterator = graph.postOrderIterator();
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
    var iterator = graph.postOrderIterator();
    assertThat(iterator.next().getId()).isEqualTo("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsPostOrder")
  @DisplayName("postOrderIterator matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void postOrderIterator(String name, Graph<String> graph, String[] start, List<String> expectedOrder) {
    var iterator = graph.postOrderIterator(start);
    for(String expected : expectedOrder) {
      String result = iterator.next().getId();
      assertThat(result).isEqualTo(expected);
    }
  }

  @DirectedSimpleGraphs
  void postOrderIteratorStart(Graph<String> graph) {
    complexTwoComponents(graph, POST_ORDER);
    var result = new ArrayList<String>();
    var iterator = graph.postOrderIterator("D", "G", "W");
    while(iterator.hasNext()) {
      result.add(iterator.next().getId());
    }

    assertThat(result).isEqualTo(List.of("E", "C", "B", "A", "D", "G", "W", "F", "Y", "Z", "X"));
  }

  @MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#graphsPostOrder")
  @DisplayName("postOrderStream matches expected order")
  @ParameterizedTest(name = "{index} - {0} {2}")
  void postOrderStream(String name, Graph<String> graph, String[] start, List<String> expectedOrder) {
    var result = graph.postOrderStream(start)
    .map(Vertex::getId)
    .collect(toList());

    assertThat(result).isEqualTo(expectedOrder);
  }

  @DirectedSimpleGraphs
  void postOrderStreamStart(Graph<String> graph) {
    complexTwoComponents(graph, POST_ORDER);
    var result = graph.postOrderStream("D", "G", "W")
      .map(Vertex::getId)
      .collect(toList());

    assertThat(result).isEqualTo(List.of("E", "C", "B", "A", "D", "G", "W", "F", "Y", "Z", "X"));
  }
}
