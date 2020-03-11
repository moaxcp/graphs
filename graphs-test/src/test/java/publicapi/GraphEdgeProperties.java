package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import com.google.common.truth.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GraphEdgeProperties {

  @TestGraphs
  void defaultProperty(Graph<String> graph) {
    assertThat(graph).withEdgeProperty("property").isEmpty();
  }

  @TestGraphs
  void setPropertyNullKey(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void setPropertyNullValue(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty("property", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void setPropertyEmptyName(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setEdgeProperty("", ""));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @TestGraphs
  void setProperty(Graph<String> graph) {
    graph.setEdgeProperty("property", "value");
    assertThat(graph).withEdgeProperty("property").hasValue("value");
  }

  @TestGraphs
  void property(Graph<String> graph) {
    Graph next = graph.edgeProperty("property", "value");
    assertThat(graph).withEdgeProperty("property").hasValue("value");
    assertThat(next).isSameInstanceAs(graph);
  }

  @TestGraphs
  void removePropertyMissing(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdgeProperty("property"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void removeProperty(Graph<String> graph) {
    graph.edgeProperty("property", "value");
    graph.removeEdgeProperty("property");
    assertThat(graph).withEdgeProperty("property").isEmpty();
  }

  @TestGraphs
  void propertyIsInherited(Graph<String> graph) {
    graph.edgeProperty("property", "value");
    var edge = graph.getEdge("A", "B");
    assertThat(edge).withProperty("property").hasValue("value");
  }

  @TestGraphs
  void getProperties(Graph<String> graph) {
    graph.edgeProperty("property", "value");
    assertThat(graph.getEdgeProperties()).containsExactly("property", "value");
  }

  @TestGraphs
  void findPropertyNull(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.getEdgeProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void findProperty(Graph<String> graph) {
    graph.edgeProperty("name", "value");
    Optional<String> result = graph.findEdgeProperty("name");
    Truth8.assertThat(result).hasValue("value");
  }

  @TestGraphs
  void getPropertyNull(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.getEdgeProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void getProperty(Graph<String> graph) {
    graph.setEdgeProperty("name", "value");
    String value = graph.getEdgeProperty("name");
    assertThat(value).isEqualTo("value");
  }
}
