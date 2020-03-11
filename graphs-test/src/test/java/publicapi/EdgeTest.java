package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import com.google.common.truth.*;
import nl.jqno.equalsverifier.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {
  @TestGraphs
  void setId(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setId("id");
    assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
    assertThat(graph).hasEdge("id").isSameInstanceAs(edge);
  }

  @TestGraphs
  void changeId(Graph<String> graph) {
    var edge = graph.getEdge("A", "B").id("id");
    edge.setId("id2");
    assertThat(graph).hasEdge("id2").isSameInstanceAs(edge);
    assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id2");
    assertThat(graph).hasEdge("id2").hasIdThat().hasValue("id2");
    assertThat(graph).hasNoEdge("id");
  }

  @TestGraphs
  void setIdNullNoId(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setId(null);
    assertThat(edge).hasNoId();
  }

  @TestGraphs
  void setIdNullRemovesId(Graph<String> graph) {
    var edge = graph.getEdge("A", "to").id("id");
    edge.setId(null);
    assertThat(edge).hasNoId();
    assertThat(graph).hasNoEdge("id");
  }

  @TestGraphs
  void id(Graph<String> graph) {
    var edge = graph.getEdge("A", "B").id("id");
    assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
    assertThat(graph).hasEdge("id").isSameInstanceAs(edge);
  }

  @TestGraphs
  void setFrom(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setFrom("C");
    assertThat(graph).hasVertex("C");
    assertThat(edge).hasFromThat().isEqualTo("C");
  }

  @TestGraphs
  void setFromNull(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setFrom(null));
    assertThat(thrown).hasMessageThat().isEqualTo("from is marked non-null but is null");
  }

  @TestGraphs
  void setFromCreatesNewVertex(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setFrom("C");
    assertThat(graph).hasVertex("C");
  }

  @TestGraphs
  void setFromChangesChangesAdjacentEdges(Graph<String> graph) {
    var edge = graph.getEdge("id", "B");
    edge.setFrom("A");
    var inEdges = edge.fromVertex().adjacentEdges();
    assertThat(inEdges).containsExactly(edge);
  }

  @TestGraphs
  void setFromChangesChangesInEdges(Graph<String> graph) {
    var edge = graph.getEdge("id", "B");
    edge.setFrom("A");
    var inEdges = edge.toVertex().inEdges();
    assertThat(inEdges).containsExactly(edge);
  }

  @TestGraphs
  void setFromChangesChangesOutEdges(Graph<String> graph) {
    var edge = graph.getEdge("A", "id");
    edge.setTo("A");
    var outEdges = edge.fromVertex().outEdges();
    assertThat(outEdges).containsExactly(edge);
  }

  @TestGraphs
  void from(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var result = edge.from("C");
    assertThat(edge).hasFromThat().isEqualTo("C");
    assertThat(result).isSameInstanceAs(edge);
  }

  @TestGraphs
  void fromGet(Graph<String> graph) {
    assertThat(graph.getEdge("A", "B").from()).isEqualTo("A");
  }

  @TestGraphs
  void setTo(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setTo("C");
    assertThat(graph).hasVertex("C");
    assertThat(edge).hasToThat().isEqualTo("C");
  }

  @TestGraphs
  void setToNull(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setTo(null));
    assertThat(thrown).hasMessageThat().isEqualTo("to is marked non-null but is null");
  }

  @TestGraphs
  void setToCreatesNewVertex(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setTo("C");
    assertThat(graph).hasVertex("C");
  }

  @TestGraphs
  void setToChangesChangesAdjacentEdges(Graph<String> graph) {
    var edge = graph.getEdge("A", "id");
    edge.setTo("B");
    var adjacentEdges = edge.toVertex().adjacentEdges();
    assertThat(adjacentEdges).containsExactly(edge);
  }

  @TestGraphs
  void setToChangesChangesInEdges(Graph<String> graph) {
    var edge = graph.getEdge("A", "id");
    edge.setTo("B");
    var inEdges = edge.toVertex().inEdges();
    assertThat(inEdges).containsExactly(edge);
  }

  @TestGraphs
  void setToChangesChangesOutEdges(Graph<String> graph) {
    var edge = graph.getEdge("A", "id");
    edge.setTo("A");
    var outEdges = edge.toVertex().inEdges();
    assertThat(outEdges).containsExactly(edge);
  }

  @TestGraphs
  void to(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var result = edge.to("C");
    assertThat(edge).hasToThat().isEqualTo("C");
    assertThat(result).isSameInstanceAs(edge);
  }

  @TestGraphs
  void toGet(Graph<String> graph) {
    assertThat(graph.getEdge("A", "B").to()).isEqualTo("B");
  }

  @TestGraphs
  void fromVertex(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var vertex = graph.findVertex("A").get();
    assertThat(edge.fromVertex()).isEqualTo(vertex);
  }

  @TestGraphs
  void toVertex(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var vertex = graph.findVertex("B").get();
    assertThat(edge.toVertex()).isEqualTo(vertex);
  }

  @TestGraphs
  void setProperty(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setProperty("key", "value");
    assertThat(edge).withProperty("key").hasValue("value");
  }

  @TestGraphs
  void setPropertyUpdateToNull(Graph<String> graph) {
    graph.getEdge("A", "B").setProperty("name", "value");
    graph.getEdge("A", "B").setProperty("name", null);
    assertThat(graph).hasEdge("A", "B").withProperty("name").isEmpty();
  }

  @TestGraphs
  void property(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void property2(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void property3(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void property4(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void property5(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void property6(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void property7(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void property8(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void property9(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void property10(Graph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void propertyWithMap(Graph<String> graph) {
    graph.getEdge("A", "B").property(Map.of("name", "value"));
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name", "value");
  }

  @TestGraphs
  void propertyUpdateToNull(Graph<String> graph) {
    graph.getEdge("A", "B").property("name", "value");
    graph.getEdge("A", "B").property("name", null);
    assertThat(graph).hasEdge("A", "B").withProperty("name").isEmpty();
  }

  @TestGraphs
  void findPropertyNull(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var exception = assertThrows(NullPointerException.class, () -> edge.findProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void findProperty(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.property("name", "value");
    Optional<String> result = edge.findProperty("name");
    Truth8.assertThat(result).hasValue("value");
  }

  @TestGraphs
  void getPropertyNull(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var exception = assertThrows(NullPointerException.class, () -> edge.getProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void getProperty(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.property("name", "value");
    String result = edge.getProperty("name");
    assertThat(result).isEqualTo("value");
  }

  @TestGraphs
  void propertySetIdFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("id", "edge"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void removePropertyIdFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").id("edge").removeProperty("id"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void propertySetFromFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("from", "C"));
    assertThat(exception).hasMessageThat().isEqualTo("from cannot be set as a property.");
  }

  @TestGraphs
  void removePropertyFromFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").removeProperty("from"));
    assertThat(exception).hasMessageThat().isEqualTo("from cannot be set as a property.");
  }

  @TestGraphs
  void propertySetToFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("to", "C"));
    assertThat(exception).hasMessageThat().isEqualTo("to cannot be set as a property.");
  }

  @TestGraphs
  void removePropertyToFails(Graph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").removeProperty("to"));
    assertThat(exception).hasMessageThat().isEqualTo("to cannot be set as a property.");
  }

  @TestGraphs
  void removeProperty(Graph<String> graph) {
    var edge = graph.getEdge("A", "B").property("name", "value");
    edge.removeProperty("name");
    assertThat(edge).withProperty("name").isEmpty();
  }

  @TestGraphs
  void testEquals(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    EqualsVerifier.forClass(edge.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
  }

  @TestGraphs
  void testEqualsDifferentInherited(Graph<String> graph) {
    var other = new UndirectedGraph<String>();
    other.edgeProperty("name", "value");
    var edge1 = graph.getEdge("A", "B");
    var edge2 = other.getEdge("A", "B");

    assertThat(edge1.equals(edge2)).isFalse();
  }

  @TestGraphs
  void remove(Graph<String> graph) {
    graph.getEdge("A", "B").remove();
    assertThat(graph).hasNoEdge("A", "B");
  }

  @TestGraphs
  void getEdgeEndpoints(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge.endpoints()).containsExactly("A", "B").inOrder();
  }

  @TestGraphs
  void getEdgeOppositeFrom(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge.getOppositeEndpoint("A")).isEqualTo("B");
  }

  @TestGraphs
  void getEdgeOppositeTo(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge.getOppositeEndpoint("B")).isEqualTo("A");
  }

  @TestGraphs
  void getEdgeOppositeMissing(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var exception = assertThrows(IllegalArgumentException.class, () -> edge.getOppositeEndpoint("C"));
    assertThat(exception).hasMessageThat().isEqualTo("id \"C\" is not an endpoint of this edge.");
  }
}
