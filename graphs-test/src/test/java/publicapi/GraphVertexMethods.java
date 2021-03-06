package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.TestGraphs;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphVertexMethods {

  @TestGraphs
  void vertex(Graph<String> graph) {
    Graph<String> r = graph.vertex("A");
    assertThat(r).isSameInstanceAs(graph);
    assertThat(graph).hasVertex("A");
  }

  @TestGraphs
  void getVertex(Graph<String> graph) {
    var vertex = graph.getVertex("id");
    assertThat(graph).hasVertex("id").isSameInstanceAs(vertex);
    assertThat(vertex).hasId("id");
  }

  @TestGraphs
  void getVertexExisting(Graph<String> graph) {
    var vertexA = graph.getVertex("A");
    var vertexB = graph.getVertex("A");
    assertThat(vertexA).isSameInstanceAs(vertexB);
  }

  @TestGraphs
  void getVertexIdIsSet(Graph<String> graph) {
    var vertex = graph.getVertex("A");
    assertThat(vertex).hasId("A");
  }

  @TestGraphs
  void getVertexSetIdAsPropertyFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A", "id", "B"));

    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void getVertexExistsSetId(Graph<String> graph) {
    graph.getVertex("A");
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A", "id", "B"));

    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void getVertexProperty1(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1");
  }

  @TestGraphs
  void getVertexProperty1Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1");
  }

  @TestGraphs
  void getVertexProperty2(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void getVertexProperty2Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void getVertexProperty3(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void getVertexProperty3Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void getVertexProperty4(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void getVertexProperty4Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void getVertexProperty5(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void getVertexProperty5Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void getVertexProperty6(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void getVertexProperty6Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void getVertexProperty7(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void getVertexProperty7Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void getVertexProperty8(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void getVertexProperty8Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void getVertexProperty9(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void getVertexProperty9Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void getVertexProperty10(Graph<String> graph) {
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void getVertexProperty10Exists(Graph<String> graph) {
    graph.getVertex("A");
    graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void vertexWithProperty1(Graph<String> graph) {
    graph.vertex("A", "name1", "value1");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1");
  }

  @TestGraphs
  void vertexWithProperty1Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1");
  }

  @TestGraphs
  void vertexWithProperty2(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void vertexWithProperty2Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void vertexWithProperty3(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void vertexWithProperty3Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void vertexWithProperty4(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void vertexWithProperty4Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void vertexWithProperty5(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void vertexWithProperty5Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void vertexWithProperty6(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void vertexWithProperty6Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void vertexWithProperty7(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void vertexWithProperty7Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void vertexWithProperty8(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void vertexWithProperty8Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void vertexWithProperty9(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void vertexWithProperty9Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void vertexWithProperty10(Graph<String> graph) {
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void vertexWithProperty10Exists(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void vertexWithMapProperties(Graph<String> graph) {
    graph.vertex("A", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
    assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void getVertexWithMapProperties(Graph<String> graph) {
    var vertex = graph.getVertex("A", Map.of("name1", "value1", "name2", "value2"));

    assertThat(vertex).withProperty("name1").hasValue("value1");
    assertThat(vertex).withProperty("name2").hasValue("value2");
    assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
    assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void vertexWithMapPropertiesExisting(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("A", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
    assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void getVertexWithMapPropertiesExisting(Graph<String> graph) {
    graph.vertex("A");
    graph.getVertex("A", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
    assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void removeVertex(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "C");
    graph.edge("Z", "Y");

    graph.removeVertex("A");

    assertThat(graph).hasNoVertex("A");
    assertThat(graph).hasNoEdge("A", "B");
    assertThat(graph).hasNoEdge("A", "C");
    assertThat(graph).hasEdge("Z", "Y");
  }

  @TestGraphs
  void removeVertexThatDoesNotExist(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeVertex("A"));
    assertThat(thrown).hasMessageThat().isEqualTo("vertex 'A' not found.");
  }

  @TestGraphs
  void removeVertexWithNullId(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeVertex(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }
}
