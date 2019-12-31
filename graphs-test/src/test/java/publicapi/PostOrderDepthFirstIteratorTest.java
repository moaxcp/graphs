package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.*;
import static com.google.common.truth.Truth.*;
import static org.junit.jupiter.api.Assertions.*;

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

  @SimpleGraphs
  void next_OneVertex(Graph<String> graph) {
    graph.vertex("A");
    var iterator = graph.postOrderIterator();
    var vertex = iterator.next();
    assertThat(vertex).hasId("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @SimpleGraphs
  void next_TwoVertex(Graph<String> graph) {
    graph.edge("A", "B");
    var iterator = graph.postOrderIterator();
    var b = iterator.next();
    assertThat(b).hasId("B");
    assertThat(iterator.hasNext()).isTrue();
    var a = iterator.next();
    assertThat(a).hasId("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @SimpleGraphs
  void next_ThreeVertex(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("B", "C");
    var iterator = graph.postOrderIterator();
    var c = iterator.next();
    assertThat(c).hasId("C");
    assertThat(iterator.hasNext()).isTrue();
    var b = iterator.next();
    assertThat(b).hasId("B");
    assertThat(iterator.hasNext()).isTrue();
    var a = iterator.next();
    assertThat(a).hasId("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @SimpleGraphs
  void next_ThreeVertexSplit(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "C");
    var iterator = graph.postOrderIterator();
    var b = iterator.next();
    assertThat(b).hasId("B");
    assertThat(iterator.hasNext()).isTrue();
    var c = iterator.next();
    assertThat(c).hasId("C");
    assertThat(iterator.hasNext()).isTrue();
    var a = iterator.next();
    assertThat(a).hasId("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @UndirectedSimpleGraphs
  void next_UndirectedThreeVertexSplitJoin(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "C");
    graph.edge("B", "D");
    graph.edge("C", "D");
    var iterator = graph.postOrderIterator();
    var c = iterator.next();
    assertThat(c).hasId("C");
    assertThat(iterator.hasNext()).isTrue();
    var d = iterator.next();
    assertThat(d).hasId("D");
    assertThat(iterator.hasNext()).isTrue();
    var b = iterator.next();
    assertThat(b).hasId("B");
    assertThat(iterator.hasNext()).isTrue();
    var a = iterator.next();
    assertThat(a).hasId("A");
    assertThat(iterator.hasNext()).isFalse();
  }

  @DirectedSimpleGraphs
  void next_DirectedThreeVertexSplitJoin(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "C");
    graph.edge("B", "D");
    graph.edge("C", "D");
    var iterator = graph.postOrderIterator();
    var d = iterator.next();
    assertThat(d).hasId("D");
    assertThat(iterator.hasNext()).isTrue();
    var b = iterator.next();
    assertThat(b).hasId("B");
    assertThat(iterator.hasNext()).isTrue();
    var c = iterator.next();
    assertThat(c).hasId("C");
    assertThat(iterator.hasNext()).isTrue();
    var a = iterator.next();
    assertThat(a).hasId("A");
    assertThat(iterator.hasNext()).isFalse();
  }
}
