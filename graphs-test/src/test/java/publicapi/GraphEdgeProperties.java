package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GraphEdgeProperties {

  @SimpleGraphs
  void defaultProperty(Graph graph) {
    assertThat(graph).withEdgeProperty("property").isEmpty();
  }

  @SimpleGraphs
  void setPropertyNullKey(Graph graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @SimpleGraphs
  void setPropertyNullValue(Graph graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty("property", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void setPropertyEmptyName(Graph graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setEdgeProperty("", ""));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @SimpleGraphs
  void setProperty(Graph graph) {
    graph.setEdgeProperty("property", "value");
    assertThat(graph).withEdgeProperty("property").hasValue("value");
  }

  @SimpleGraphs
  void property(Graph graph) {
    Graph next = graph.edgeProperty("property", "value");
    assertThat(graph).withEdgeProperty("property").hasValue("value");
    assertThat(next).isSameInstanceAs(graph);
  }

  @SimpleGraphs
  void removePropertyMissing(Graph graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdgeProperty("property"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void removeProperty(Graph graph) {
    graph.edgeProperty("property", "value");
    graph.removeEdgeProperty("property");
    assertThat(graph).withEdgeProperty("property").isEmpty();
  }

  @SimpleGraphs
  void propertyIsInherited(Graph graph) {
    graph.edgeProperty("property", "value");
    var edge = graph.getEdge("A", "B");
    assertThat(edge).withProperty("property").hasValue("value");
  }

  @SimpleGraphs
  void getProperties(Graph graph) {
    graph.edgeProperty("property", "value");
    assertThat(graph.getEdgeProperties()).containsExactly("property", "value");
  }

  @SimpleGraphs
  void findProperty(Graph graph) {
    graph.setProperty("property", "value");
    Optional<String> value = graph.findProperty("property");
    assertThat(value).hasValue("value");
  }

  @SimpleGraphs
  void getProperty(Graph graph) {
    graph.setProperty("property", "value");
    String value = graph.getProperty("property");
    assertThat(value).isEqualTo("value");
  }
}
