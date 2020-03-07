package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GraphEdgeProperties {

  @SimpleGraphs
  void defaultProperty(Graph<String> graph) {
    assertThat(graph).withEdgeProperty("property").isEmpty();
  }

  @SimpleGraphs
  void setPropertyNullKey(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @SimpleGraphs
  void setPropertyNullValue(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty("property", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void setPropertyEmptyName(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setEdgeProperty("", ""));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @SimpleGraphs
  void setProperty(Graph<String> graph) {
    graph.setEdgeProperty("property", "value");
    assertThat(graph).withEdgeProperty("property").hasValue("value");
  }

  @SimpleGraphs
  void property(Graph<String> graph) {
    Graph next = graph.edgeProperty("property", "value");
    assertThat(graph).withEdgeProperty("property").hasValue("value");
    assertThat(next).isSameInstanceAs(graph);
  }

  @SimpleGraphs
  void removePropertyMissing(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdgeProperty("property"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void removeProperty(Graph<String> graph) {
    graph.edgeProperty("property", "value");
    graph.removeEdgeProperty("property");
    assertThat(graph).withEdgeProperty("property").isEmpty();
  }

  @SimpleGraphs
  void propertyIsInherited(Graph<String> graph) {
    graph.edgeProperty("property", "value");
    var edge = graph.getEdge("A", "B");
    assertThat(edge).withProperty("property").hasValue("value");
  }

  @SimpleGraphs
  void getProperties(Graph<String> graph) {
    graph.edgeProperty("property", "value");
    assertThat(graph.getEdgeProperties()).containsExactly("property", "value");
  }
}
