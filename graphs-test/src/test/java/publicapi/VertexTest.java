package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.UndirectedLinkedGraph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VertexTest {

  @SimpleGraphs
  void inheritedDefault(Graph<String> graph) {
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withInherited().isEmpty();
  }

  @SimpleGraphs
  void inheritedWithProperty(Graph<String> graph) {
    graph.vertex("A");
    graph.vertexProperty("color", "blue");
    assertThat(graph).hasVertex("A").withInherited().containsExactly("color", "blue");
  }

  @SimpleGraphs
  void localWithProperty(Graph<String> graph) {
    graph.getVertex("A").property("color", "blue");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "color", "blue");
  }

  @SimpleGraphs
  void getPropertyInherited(Graph<String> graph) {
    graph.vertexProperty("color", "blue");
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
  }

  @SimpleGraphs
  void getPropertyLocal(Graph<String> graph) {
    graph.getVertex("A").property("color", "blue");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
  }

  @SimpleGraphs
  void getPropertyDefault(Graph<String> graph) {
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withProperty("color").isEmpty();
  }

  @SimpleGraphs
  void setPropertyNullName(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").setProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @SimpleGraphs
  void setPropertyNullValue(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").setProperty("name", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void setPropertyEmptyName(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").setProperty("", ""));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @SimpleGraphs
  void setProperty(Graph<String> graph) {
    graph.getVertex("A").setProperty("name", "value");
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @SimpleGraphs
  void setPropertyUpdateToNull(Graph<String> graph) {
    graph.getVertex("A").setProperty("name", "value");
    graph.getVertex("A").setProperty("name", null);
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
  }

  @SimpleGraphs
  void property(Graph<String> graph) {
    graph.getVertex("A").property("name", "value");
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @SimpleGraphs
  void property2(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @SimpleGraphs
  void property3(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @SimpleGraphs
  void property4(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @SimpleGraphs
  void property5(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @SimpleGraphs
  void property6(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @SimpleGraphs
  void property7(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @SimpleGraphs
  void property8(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @SimpleGraphs
  void property9(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @SimpleGraphs
  void property10(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @SimpleGraphs
  void propertyWithMap(Graph<String> graph) {
    graph.getVertex("A").property(Map.of("name", "value"));
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @SimpleGraphs
  void property1UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name", "value");
    graph.getVertex("A").property("name", null);
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
  }

  @SimpleGraphs
  void property2UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name2", "value2");
    graph.getVertex("A").property("name1", "value1", "name2", null);
    assertThat(graph).hasVertex("A").withProperty("name2").isEmpty();
  }

  @SimpleGraphs
  void property3UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name3", "value3");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", null);
    assertThat(graph).hasVertex("A").withProperty("name3").isEmpty();
  }

  @SimpleGraphs
  void property4UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name4", "value4");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", null);
    assertThat(graph).hasVertex("A").withProperty("name4").isEmpty();
  }

  @SimpleGraphs
  void property5UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name5", "value5");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", null);
    assertThat(graph).hasVertex("A").withProperty("name5").isEmpty();
  }

  @SimpleGraphs
  void property6UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name6", "value6");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", null);
    assertThat(graph).hasVertex("A").withProperty("name6").isEmpty();
  }

  @SimpleGraphs
  void property7UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name7", "value7");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", null);
    assertThat(graph).hasVertex("A").withProperty("name7").isEmpty();
  }

  @SimpleGraphs
  void property8UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name8", "value8");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", null);
    assertThat(graph).hasVertex("A").withProperty("name8").isEmpty();
  }

  @SimpleGraphs
  void property9UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name9", "value9");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", null);
    assertThat(graph).hasVertex("A").withProperty("name9").isEmpty();
  }

  @SimpleGraphs
  void property10UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name10", "value10");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", null);
    assertThat(graph).hasVertex("A").withProperty("name10").isEmpty();
  }

  @SimpleGraphs
  void propertySetIdFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").property("id", "B"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @SimpleGraphs
  void removePropertyIdFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").removeProperty("id"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @SimpleGraphs
  void removePropertyNullName(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").removeProperty(null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @SimpleGraphs
  void removePropertyNameMissing(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").removeProperty("name"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @SimpleGraphs
  void removeProperty(Graph<String> graph) {
    graph.getVertex("A").property("name", "value");
    var result = graph.getVertex("A").removeProperty("name");
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
    assertThat(result).isSameInstanceAs(graph.getVertex("A"));
  }

  @SimpleGraphs
  void testSetId(Graph<String> graph) {
    var a = graph.getVertex("id");
    var from = graph.getEdge("id", "b");
    var to = graph.getEdge("c", "id");
    a.setId("a");

    assertThat(a.getId()).isEqualTo("a");
    assertThat(from.getFrom()).isEqualTo("a");
    assertThat(to.getTo()).isEqualTo("a");
  }

  @SimpleGraphs
  void testSetIdAlreadyExists(Graph<String> graph) {
    graph.getVertex("a").property("key", "value");
    var vertex = graph.getVertex("id");
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> vertex.id("a"));
    assertThat(ex).hasMessageThat().isEqualTo("vertex with id a already exists.");
  }

  @SimpleGraphs
  void edgeAfterSetId(Graph<String> graph) {
    graph.edge("id", "b");
    graph.edge("c", "id");
    graph.getVertex("id").setId("a");

    assertThat(graph).hasEdge("a", "b");
    assertThat(graph).hasEdge("c", "a");
  }

  @SimpleGraphs
  void testId(Graph<String> graph) {
    var vertex = graph.getVertex("A").id("B");
    assertThat(vertex).hasId("B");
    assertThat(vertex).isSameInstanceAs(graph.getVertex("B"));
  }

  @SimpleGraphs
  void testAdjacentEdges(Graph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "C");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("A");

    var result = vertex.adjacentEdges();
    assertThat(result).containsExactly(edge1, edge2).inOrder();
  }

  @SimpleGraphs
  void adjacentEdgesEmpty(Graph<String> graph) {
    assertThat(graph.getVertex("A").adjacentEdges()).isEmpty();
  }

  @SimpleGraphs
  void adjacentEdgesAfterSetId(Graph<String> graph) {
    var edge1 = graph.getEdge("id", "B");
    var edge2 = graph.getEdge("id", "C");
    var edge3 = graph.getEdge("D", "id");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.adjacentEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @SimpleGraphs
  void testConnectsTo(Graph<String> graph) {
    var vertex = graph.getVertex("A")
      .connectsTo("B");
    assertThat(graph).hasVertex("B");
    assertThat(graph).hasEdge("A", "B");
  }

  @SimpleGraphs
  void testConnectsFrom(Graph<String> graph) {
    var vertex = graph.getVertex("A")
      .connectsFrom("B");
    assertThat(graph).hasVertex("B");
    assertThat(graph).hasEdge("B", "A");
    var optional = graph.findEdge("B", "A");
    assertThat(optional).isPresent();
    var edge = optional.orElse(null);
    assertThat(edge).hasFromThat().isEqualTo("B");
    assertThat(edge).hasToThat().isEqualTo("A");
    assertThat(vertex.getId()).isEqualTo("A");
  }

  @SimpleGraphs
  void inEdges(Graph<String> graph) {
    var edge1 = graph.getEdge("B", "A");
    var edge2 = graph.getEdge("C", "A");
    var edge3 = graph.getEdge("D", "A");
    var result = graph.getVertex("A").inEdges();

    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @SimpleGraphs
  void inEdgesAfterSetId(Graph<String> graph) {
    var edge1 = graph.getEdge("B", "id");
    var edge2 = graph.getEdge("C", "id");
    var edge3 = graph.getEdge("D", "id");
    graph.edge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.inEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @SimpleGraphs
  void inEdgesEmpty(Graph<String> graph) {
    assertThat(graph.getVertex("A").inEdges()).isEmpty();
  }

  @SimpleGraphs
  void outEdges(Graph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "C");
    var edge3 = graph.getEdge("A", "D");
    var result = graph.getVertex("A").outEdges();

    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @SimpleGraphs
  void outEdgesAfterSetId(Graph<String> graph) {
    var edge1 = graph.getEdge("id", "B");
    var edge2 = graph.getEdge("id", "C");
    var edge3 = graph.getEdge("id", "D");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.outEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @SimpleGraphs
  void outEdgesEmpty(Graph<String> graph) {
    assertThat(graph.getVertex("A").outEdges()).isEmpty();
  }

  @SimpleGraphs
  void testEquals(Graph<String> graph) {
    var vertex = graph.getVertex("A");
    EqualsVerifier.forClass(vertex.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
  }

  @SimpleGraphs
  void testEqualsDifferentInherited(Graph<String> graph) {
    var other = new UndirectedLinkedGraph<String>();
    other.vertexProperty("name", "value");
    var vertex1 = graph.getVertex("A");
    var vertex2 = other.getVertex("A");

    assertThat(vertex1.equals(vertex2)).isFalse();
  }
}
