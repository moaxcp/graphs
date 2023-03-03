package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestPropertyGraphs;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyGraphVertexMethods {

  @TestPropertyGraphs
  void vertex(PropertyGraph<String> graph) {
    PropertyGraph<String> r = graph.vertex("A");
    assertThat(r).isSameInstanceAs(graph);
    assertThat(graph).hasVertex("A");
  }

  @TestPropertyGraphs
  void getVertex(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("id");
    assertThat(graph).hasVertex("id").isSameInstanceAs(vertex);
    assertThat(vertex).hasId("id");
  }

  @TestPropertyGraphs
  void getVertexExisting(PropertyGraph<String> graph) {
    var vertexA = graph.getVertex("A");
    var vertexB = graph.getVertex("A");
    assertThat(vertexA).isSameInstanceAs(vertexB);
  }

  @TestPropertyGraphs
  void getVertexIdIsSet(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A");
    assertThat(vertex).hasId("A");
  }

  @TestPropertyGraphs
  void getVertexSetIdAsPropertyFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A", "id", "B"));

    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestPropertyGraphs
  void getVertexExistsSetId(PropertyGraph<String> graph) {
    graph.getVertex("A");
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A", "id", "B"));

    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestPropertyGraphs
  void getVertexProperty1(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1");
  }

  @TestPropertyGraphs
  void getVertexProperty1Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1");
  }

  @TestPropertyGraphs
  void getVertexProperty2(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestPropertyGraphs
  void getVertexProperty2Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestPropertyGraphs
  void getVertexProperty3(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestPropertyGraphs
  void getVertexProperty3Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestPropertyGraphs
  void getVertexProperty4(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestPropertyGraphs
  void getVertexProperty4Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestPropertyGraphs
  void getVertexProperty5(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestPropertyGraphs
  void getVertexProperty5Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestPropertyGraphs
  void getVertexProperty6(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestPropertyGraphs
  void getVertexProperty6Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestPropertyGraphs
  void getVertexProperty7(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestPropertyGraphs
  void getVertexProperty7Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestPropertyGraphs
  void getVertexProperty8(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestPropertyGraphs
  void getVertexProperty8Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestPropertyGraphs
  void getVertexProperty9(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestPropertyGraphs
  void getVertexProperty9Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestPropertyGraphs
  void getVertexProperty10(PropertyGraph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestPropertyGraphs
  void getVertexProperty10Exists(PropertyGraph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestPropertyGraphs
  void vertexWithProperty1(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1");
  }

  @TestPropertyGraphs
  void vertexWithProperty1Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1");
  }

  @TestPropertyGraphs
  void vertexWithProperty2(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestPropertyGraphs
  void vertexWithProperty2Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestPropertyGraphs
  void vertexWithProperty3(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestPropertyGraphs
  void vertexWithProperty3Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestPropertyGraphs
  void vertexWithProperty4(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestPropertyGraphs
  void vertexWithProperty4Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestPropertyGraphs
  void vertexWithProperty5(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestPropertyGraphs
  void vertexWithProperty5Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestPropertyGraphs
  void vertexWithProperty6(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestPropertyGraphs
  void vertexWithProperty6Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestPropertyGraphs
  void vertexWithProperty7(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestPropertyGraphs
  void vertexWithProperty7Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestPropertyGraphs
  void vertexWithProperty8(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestPropertyGraphs
  void vertexWithProperty8Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestPropertyGraphs
  void vertexWithProperty9(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestPropertyGraphs
  void vertexWithProperty9Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestPropertyGraphs
  void vertexWithProperty10(PropertyGraph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestPropertyGraphs
  void vertexWithProperty10Exists(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestPropertyGraphs
  void vertexWithMapProperties(PropertyGraph<String> graph) {
    graph.vertex("A", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
    assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
  }

  @TestPropertyGraphs
  void getVertexWithMapProperties(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A", Map.of("name1", "value1", "name2", "value2"));

    assertThat(vertex).withProperty("name1").hasValue("value1");
    assertThat(vertex).withProperty("name2").hasValue("value2");
    assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
    assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
  }

  @TestPropertyGraphs
  void vertexWithMapPropertiesExisting(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
    assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
  }

  @TestPropertyGraphs
  void getVertexWithMapPropertiesExisting(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.getVertex("A", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
    assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
  }

  @TestPropertyGraphs
  void removeVertex(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "C");
    graph.edge("Z", "Y");

    graph.removeVertex("A");

    assertThat(graph).hasNoVertex("A");
    assertThat(graph).hasNoEdge("A", "B");
    assertThat(graph).hasNoEdge("A", "C");
    assertThat(graph).hasEdge("Z", "Y");
  }

  @TestPropertyGraphs
  void removeVertexThatDoesNotExist(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeVertex("A"));
    assertThat(thrown).hasMessageThat().isEqualTo("vertex 'A' not found.");
  }

  @TestPropertyGraphs
  void removeVertexWithNullId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeVertex(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }
}
