package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.*;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyGraphEdgeMethods {

  @TestPropertyGraphs
  void edge(PropertyGraph<String> graph) {
    PropertyGraph<String> r = graph.edge("A", "B");
    assertThat(r).isSameInstanceAs(graph);
    assertThat(graph).hasEdge("A", "B");
  }

  @TestPropertyGraphs
  void getEdge(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(graph).hasEdge("A", "B").isSameInstanceAs(edge);
    assertThat(edge).hasToThat().isEqualTo("B");
    assertThat(edge).hasFromThat().isEqualTo("A");
  }

  @TestPropertyGraphs
  void getEdgeExisting(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "B");
    assertThat(edge1).isSameInstanceAs(edge2);
  }

  @TestPropertyGraphs
  void getEdgeHasNoId(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasIdThat().isEmpty();
  }

  @TestPropertyGraphs
  void getEdgeFromIsSet(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasFromThat().isEqualTo("A");
  }

  @TestPropertyGraphs
  void getEdgeToIsSet(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasToThat().isEqualTo("B");
  }

  @TestUndirectedPropertyGraphs
  void getEdgeDifferentOrderOfEndpointsUndirected(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("B", "A");
    assertThat(edge1).isEqualTo(edge2);
  }

  @TestUndirectedPropertyGraphs
  void findEdgeDifferentOrderOfEndpointsUndirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var edge1 = graph.findEdge("A", "B");
    var edge2 = graph.findEdge("B", "A");
    assertThat(edge1).isEqualTo(edge2);
  }

  @TestDirectedPropertyGraphs
  void getEdgeDifferentOrderOfEndpointsDirected(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("B", "A");
    assertThat(edge1).isNotEqualTo(edge2);
  }

  @TestDirectedPropertyGraphs
  void findEdgeDifferentOrderOfEndpointsDirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var edge1 = graph.findEdge("A", "B");
    var edge2 = graph.findEdge("B", "A");
    assertThat(edge1).isNotEqualTo(edge2);
  }

  @TestPropertyGraphs
  void getEdgeProperty1(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1");
  }

  @TestPropertyGraphs
  void getEdgeProperty1Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1");
  }

  @TestPropertyGraphs
  void getEdgeProperty2(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2");
  }

  @TestPropertyGraphs
  void getEdgeProperty2Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2");
  }

  @TestPropertyGraphs
  void getEdgeProperty3(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestPropertyGraphs
  void getEdgeProperty3Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestPropertyGraphs
  void getEdgeProperty4(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestPropertyGraphs
  void getEdgeProperty4Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestPropertyGraphs
  void getEdgeProperty5(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestPropertyGraphs
  void getEdgeProperty5Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestPropertyGraphs
  void getEdgeProperty6(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestPropertyGraphs
  void getEdgeProperty6Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestPropertyGraphs
  void getEdgeProperty7(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestPropertyGraphs
  void getEdgeProperty7Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestPropertyGraphs
  void getEdgeProperty8(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestPropertyGraphs
  void getEdgeProperty8Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestPropertyGraphs
  void getEdgeProperty9(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestPropertyGraphs
  void getEdgeProperty9Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestPropertyGraphs
  void getEdgeProperty10(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestPropertyGraphs
  void getEdgeProperty10Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestPropertyGraphs
  void getEdgeWithMapProperties(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(edge).withProperty("name1").hasValue("value1");
    assertThat(edge).withProperty("name2").hasValue("value2");
    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestPropertyGraphs
  void getEdgeWithMapPropertiesExisting(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestPropertyGraphs
  void edgeProperty1(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1");
  }

  @TestPropertyGraphs
  void edgeProperty1Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1");
  }

  @TestPropertyGraphs
  void edgeProperty2(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2");
  }

  @TestPropertyGraphs
  void edgeProperty2Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2");
  }

  @TestPropertyGraphs
  void edgeProperty3(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestPropertyGraphs
  void edgeProperty3Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestPropertyGraphs
  void edgeProperty4(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestPropertyGraphs
  void edgeProperty4Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestPropertyGraphs
  void edgeProperty5(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestPropertyGraphs
  void edgeProperty5Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestPropertyGraphs
  void edgeProperty6(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestPropertyGraphs
  void edgeProperty6Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestPropertyGraphs
  void edgeProperty7(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestPropertyGraphs
  void edgeProperty7Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestPropertyGraphs
  void edgeProperty8(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestPropertyGraphs
  void edgeProperty8Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestPropertyGraphs
  void edgeProperty9(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestPropertyGraphs
  void edgeProperty9Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestPropertyGraphs
  void edgeProperty10(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestPropertyGraphs
  void edgeProperty10Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("source", "A", "target", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestPropertyGraphs
  void edgeWithMapProperties(PropertyGraph<String> graph) {
    graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestPropertyGraphs
  void edgeWithMapPropertiesExisting(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestPropertyGraphs
  void removeEdge(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.removeEdge("A", "B");
    assertThat(graph).hasNoEdge("A", "B");
  }

  @TestPropertyGraphs
  void removeEdgeThatDoesNotExist(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
  }

  @TestPropertyGraphs
  void removeEdgeWithIdNullId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestPropertyGraphs
  void removeEdgeWithId(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").id("id");
    graph.removeEdge("id");
    assertThat(graph).hasNoEdge("id");
  }

  @TestPropertyGraphs
  void removeEdgeWithNoId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("id"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge with id 'id' not found.");
  }

  @TestPropertyGraphs
  void removeWithNullId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestPropertyGraphs
  void removeByFromTo(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
  }

  @TestPropertyGraphs
  void removeWithNullFrom(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null, "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("source is marked non-null but is null");
  }

  @TestPropertyGraphs
  void removeWithNullTo(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge("A", null));
    assertThat(thrown).hasMessageThat().isEqualTo("target is marked non-null but is null");
  }

  @TestPropertyGraphs
  void findEdgeMissingFrom(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("source is marked non-null but is null");
  }

  @TestPropertyGraphs
  void findEdgeMissingTo(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge("A", null));
    assertThat(thrown).hasMessageThat().isEqualTo("target is marked non-null but is null");
  }

  @TestPropertyGraphs
  void findEdge(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(graph.findEdge("A", "B")).hasValue(edge);
  }

  @TestPropertyGraphs
  void findEdgeMissingId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestPropertyGraphs
  void findEdgeById(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B").id("id");
    assertThat(graph.findEdge("id")).hasValue(edge);
  }
}
