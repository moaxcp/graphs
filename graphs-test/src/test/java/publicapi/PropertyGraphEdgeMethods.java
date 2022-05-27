package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.*;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyGraphEdgeMethods {

  @TestGraphs
  void edge(PropertyGraph<String> graph) {
    PropertyGraph<String> r = graph.edge("A", "B");
    assertThat(r).isSameInstanceAs(graph);
    assertThat(graph).hasEdge("A", "B");
  }

  @TestGraphs
  void getEdge(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(graph).hasEdge("A", "B").isSameInstanceAs(edge);
    assertThat(edge).hasToThat().isEqualTo("B");
    assertThat(edge).hasFromThat().isEqualTo("A");
  }

  @TestGraphs
  void getEdgeExisting(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "B");
    assertThat(edge1).isSameInstanceAs(edge2);
  }

  @TestGraphs
  void getEdgeHasNoId(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasIdThat().isEmpty();
  }

  @TestGraphs
  void getEdgeFromIsSet(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasFromThat().isEqualTo("A");
  }

  @TestGraphs
  void getEdgeToIsSet(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasToThat().isEqualTo("B");
  }

  @TestUndirectedGraphs
  void getEdgeDifferentOrderOfEndpointsUndirected(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("B", "A");
    assertThat(edge1).isEqualTo(edge2);
  }

  @TestUndirectedGraphs
  void findEdgeDifferentOrderOfEndpointsUndirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var edge1 = graph.findEdge("A", "B");
    var edge2 = graph.findEdge("B", "A");
    assertThat(edge1).isEqualTo(edge2);
  }

  @TestDirectedGraphs
  void getEdgeDifferentOrderOfEndpointsDirected(PropertyGraph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("B", "A");
    assertThat(edge1).isNotEqualTo(edge2);
  }

  @TestDirectedGraphs
  void findEdgeDifferentOrderOfEndpointsDirected(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    var edge1 = graph.findEdge("A", "B");
    var edge2 = graph.findEdge("B", "A");
    assertThat(edge1).isNotEqualTo(edge2);
  }

  @TestGraphs
  void getEdgeProperty1(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void getEdgeProperty1Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void getEdgeProperty2(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void getEdgeProperty2Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void getEdgeProperty3(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void getEdgeProperty3Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void getEdgeProperty4(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void getEdgeProperty4Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void getEdgeProperty5(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void getEdgeProperty5Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void getEdgeProperty6(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void getEdgeProperty6Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void getEdgeProperty7(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void getEdgeProperty7Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void getEdgeProperty8(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void getEdgeProperty8Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void getEdgeProperty9(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void getEdgeProperty9Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void getEdgeProperty10(PropertyGraph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void getEdgeProperty10Exists(PropertyGraph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void getEdgeWithMapProperties(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(edge).withProperty("name1").hasValue("value1");
    assertThat(edge).withProperty("name2").hasValue("value2");
    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void getEdgeWithMapPropertiesExisting(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void edgeProperty1(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void edgeProperty1Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void edgeProperty2(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void edgeProperty2Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void edgeProperty3(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void edgeProperty3Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void edgeProperty4(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void edgeProperty4Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void edgeProperty5(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void edgeProperty5Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void edgeProperty6(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void edgeProperty6Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void edgeProperty7(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void edgeProperty7Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void edgeProperty8(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void edgeProperty8Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void edgeProperty9(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void edgeProperty9Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void edgeProperty10(PropertyGraph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void edgeProperty10Exists(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void edgeWithMapProperties(PropertyGraph<String> graph) {
    graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void edgeWithMapPropertiesExisting(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void removeEdge(PropertyGraph<String> graph) {
    graph.edge("A", "B");
    graph.removeEdge("A", "B");
    assertThat(graph).hasNoEdge("A", "B");
  }

  @TestGraphs
  void removeEdgeThatDoesNotExist(PropertyGraph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
  }

  @TestGraphs
  void removeEdgeWithIdNullId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestGraphs
  void removeEdgeWithId(PropertyGraph<String> graph) {
    graph.getEdge("A", "B").id("id");
    graph.removeEdge("id");
    assertThat(graph).hasNoEdge("id");
  }

  @TestGraphs
  void removeEdgeWithNoId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("id"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge with id 'id' not found.");
  }

  @TestGraphs
  void removeWithNullId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestGraphs
  void removeByFromTo(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
  }

  @TestGraphs
  void removeWithNullFrom(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null, "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("from is marked non-null but is null");
  }

  @TestGraphs
  void removeWithNullTo(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge("A", null));
    assertThat(thrown).hasMessageThat().isEqualTo("to is marked non-null but is null");
  }

  @TestGraphs
  void findEdgeMissingFrom(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("from is marked non-null but is null");
  }

  @TestGraphs
  void findEdgeMissingTo(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge("A", null));
    assertThat(thrown).hasMessageThat().isEqualTo("to is marked non-null but is null");
  }

  @TestGraphs
  void findEdge(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(graph.findEdge("A", "B")).hasValue(edge);
  }

  @TestGraphs
  void findEdgeMissingId(PropertyGraph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestGraphs
  void findEdgeById(PropertyGraph<String> graph) {
    var edge = graph.getEdge("A", "B").id("id");
    assertThat(graph.findEdge("id")).hasValue(edge);
  }
}
