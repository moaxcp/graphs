package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import nl.jqno.equalsverifier.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class VertexTest {

  @TestGraphs
  void inheritedDefault(Graph<String> graph) {
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withInherited().isEmpty();
  }

  @TestGraphs
  void inheritedWithProperty(Graph<String> graph) {
    graph.vertex("A");
    graph.vertexProperty("color", "blue");
    assertThat(graph).hasVertex("A").withInherited().containsExactly("color", "blue");
  }

  @TestGraphs
  void localWithProperty(Graph<String> graph) {
    graph.getVertex("A").property("color", "blue");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "color", "blue");
  }

  @TestGraphs
  void getPropertyInherited(Graph<String> graph) {
    graph.vertexProperty("color", "blue");
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
  }

  @TestGraphs
  void getPropertyLocal(Graph<String> graph) {
    graph.getVertex("A").property("color", "blue");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
  }

  @TestGraphs
  void getPropertyDefault(Graph<String> graph) {
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withProperty("color").isEmpty();
  }

  @TestGraphs
  void setPropertyNullName(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").setProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void setPropertyNullValue(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").setProperty("name", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void setPropertyEmptyName(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").setProperty("", ""));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @TestGraphs
  void setProperty(Graph<String> graph) {
    graph.getVertex("A").setProperty("name", "value");
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @TestGraphs
  void setPropertyUpdateToNull(Graph<String> graph) {
    graph.getVertex("A").setProperty("name", "value");
    graph.getVertex("A").setProperty("name", null);
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
  }

  @TestGraphs
  void property(Graph<String> graph) {
    graph.getVertex("A").property("name", "value");
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @TestGraphs
  void property2(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void property3(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void property4(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void property5(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void property6(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void property7(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void property8(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void property9(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void property10(Graph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void propertyWithMap(Graph<String> graph) {
    graph.getVertex("A").property(Map.of("name", "value"));
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @TestGraphs
  void property1UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name", "value");
    graph.getVertex("A").property("name", null);
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
  }

  @TestGraphs
  void property2UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name2", "value2");
    graph.getVertex("A").property("name1", "value1", "name2", null);
    assertThat(graph).hasVertex("A").withProperty("name2").isEmpty();
  }

  @TestGraphs
  void property3UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name3", "value3");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", null);
    assertThat(graph).hasVertex("A").withProperty("name3").isEmpty();
  }

  @TestGraphs
  void property4UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name4", "value4");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", null);
    assertThat(graph).hasVertex("A").withProperty("name4").isEmpty();
  }

  @TestGraphs
  void property5UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name5", "value5");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", null);
    assertThat(graph).hasVertex("A").withProperty("name5").isEmpty();
  }

  @TestGraphs
  void property6UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name6", "value6");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", null);
    assertThat(graph).hasVertex("A").withProperty("name6").isEmpty();
  }

  @TestGraphs
  void property7UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name7", "value7");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", null);
    assertThat(graph).hasVertex("A").withProperty("name7").isEmpty();
  }

  @TestGraphs
  void property8UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name8", "value8");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", null);
    assertThat(graph).hasVertex("A").withProperty("name8").isEmpty();
  }

  @TestGraphs
  void property9UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name9", "value9");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", null);
    assertThat(graph).hasVertex("A").withProperty("name9").isEmpty();
  }

  @TestGraphs
  void property10UpdateToNull(Graph<String> graph) {
    graph.getVertex("A").property("name10", "value10");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", null);
    assertThat(graph).hasVertex("A").withProperty("name10").isEmpty();
  }

  @TestGraphs
  void propertySetIdFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").property("id", "B"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void findPropertyNull(Graph<String> graph) {
    var vertex = graph.getVertex("A");
    var exception = assertThrows(NullPointerException.class, () -> vertex.findProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void findProperty(Graph<String> graph) {
    var vertex = graph.getVertex("A");
    vertex.property("name", "value");
    Optional<String> result = vertex.findProperty("name");
    assertThat(result).hasValue("value");
  }

  @TestGraphs
  void getPropertyNull(Graph<String> graph) {
    var vertex = graph.getVertex("A");
    var exception = assertThrows(NullPointerException.class, () -> vertex.getProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void getProperty(Graph<String> graph) {
    var vertex = graph.getVertex("A");
    vertex.property("name", "value");
    String result = vertex.getProperty("name");
    assertThat(result).isEqualTo("value");
  }

  @TestGraphs
  void removePropertyIdFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").removeProperty("id"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void removePropertyNullName(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").removeProperty(null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void removePropertyNameMissing(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").removeProperty("name"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void removeProperty(Graph<String> graph) {
    graph.getVertex("A").property("name", "value");
    var result = graph.getVertex("A").removeProperty("name");
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
    assertThat(result).isSameInstanceAs(graph.getVertex("A"));
  }

  @TestGraphs
  void testSetId(Graph<String> graph) {
    var a = graph.getVertex("id");
    var from = graph.getEdge("id", "b");
    var to = graph.getEdge("c", "id");
    a.setId("a");

    assertThat(a.getId()).isEqualTo("a");
    assertThat(from.getFrom()).isEqualTo("a");
    assertThat(to.getTo()).isEqualTo("a");
  }

  @TestGraphs
  void testSetIdAlreadyExists(Graph<String> graph) {
    graph.getVertex("a").property("key", "value");
    var vertex = graph.getVertex("id");
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> vertex.id("a"));
    assertThat(ex).hasMessageThat().isEqualTo("vertex with id a already exists.");
  }

  @TestGraphs
  void edgeAfterSetId(Graph<String> graph) {
    graph.edge("id", "b");
    graph.edge("c", "id");
    graph.getVertex("id").setId("a");

    assertThat(graph).hasEdge("a", "b");
    assertThat(graph).hasEdge("c", "a");
  }

  @TestGraphs
  void testId(Graph<String> graph) {
    var vertex = graph.getVertex("A").id("B");
    assertThat(vertex).hasId("B");
    assertThat(vertex).isSameInstanceAs(graph.getVertex("B"));
  }

  @TestGraphs
  void testAdjacentEdges(Graph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "C");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("A");

    var result = vertex.adjacentEdges();
    assertThat(result).containsExactly(edge1, edge2).inOrder();
  }

  @TestGraphs
  void adjacentEdgesEmpty(Graph<String> graph) {
    assertThat(graph.getVertex("A").adjacentEdges()).isEmpty();
  }

  @TestGraphs
  void adjacentEdgesAfterSetId(Graph<String> graph) {
    var edge1 = graph.getEdge("id", "B");
    var edge2 = graph.getEdge("id", "C");
    var edge3 = graph.getEdge("D", "id");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.adjacentEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void testConnectsTo(Graph<String> graph) {
    var vertex = graph.getVertex("A")
      .connectsTo("B");
    assertThat(graph).hasVertex("B");
    assertThat(graph).hasEdge("A", "B");
  }

  @TestGraphs
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

  @TestGraphs
  void inEdges(Graph<String> graph) {
    var edge1 = graph.getEdge("B", "A");
    var edge2 = graph.getEdge("C", "A");
    var edge3 = graph.getEdge("D", "A");
    var result = graph.getVertex("A").inEdges();

    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void inEdgesAfterSetId(Graph<String> graph) {
    var edge1 = graph.getEdge("B", "id");
    var edge2 = graph.getEdge("C", "id");
    var edge3 = graph.getEdge("D", "id");
    graph.edge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.inEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void inEdgesEmpty(Graph<String> graph) {
    assertThat(graph.getVertex("A").inEdges()).isEmpty();
  }

  @TestGraphs
  void outEdges(Graph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "C");
    var edge3 = graph.getEdge("A", "D");
    var result = graph.getVertex("A").outEdges();

    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void outEdgesAfterSetId(Graph<String> graph) {
    var edge1 = graph.getEdge("id", "B");
    var edge2 = graph.getEdge("id", "C");
    var edge3 = graph.getEdge("id", "D");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.outEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void outEdgesEmpty(Graph<String> graph) {
    assertThat(graph.getVertex("A").outEdges()).isEmpty();
  }

  @TestGraphs
  void testEquals(Graph<String> graph) {
    var vertex = graph.getVertex("A");
    EqualsVerifier.forClass(vertex.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
  }

  @TestGraphs
  void testEqualsDifferentInherited(Graph<String> graph) {
    var other = new UndirectedGraph<String>();
    other.vertexProperty("name", "value");
    var vertex1 = graph.getVertex("A");
    var vertex2 = other.getVertex("A");

    assertThat(vertex1.equals(vertex2)).isFalse();
  }

  @TestGraphs
  void remove(Graph<String> graph) {
    graph.getVertex("A").remove();
    assertThat(graph).hasNoVertex("A");
  }
}
