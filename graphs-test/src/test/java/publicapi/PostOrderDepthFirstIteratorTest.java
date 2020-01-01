package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.google.common.truth.Truth.*;
import static java.util.function.Function.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

public class PostOrderDepthFirstIteratorTest {

  @SimpleGraphs
  void nullStart(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.postOrderIterator(null));
    assertThat(exception).hasMessageThat().isEqualTo("start is marked non-null but is null");
  }

  @SimpleGraphs
  void nullInOther(Graph<String> graph) {
    graph.vertex("A").vertex("B");
    var exception = assertThrows(NullPointerException.class, () -> graph.postOrderIterator("A", null, "B"));
    assertThat(exception).hasMessageThat().isEqualTo("'id' in 'start' must not be null.");
  }

  @SimpleGraphs
  void startNotInGraph(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.postOrderIterator("A"));
    assertThat(exception).hasMessageThat().isEqualTo("vertex 'A' not found in graph.");
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

  @MethodSource("graphsPostOrder")
  @DisplayName("dfs matches expected order")
  @ParameterizedTest(name = "{index} - {0} post order {2}")
  void dfs(String name, Graph<String> graph, List<String> expectedOrder) {
    var iterator = graph.postOrderIterator();
    for(String expected : expectedOrder) {
      String result = iterator.next().getId();
      assertThat(result).isEqualTo(expected);
    }
  }

  private static Stream<Arguments> graphsPostOrder() {
    return Stream.of(
      simpleGraphArguments(PostOrderDepthFirstIteratorTest::one),
      simpleGraphArguments(PostOrderDepthFirstIteratorTest::two),
      simpleGraphArguments(PostOrderDepthFirstIteratorTest::twoLinked),
      simpleGraphArguments(PostOrderDepthFirstIteratorTest::three),
      simpleGraphArguments(PostOrderDepthFirstIteratorTest::threeLinked),
      simpleGraphArguments(PostOrderDepthFirstIteratorTest::threeSplit),
      simpleGraphArguments(PostOrderDepthFirstIteratorTest::splitJoin),
      directedGraphArguments(PostOrderDepthFirstIteratorTest::complexOneTraversal),
      directedGraphArguments(PostOrderDepthFirstIteratorTest::complexTwoTraversal)
    ).flatMap(identity());
  }

  private static Stream<Arguments> simpleGraphArguments(Function<Graph<String>, Arguments> convert) {
    return simpleGraphs()
      .map(convert);
  }

  private static Stream<Arguments> directedGraphArguments(Function<Graph<String>, Arguments> convert) {
    return directedSimpleGraphs()
      .map(convert);
  }

  private static Arguments complexTwoTraversal(Graph<String> graph) {
    graph.edge("A", "B")
      .edge("B", "C")
      .edge("B", "D")
      .edge("D", "E")
      .edge("D", "C")
      .edge("A", "D")
      .edge("D", "A")
      .edge("A", "E")
      .edge("F", "G")
      .edge("G", "D");

    return arguments("complex", graph, List.of("C", "E", "D", "B", "A", "G", "F"));
  }

  private static Arguments complexOneTraversal(Graph<String> graph) {
    graph.edge("A", "B")
      .edge("B", "C")
      .edge("B", "D")
      .edge("D", "E")
      .edge("D", "C")
      .edge("A", "D")
      .edge("D", "A")
      .edge("A", "E");

    return arguments("complex", graph, List.of("C", "E", "D", "B", "A"));
  }

  private static Arguments splitJoin(Graph<String> graph) {
    graph.edge("A", "B")
      .edge("A", "C")
      .edge("B", "D")
      .edge("C", "D");

    if(graph.isDirected()) {
      return arguments("split join", graph, List.of("D", "B", "C", "A"));
    } else {
      return arguments("split join", graph, List.of("C", "D", "B", "A"));
    }
  }

  private static Arguments threeSplit(Graph<String> graph) {
    graph.edge("A", "B")
      .edge("A", "C");
    return arguments("three split", graph, List.of("B", "C", "A"));
  }

  private static Arguments threeLinked(Graph<String> graph) {
    graph.edge("A", "B")
      .edge("B", "C");
    return arguments("three linked", graph, List.of("C", "B", "A"));
  }

  private static Arguments twoLinked(Graph<String> graph) {
    graph.edge("A", "B");
    return arguments("two linked", graph, List.of("B", "A"));
  }

  private static Arguments three(Graph<String> graph) {
    graph.vertex("A")
      .vertex("B")
      .vertex("C");
    return arguments("two", graph, List.of("A", "B", "C"));
  }

  private static Arguments two(Graph<String> graph) {
    graph.vertex("A")
      .vertex("B");
    return arguments("two", graph, List.of("A", "B"));
  }

  private static Arguments one(Graph<String> graph) {
    graph.vertex("A");
    return arguments("one", graph, List.of("A"));
  }
}
