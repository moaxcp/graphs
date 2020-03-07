package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import com.google.common.truth.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GraphProperties {

  @SimpleGraphs
  void defaultId(Graph<String> graph) {
    assertThat(graph).hasIdThat().isEmpty();
  }

  @SimpleGraphs
  void setId(Graph<String> graph) {
    graph.setId("id");
    assertThat(graph).hasIdThat().hasValue("id");
  }

  @SimpleGraphs
  void setIdNull(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setId(null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void id(Graph<String> graph) {
    Graph next = graph.id("id");
    assertThat(graph).hasIdThat().hasValue("id");
    assertThat(graph).isSameInstanceAs(next);
  }

  @SimpleGraphs
  void defaultProperty(Graph<String> graph) {
    assertThat(graph).withProperty("property").isEmpty();
  }

  @SimpleGraphs
  void setPropertyNullKey(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @SimpleGraphs
  void setPropertyNullValue(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty("property", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void setPorpertyEmptyName(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setProperty("", "value"));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @SimpleGraphs
  void setProperty(Graph<String> graph) {
    graph.setProperty("property", "value");
    assertThat(graph).withProperty("property").hasValue("value");
  }

  @SimpleGraphs
  void setPropertyUpdateToNull(Graph<String> graph) {
    graph.setProperty("name", "value");
    graph.setProperty("name", null);
    assertThat(graph).withProperty("name").isEmpty();
  }

  @SimpleGraphs
  void setPropertyIdFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.setProperty("id", "graph"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @SimpleGraphs
  void property(Graph<String> graph) {
    Graph next = graph.property("property", "value");
    assertThat(graph).withProperty("property").hasValue("value");
    assertThat(next).isSameInstanceAs(graph);
  }

  @SimpleGraphs
  void removePropertyMissing(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeProperty("property"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void removeProperty(Graph<String> graph) {
    graph.property("property", "value");
    graph.removeProperty("property");
    assertThat(graph).withProperty("property").isEmpty();
  }

  @SimpleGraphs
  void getProperties(Graph<String> graph) {
    graph.property("property", "value");
    assertThat(graph.getProperties()).containsExactly("property", "value");
  }

  @SimpleGraphs
  void findPropertyNull(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.findProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @SimpleGraphs
  void findProperty(Graph<String> graph) {
    graph.setProperty("property", "value");
    Optional<String> value = graph.findProperty("property");
    Truth8.assertThat(value).hasValue("value");
  }

  @SimpleGraphs
  void getPropertyNull(Graph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.getProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @SimpleGraphs
  void getProperty(Graph<String> graph) {
    graph.setProperty("property", "value");
    String value = graph.getProperty("property");
    assertThat(value).isEqualTo("value");
  }
}
