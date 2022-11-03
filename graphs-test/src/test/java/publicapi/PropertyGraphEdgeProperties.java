package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import com.google.common.truth.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyGraphEdgeProperties {

  @TestPropertyGraphs
  void defaultProperty(PropertyGraph<String> graph) {
    assertThat(graph).withEdgeProperty("property").isEmpty();
  }

  @TestPropertyGraphs
  void setPropertyNullKey(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestPropertyGraphs
  void setPropertyNullValue(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty("property", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestPropertyGraphs
  void setPropertyEmptyName(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setEdgeProperty("", ""));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @TestPropertyGraphs
  void setProperty(PropertyGraph<String> graph) {
    graph.setEdgeProperty("property", "value");
    assertThat(graph).withEdgeProperty("property").hasValue("value");
  }

  @TestPropertyGraphs
  void property(PropertyGraph<String> graph) {
    PropertyGraph next = graph.edgeProperty("property", "value");
    assertThat(graph).withEdgeProperty("property").hasValue("value");
    assertThat(next).isSameInstanceAs(graph);
  }

  @TestPropertyGraphs
  void removePropertyMissing(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdgeProperty("property"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestPropertyGraphs
  void removeProperty(PropertyGraph<String> graph) {
    graph.edgeProperty("property", "value");
    graph.removeEdgeProperty("property");
    assertThat(graph).withEdgeProperty("property").isEmpty();
  }

  @TestPropertyGraphs
  void propertyIsInherited(PropertyGraph<String> graph) {
    graph.edgeProperty("property", "value");
    var edge = graph.getEdge("A", "B");
    assertThat(edge).withProperty("property").hasValue("value");
  }

  @TestPropertyGraphs
  void getProperties(PropertyGraph<String> graph) {
    graph.edgeProperty("property", "value");
    assertThat(graph.getEdgeProperties()).containsExactly("property", "value");
  }

  @TestPropertyGraphs
  void findPropertyNull(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.getEdgeProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestPropertyGraphs
  void findProperty(PropertyGraph<String> graph) {
    graph.edgeProperty("name", "value");
    Optional<String> result = graph.findEdgeProperty("name");
    Truth8.assertThat(result).hasValue("value");
  }

  @TestPropertyGraphs
  void getPropertyNull(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.getEdgeProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestPropertyGraphs
  void getProperty(PropertyGraph<String> graph) {
    graph.setEdgeProperty("name", "value");
    String value = graph.getEdgeProperty("name");
    assertThat(value).isEqualTo("value");
  }
}
