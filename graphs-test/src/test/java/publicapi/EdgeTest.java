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
  void setId(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setId("id");
    assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
    assertThat(graph).hasEdge("id").isSameInstanceAs(edge);
  }

  @TestGraphs
  void changeId(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B").id("id");
    edge.setId("id2");
    assertThat(graph).hasEdge("id2").isSameInstanceAs(edge);
    assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id2");
    assertThat(graph).hasEdge("id2").hasIdThat().hasValue("id2");
    assertThat(graph).hasNoEdge("id");
  }

  @TestGraphs
  void setIdNullNoId(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setId(null);
    assertThat(edge).hasNoId();
  }

  @TestGraphs
  void setIdNullRemovesId(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "to").id("id");
    edge.setId(null);
    assertThat(edge).hasNoId();
    assertThat(graph).hasNoEdge("id");
  }

  @TestGraphs
  void id(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B").id("id");
    assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
    assertThat(graph).hasEdge("id").isSameInstanceAs(edge);
  }

  @TestGraphs
  void setFrom(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setSource("C");
    assertThat(graph).hasVertex("C");
    assertThat(edge).hasFromThat().isEqualTo("C");
  }

  @TestGraphs
  void setFromNull(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setSource(null));
    assertThat(thrown).hasMessageThat().isEqualTo("source is marked non-null but is null");
  }

  @TestGraphs
  void setFromCreatesNewVertex(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setSource("C");
    assertThat(graph).hasVertex("C");
  }

  @TestGraphs
  void setFromChangesChangesAdjacentEdges(PropertyGraph<String> graph) {
    var edge = graph.getEdge("id", "B");
    edge.setSource("A");
    var inEdges = edge.sourceVertex().adjacentEdges();
    assertThat(inEdges).containsExactly(edge);
  }

  @TestGraphs
  void setFromChangesChangesInEdges(PropertyGraph<String> graph) {
    var edge = graph.getEdge("id", "B");
    edge.setSource("A");
    var inEdges = edge.targetVertex().inEdges();
    assertThat(inEdges).containsExactly(edge);
  }

  @TestGraphs
  void setFromChangesChangesOutEdges(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "id");
    edge.setTarget("A");
    var outEdges = edge.sourceVertex().outEdges();
    assertThat(outEdges).containsExactly(edge);
  }

  @TestGraphs
  void from(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var result = edge.source("C");
    assertThat(edge).hasFromThat().isEqualTo("C");
    assertThat(result).isSameInstanceAs(edge);
  }

  @TestGraphs
  void fromGet(PropertyGraph<String> graph) {
    assertThat(graph.getEdge("A", "B").source()).isEqualTo("A");
  }

  @TestGraphs
  void setTo(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setTarget("C");
    assertThat(graph).hasVertex("C");
    assertThat(edge).hasToThat().isEqualTo("C");
  }

  @TestGraphs
  void setToNull(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setTarget(null));
    assertThat(thrown).hasMessageThat().isEqualTo("target is marked non-null but is null");
  }

  @TestGraphs
  void setToCreatesNewVertex(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setTarget("C");
    assertThat(graph).hasVertex("C");
  }

  @TestGraphs
  void setToChangesChangesAdjacentEdges(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "id");
    edge.setTarget("B");
    var adjacentEdges = edge.targetVertex().adjacentEdges();
    assertThat(adjacentEdges).containsExactly(edge);
  }

  @TestGraphs
  void setToChangesChangesInEdges(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "id");
    edge.setTarget("B");
    var inEdges = edge.targetVertex().inEdges();
    assertThat(inEdges).containsExactly(edge);
  }

  @TestGraphs
  void setToChangesChangesOutEdges(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "id");
    edge.setTarget("A");
    var outEdges = edge.targetVertex().inEdges();
    assertThat(outEdges).containsExactly(edge);
  }

  @TestGraphs
  void to(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var result = edge.target("C");
    assertThat(edge).hasToThat().isEqualTo("C");
    assertThat(result).isSameInstanceAs(edge);
  }

  @TestGraphs
  void toGet(PropertyGraph<String> graph) {
    assertThat(graph.getEdge("A", "B").target()).isEqualTo("B");
  }

  @TestGraphs
  void fromVertex(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var vertex = graph.findVertex("A").get();
    assertThat(edge.sourceVertex()).isEqualTo(vertex);
  }

  @TestGraphs
  void toVertex(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var vertex = graph.findVertex("B").get();
    assertThat(edge.targetVertex()).isEqualTo(vertex);
  }

  @TestGraphs
  void setProperty(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.setProperty("key", "value");
    assertThat(edge).withProperty("key").hasValue("value");
  }

  @TestGraphs
  void setPropertyUpdateToNull(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").setProperty("name", "value");
    graph.getEdge("A", "B").setProperty("name", null);
    assertThat(graph).hasEdge("A", "B").withProperty("name").isEmpty();
  }

  @TestGraphs
  void property(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1");
  }

  @TestGraphs
  void property2(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void property3(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void property4(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void property5(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void property6(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void property7(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void property8(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void property9(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void property10(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void propertyWithMap(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property(Map.of("name", "value"));
    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name", "value");
  }

  @TestGraphs
  void propertyUpdateToNull(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").property("name", "value");
    graph.getEdge("A", "B").property("name", null);
    assertThat(graph).hasEdge("A", "B").withProperty("name").isEmpty();
  }

  @TestGraphs
  void findPropertyNull(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var exception = assertThrows(NullPointerException.class, () -> edge.findProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void findProperty(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.property("name", "value");
    Optional<String> result = edge.findProperty("name");
    Truth8.assertThat(result).hasValue("value");
  }

  @TestGraphs
  void getPropertyNull(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var exception = assertThrows(NullPointerException.class, () -> edge.getProperty(null));
    assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
  }

  @TestGraphs
  void getProperty(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    edge.property("name", "value");
    String result = edge.getProperty("name");
    assertThat(result).isEqualTo("value");
  }

  @TestGraphs
  void propertySetIdFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("id", "edge"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void removePropertyIdFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").id("edge").removeProperty("id"));
    assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
  }

  @TestGraphs
  void propertySetFromFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("source", "C"));
    assertThat(exception).hasMessageThat().isEqualTo("source cannot be set as a property.");
  }

  @TestGraphs
  void removePropertyFromFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").removeProperty("source"));
    assertThat(exception).hasMessageThat().isEqualTo("source cannot be set as a property.");
  }

  @TestGraphs
  void propertySetToFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("target", "C"));
    assertThat(exception).hasMessageThat().isEqualTo("target cannot be set as a property.");
  }

  @TestGraphs
  void removePropertyToFails(PropertyGraph<String> graph) {
    var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").removeProperty("target"));
    assertThat(exception).hasMessageThat().isEqualTo("target cannot be set as a property.");
  }

  @TestGraphs
  void removeProperty(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B").property("name", "value");
    edge.removeProperty("name");
    assertThat(edge).withProperty("name").isEmpty();
  }

  @TestGraphs
  void testEquals(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    EqualsVerifier.forClass(edge.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
  }

  @TestGraphs
  void testEqualsDifferentInherited(PropertyGraph<String> graph) {
    var other = new UndirectedPropertyGraph<String>();
    other.edgeProperty("name", "value");
    var edge1 = graph.getEdge("A", "B");
    var edge2 = other.getEdge("A", "B");

    assertThat(edge1.equals(edge2)).isFalse();
  }

  @TestGraphs
  void remove(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").remove();
    assertThat(graph).hasNoEdge("A", "B");
  }

  @TestGraphs
  void getEdgeEndpoints(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge.endpoints()).containsExactly("A", "B").inOrder();
  }

  @TestGraphs
  void getEdgeOppositeFrom(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge.getOppositeEndpoint("A")).isEqualTo("B");
  }

  @TestGraphs
  void getEdgeOppositeTo(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge.getOppositeEndpoint("B")).isEqualTo("A");
  }

  @TestGraphs
  void getEdgeOppositeMissing(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    var exception = assertThrows(IllegalArgumentException.class, () -> edge.getOppositeEndpoint("C"));
    assertThat(exception).hasMessageThat().isEqualTo("id \"C\" is not an endpoint of this edge.");
  }
}
