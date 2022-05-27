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
  void inheritedDefault(PropertyGraph<String> graph) {
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withInherited().isEmpty();
  }

  @TestGraphs
  void inheritedWithProperty(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertexProperty("color", "blue");
    assertThat(graph).hasVertex("A").withInherited().containsExactly("color", "blue");
  }

  @TestGraphs
  void localWithProperty(PropertyGraph<String> graph) {
    graph.getVertex("A").property("color", "blue");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "color", "blue");
  }

  @TestGraphs
  void getPropertyInherited(PropertyGraph<String> graph) {
    graph.vertexProperty("color", "blue");
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
  }

  @TestGraphs
  void getPropertyLocal(PropertyGraph<String> graph) {
    graph.getVertex("A").property("color", "blue");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
  }

  @TestGraphs
  void getPropertyDefault(PropertyGraph<String> graph) {
    graph.vertex("A");
    assertThat(graph).hasVertex("A").withProperty("color").isEmpty();
  }

  @TestGraphs
  void setPropertyNullName(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").setProperty(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void setPropertyNullValue(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").setProperty("name", null));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void setPropertyEmptyName(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").setProperty("", ""));
    assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
  }

  @TestGraphs
  void setProperty(PropertyGraph<String> graph) {
    graph.getVertex("A").setProperty("name", "value");
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @TestGraphs
  void setPropertyUpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").setProperty("name", "value");
    graph.getVertex("A").setProperty("name", null);
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
  }

  @TestGraphs
  void property(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name", "value");
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @TestGraphs
  void property2(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void property3(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void property4(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void property5(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void property6(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void property7(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void property8(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void property9(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void property10(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void propertyWithMap(PropertyGraph<String> graph) {
    graph.getVertex("A").property(Map.of("name", "value"));
    assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
  }

  @TestGraphs
  void property1UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name", "value");
    graph.getVertex("A").property("name", null);
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
  }

  @TestGraphs
  void property2UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name2", "value2");
    graph.getVertex("A").property("name1", "value1", "name2", null);
    assertThat(graph).hasVertex("A").withProperty("name2").isEmpty();
  }

  @TestGraphs
  void property3UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name3", "value3");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", null);
    assertThat(graph).hasVertex("A").withProperty("name3").isEmpty();
  }

  @TestGraphs
  void property4UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name4", "value4");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", null);
    assertThat(graph).hasVertex("A").withProperty("name4").isEmpty();
  }

  @TestGraphs
  void property5UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name5", "value5");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", null);
    assertThat(graph).hasVertex("A").withProperty("name5").isEmpty();
  }

  @TestGraphs
  void property6UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name6", "value6");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", null);
    assertThat(graph).hasVertex("A").withProperty("name6").isEmpty();
  }

  @TestGraphs
  void property7UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name7", "value7");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", null);
    assertThat(graph).hasVertex("A").withProperty("name7").isEmpty();
  }

  @TestGraphs
  void property8UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name8", "value8");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", null);
    assertThat(graph).hasVertex("A").withProperty("name8").isEmpty();
  }

  @TestGraphs
  void property9UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name9", "value9");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", null);
    assertThat(graph).hasVertex("A").withProperty("name9").isEmpty();
  }

  @TestGraphs
  void property10UpdateToNull(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name10", "value10");
    graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", null);
    assertThat(graph).hasVertex("A").withProperty("name10").isEmpty();
  }

  @TestGraphs
  void propertySetIdFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").property("id", "B"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void findPropertyNull(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A");
    var exception = assertThrows(NullPointerException.class, () -> vertex.findProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void findProperty(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A");
    vertex.property("name", "value");
    Optional<String> result = vertex.findProperty("name");
    assertThat(result).hasValue("value");
  }

  @TestGraphs
  void getPropertyNull(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A");
    var exception = assertThrows(NullPointerException.class, () -> vertex.getProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void getProperty(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A");
    vertex.property("name", "value");
    String result = vertex.getProperty("name");
    assertThat(result).isEqualTo("value");
  }

  @TestGraphs
  void removePropertyIdFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").removeProperty("id"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void removePropertyNullName(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").removeProperty(null));
    assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void removePropertyNameMissing(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").removeProperty("name"));
    assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
  }

  @TestGraphs
  void removeProperty(PropertyGraph<String> graph) {
    graph.getVertex("A").property("name", "value");
    var result = graph.getVertex("A").removeProperty("name");
    assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
    assertThat(result).isSameInstanceAs(graph.getVertex("A"));
  }

  @TestGraphs
  void testSetId(PropertyGraph<String> graph) {
    var a = graph.getVertex("id");
    var from = graph.getEdge("id", "b");
    var to = graph.getEdge("c", "id");
    a.setId("a");

    assertThat(a.getId()).isEqualTo("a");
    assertThat(from.getFrom()).isEqualTo("a");
    assertThat(to.getTo()).isEqualTo("a");
  }

  @TestGraphs
  void testSetIdAlreadyExists(PropertyGraph<String> graph) {
    graph.getVertex("a").property("key", "value");
    var vertex = graph.getVertex("id");
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> vertex.id("a"));
    assertThat(ex).hasMessageThat().isEqualTo("vertex with id a already exists.");
  }

  @TestGraphs
  void edgeAfterSetId(PropertyGraph<String> graph) {
    graph.edge("id", "b");
    graph.edge("c", "id");
    graph.getVertex("id").setId("a");

    assertThat(graph).hasEdge("a", "b");
    assertThat(graph).hasEdge("c", "a");
  }

  @TestGraphs
  void testId(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A").id("B");
    assertThat(vertex).hasId("B");
    assertThat(vertex).isSameInstanceAs(graph.getVertex("B"));
  }

  @TestGraphs
  void testAdjacentEdges(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "C");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("A");

    var result = vertex.adjacentEdges();
    assertThat(result).containsExactly(edge1, edge2).inOrder();
  }

  @TestGraphs
  void adjacentEdgesEmpty(PropertyGraph<String> graph) {
    assertThat(graph.getVertex("A").adjacentEdges()).isEmpty();
  }

  @TestGraphs
  void adjacentEdgesAfterSetId(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("id", "B");
    var edge2 = graph.getEdge("id", "C");
    var edge3 = graph.getEdge("D", "id");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.adjacentEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void testConnectsTo(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A")
      .connectsTo("B");
    assertThat(graph).hasVertex("B");
    assertThat(graph).hasEdge("A", "B");
  }

  @TestGraphs
  void testConnectsFrom(PropertyGraph<String> graph) {
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
  void inEdges(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("B", "A");
    var edge2 = graph.getEdge("C", "A");
    var edge3 = graph.getEdge("D", "A");
    var result = graph.getVertex("A").inEdges();

    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void inEdgesAfterSetId(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("B", "id");
    var edge2 = graph.getEdge("C", "id");
    var edge3 = graph.getEdge("D", "id");
    graph.edge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.inEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void inEdgesEmpty(PropertyGraph<String> graph) {
    assertThat(graph.getVertex("A").inEdges()).isEmpty();
  }

  @TestGraphs
  void outEdges(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "C");
    var edge3 = graph.getEdge("A", "D");
    var result = graph.getVertex("A").outEdges();

    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void outEdgesAfterSetId(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("id", "B");
    var edge2 = graph.getEdge("id", "C");
    var edge3 = graph.getEdge("id", "D");
    graph.getEdge("Z", "Y");

    var vertex = graph.getVertex("id").id("A");

    var result = vertex.outEdges();
    assertThat(result).containsExactly(edge1, edge2, edge3).inOrder();
  }

  @TestGraphs
  void outEdgesEmpty(PropertyGraph<String> graph) {
    assertThat(graph.getVertex("A").outEdges()).isEmpty();
  }

  @TestGraphs
  void testEquals(PropertyGraph<String> graph) {
    var vertex = graph.getVertex("A");
    EqualsVerifier.forClass(vertex.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
  }

  @TestGraphs
  void testEqualsDifferentInherited(PropertyGraph<String> graph) {
    var other = new UndirectedPropertyGraph<String>();
    other.vertexProperty("name", "value");
    var vertex1 = graph.getVertex("A");
    var vertex2 = other.getVertex("A");

    assertThat(vertex1.equals(vertex2)).isFalse();
  }

  @TestGraphs
  void remove(PropertyGraph<String> graph) {
    graph.getVertex("A").remove();
    assertThat(graph).hasNoVertex("A");
  }
}
