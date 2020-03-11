package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.*;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphEdgeMethods {

  @TestGraphs
  void edge(Graph<String> graph) {
    Graph<String> r = graph.edge("A", "B");
    assertThat(r).isSameInstanceAs(graph);
    assertThat(graph).hasEdge("A", "B");
  }

  @TestGraphs
  void getEdge(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(graph).hasEdge("A", "B").isSameInstanceAs(edge);
    assertThat(edge).hasToThat().isEqualTo("B");
    assertThat(edge).hasFromThat().isEqualTo("A");
  }

  @TestGraphs
  void getEdgeExisting(Graph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("A", "B");
    assertThat(edge1).isSameInstanceAs(edge2);
  }

  @TestGraphs
  void getEdgeHasNoId(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasIdThat().isEmpty();
  }

  @TestGraphs
  void getEdgeFromIsSet(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasFromThat().isEqualTo("A");
  }

  @TestGraphs
  void getEdgeToIsSet(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(edge).hasToThat().isEqualTo("B");
  }

  @TestUndirectedGraphs
  void getEdgeDifferentOrderOfEndpointsUndirected(Graph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("B", "A");
    assertThat(edge1).isEqualTo(edge2);
  }

  @TestUndirectedGraphs
  void findEdgeDifferentOrderOfEndpointsUndirected(Graph<String> graph) {
    graph.edge("A", "B");
    var edge1 = graph.findEdge("A", "B");
    var edge2 = graph.findEdge("B", "A");
    assertThat(edge1).isEqualTo(edge2);
  }

  @TestDirectedGraphs
  void getEdgeDifferentOrderOfEndpointsDirected(Graph<String> graph) {
    var edge1 = graph.getEdge("A", "B");
    var edge2 = graph.getEdge("B", "A");
    assertThat(edge1).isNotEqualTo(edge2);
  }

  @TestDirectedGraphs
  void findEdgeDifferentOrderOfEndpointsDirected(Graph<String> graph) {
    graph.edge("A", "B");
    var edge1 = graph.findEdge("A", "B");
    var edge2 = graph.findEdge("B", "A");
    assertThat(edge1).isNotEqualTo(edge2);
  }

  @TestGraphs
  void getEdgeProperty1(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void getEdgeProperty1Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void getEdgeProperty2(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void getEdgeProperty2Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void getEdgeProperty3(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void getEdgeProperty3Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void getEdgeProperty4(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void getEdgeProperty4Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void getEdgeProperty5(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void getEdgeProperty5Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void getEdgeProperty6(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void getEdgeProperty6Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void getEdgeProperty7(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void getEdgeProperty7Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void getEdgeProperty8(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void getEdgeProperty8Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void getEdgeProperty9(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void getEdgeProperty9Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void getEdgeProperty10(Graph<String> graph) {
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void getEdgeProperty10Exists(Graph<String> graph) {
    graph.getEdge("A", "B");
    graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void getEdgeWithMapProperties(Graph<String> graph) {
    var edge = graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(edge).withProperty("name1").hasValue("value1");
    assertThat(edge).withProperty("name2").hasValue("value2");
    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void getEdgeWithMapPropertiesExisting(Graph<String> graph) {
    graph.edge("A", "B");
    graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void edgeProperty1(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void edgeProperty1Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
  }

  @TestGraphs
  void edgeProperty2(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void edgeProperty2Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
  }

  @TestGraphs
  void edgeProperty3(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void edgeProperty3Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
  }

  @TestGraphs
  void edgeProperty4(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void edgeProperty4Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
  }

  @TestGraphs
  void edgeProperty5(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void edgeProperty5Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
  }

  @TestGraphs
  void edgeProperty6(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void edgeProperty6Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
  }

  @TestGraphs
  void edgeProperty7(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void edgeProperty7Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
  }

  @TestGraphs
  void edgeProperty8(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void edgeProperty8Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
  }

  @TestGraphs
  void edgeProperty9(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void edgeProperty9Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
  }

  @TestGraphs
  void edgeProperty10(Graph<String> graph) {
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void edgeProperty10Exists(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

    assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
  }

  @TestGraphs
  void edgeWithMapProperties(Graph<String> graph) {
    graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void edgeWithMapPropertiesExisting(Graph<String> graph) {
    graph.edge("A", "B");
    graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

    assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
    assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
  }

  @TestGraphs
  void removeEdge(Graph<String> graph) {
    graph.edge("A", "B");
    graph.removeEdge("A", "B");
    assertThat(graph).hasNoEdge("A", "B");
  }

  @TestGraphs
  void removeEdgeThatDoesNotExist(Graph<String> graph) {
    graph.vertex("A");
    graph.vertex("B");
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
  }

  @TestGraphs
  void removeEdgeWithIdNullId(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestGraphs
  void removeEdgeWithId(Graph<String> graph) {
    graph.getEdge("A", "B").id("id");
    graph.removeEdge("id");
    assertThat(graph).hasNoEdge("id");
  }

  @TestGraphs
  void removeEdgeWithNoId(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("id"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge with id 'id' not found.");
  }

  @TestGraphs
  void removeWithNullId(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestGraphs
  void removeByFromTo(Graph<String> graph) {
    Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
  }

  @TestGraphs
  void removeWithNullFrom(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null, "B"));
    assertThat(thrown).hasMessageThat().isEqualTo("from is marked non-null but is null");
  }

  @TestGraphs
  void removeWithNullTo(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge("A", null));
    assertThat(thrown).hasMessageThat().isEqualTo("to is marked non-null but is null");
  }

  @TestGraphs
  void findEdgeMissingFrom(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge(null, null));
    assertThat(thrown).hasMessageThat().isEqualTo("from is marked non-null but is null");
  }

  @TestGraphs
  void findEdgeMissingTo(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge("A", null));
    assertThat(thrown).hasMessageThat().isEqualTo("to is marked non-null but is null");
  }

  @TestGraphs
  void findEdge(Graph<String> graph) {
    var edge = graph.getEdge("A", "B");
    assertThat(graph.findEdge("A", "B")).hasValue(edge);
  }

  @TestGraphs
  void findEdgeMissingId(Graph<String> graph) {
    Throwable thrown = assertThrows(NullPointerException.class, () -> graph.findEdge(null));
    assertThat(thrown).hasMessageThat().isEqualTo("id is marked non-null but is null");
  }

  @TestGraphs
  void findEdgeById(Graph<String> graph) {
    var edge = graph.getEdge("A", "B").id("id");
    assertThat(graph.findEdge("id")).hasValue(edge);
  }
}
