package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import com.google.common.truth.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyGraphProperties {

  @TestPropertyGraphs
  void defaultId(PropertyGraph<String> graph) {
    assertThat(graph).hasIdThat().isEmpty();
  }

  @TestPropertyGraphs
  void setId(PropertyGraph<String> graph) {
    graph.setId("id");
    assertThat(graph).hasIdThat().hasValue("id");
  }

  @TestPropertyGraphs
  void setIdNull(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setId(null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestPropertyGraphs
  void id(PropertyGraph<String> graph) {
    PropertyGraph next = graph.id("id");
    assertThat(graph).hasIdThat().hasValue("id");
    assertThat(graph).isSameInstanceAs(next);
  }

  @TestPropertyGraphs
  void defaultProperty(PropertyGraph<String> graph) {
    assertThat(graph).withProperty("property").isEmpty();
  }

  @TestPropertyGraphs
  void setPropertyNullKey(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestPropertyGraphs
  void setPropertyNullValue(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty("property", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestPropertyGraphs
  void setPorpertyEmptyName(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setProperty("", "value"));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @TestPropertyGraphs
  void setProperty(PropertyGraph<String> graph) {
    graph.setProperty("property", "value");
    assertThat(graph).withProperty("property").hasValue("value");
  }

  @TestPropertyGraphs
  void setPropertyUpdateToNull(PropertyGraph<String> graph) {
    graph.setProperty("name", "value");
    graph.setProperty("name", null);
    assertThat(graph).withProperty("name").isEmpty();
  }

  @TestPropertyGraphs
  void setPropertyIdFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.setProperty("id", "graph"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestPropertyGraphs
  void property(PropertyGraph<String> graph) {
    PropertyGraph next = graph.property("property", "value");
    assertThat(graph).withProperty("property").hasValue("value");
    assertThat(next).isSameInstanceAs(graph);
  }

  @TestPropertyGraphs
  void removePropertyMissing(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeProperty("property"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestPropertyGraphs
  void removeProperty(PropertyGraph<String> graph) {
    graph.property("property", "value");
    graph.removeProperty("property");
    assertThat(graph).withProperty("property").isEmpty();
  }

  @TestPropertyGraphs
  void getProperties(PropertyGraph<String> graph) {
    graph.property("property", "value");
    assertThat(graph.getProperties()).containsExactly("property", "value");
  }

  @TestPropertyGraphs
  void findPropertyNull(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.findProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestPropertyGraphs
  void findProperty(PropertyGraph<String> graph) {
    graph.setProperty("property", "value");
    Optional<String> value = graph.findProperty("property");
    Truth8.assertThat(value).hasValue("value");
  }

  @TestPropertyGraphs
  void getPropertyNull(PropertyGraph<String> graph) {
    var exception = assertThrows(NullPointerException.class, () -> graph.getProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestPropertyGraphs
  void getProperty(PropertyGraph<String> graph) {
    graph.setProperty("property", "value");
    String value = graph.getProperty("property");
    assertThat(value).isEqualTo("value");
  }
}
