package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import com.google.common.truth.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GraphProperties {

  @TestGraphs
  void defaultId(Graph<String> graph) {
    assertThat(graph).hasIdThat().isEmpty();
  }

  @TestGraphs
  void setId(Graph<String> graph) {
    graph.setId("id");
    assertThat(graph).hasIdThat().hasValue("id");
  }

  @TestGraphs
  void setIdNull(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setId(null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void id(Graph<String> graph) {
    Graph next = graph.id("id");
    assertThat(graph).hasIdThat().hasValue("id");
    assertThat(graph).isSameInstanceAs(next);
  }

  @TestGraphs
  void defaultProperty(Graph<String> graph) {
    assertThat(graph).withProperty("property").isEmpty();
  }

  @TestGraphs
  void setPropertyNullKey(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void setPropertyNullValue(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty("property", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void setPorpertyEmptyName(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setProperty("", "value"));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @TestGraphs
  void setProperty(Graph<String> graph) {
    graph.setProperty("property", "value");
    assertThat(graph).withProperty("property").hasValue("value");
  }

  @TestGraphs
  void setPropertyUpdateToNull(Graph<String> graph) {
    graph.setProperty("name", "value");
    graph.setProperty("name", null);
    assertThat(graph).withProperty("name").isEmpty();
  }

  @TestGraphs
  void setPropertyIdFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.setProperty("id", "graph"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void property(Graph<String> graph) {
    Graph next = graph.property("property", "value");
    assertThat(graph).withProperty("property").hasValue("value");
    assertThat(next).isSameInstanceAs(graph);
  }

  @TestGraphs
  void removePropertyMissing(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeProperty("property"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void removeProperty(Graph<String> graph) {
    graph.property("property", "value");
    graph.removeProperty("property");
    assertThat(graph).withProperty("property").isEmpty();
  }

  @TestGraphs
  void getProperties(Graph<String> graph) {
    graph.property("property", "value");
    assertThat(graph.getProperties()).containsExactly("property", "value");
  }

  @TestGraphs
  void findPropertyNull(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.findProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void findProperty(Graph<String> graph) {
    graph.setProperty("property", "value");
    Optional<String> value = graph.findProperty("property");
    Truth8.assertThat(value).hasValue("value");
  }

  @TestGraphs
  void getPropertyNull(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.getProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void getProperty(Graph<String> graph) {
    graph.setProperty("property", "value");
    String value = graph.getProperty("property");
    assertThat(value).isEqualTo("value");
  }
}
