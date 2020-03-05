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
  void defaultId(Graph graph) {
    assertThat(graph).hasIdThat().isEmpty();
  }

  @SimpleGraphs
  void setId(Graph graph) {
    graph.setId("id");
    assertThat(graph).hasIdThat().hasValue("id");
  }

  @SimpleGraphs
  void setIdNull(Graph graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setId(null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void id(Graph graph) {
    Graph next = graph.id("id");
    assertThat(graph).hasIdThat().hasValue("id");
    assertThat(graph).isSameInstanceAs(next);
  }

  @SimpleGraphs
  void defaultProperty(Graph graph) {
    assertThat(graph).withProperty("property").isEmpty();
  }

  @SimpleGraphs
  void setPropertyNullKey(Graph graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @SimpleGraphs
  void setPropertyNullValue(Graph graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty("property", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void setPorpertyEmptyName(Graph graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setProperty("", "value"));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @SimpleGraphs
  void setProperty(Graph graph) {
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
  void property(Graph graph) {
    Graph next = graph.property("property", "value");
    assertThat(graph).withProperty("property").hasValue("value");
    assertThat(next).isSameInstanceAs(graph);
  }

  @SimpleGraphs
  void removePropertyMissing(Graph graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeProperty("property"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void removeProperty(Graph graph) {
    graph.property("property", "value");
    graph.removeProperty("property");
    assertThat(graph).withProperty("property").isEmpty();
  }

  @SimpleGraphs
  void getProperties(Graph graph) {
    graph.property("property", "value");
    assertThat(graph.getProperties()).containsExactly("property", "value");
  }

  @SimpleGraphs
  void findProperty(Graph graph) {
    graph.setProperty("property", "value");
    Optional<String> value = graph.findProperty("property");
    Truth8.assertThat(value).hasValue("value");
  }

  @SimpleGraphs
  void getProperty(Graph graph) {
    graph.setProperty("property", "value");
    String value = graph.getProperty("property");
    assertThat(value).isEqualTo("value");
  }
}
