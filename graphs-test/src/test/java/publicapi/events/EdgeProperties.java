package publicapi.events;

import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.events.EdgePropertyEvent;
import com.github.moaxcp.graphs.testframework.TestObservablePropertyGraphs;
import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class EdgeProperties {
  @TestObservablePropertyGraphs
  void addProperty1(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getEdge("A", "B").id("edge");
    assertThat(graph)
        .withAction(g -> g.getEdge("A", "B").property("name1", "value1"))
        .containsExactly(EdgePropertyEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").edgeId("edge").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void addPropertyMap(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getEdge("A", "B").id("edge");
    assertThat(graph)
        .withAction(g -> g.getEdge("A", "B").property(Map.of("name1", "value1")))
        .containsExactly(EdgePropertyEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").edgeId("edge").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void updateProperty1(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getEdge("A", "B").id("edge").property("name1", "A");
    assertThat(graph)
        .withAction(g -> g.getEdge("A", "B").property("name1", "value1"))
        .containsExactly(EdgePropertyEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").edgeId("edge").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void updatePropertyMap(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getEdge("A", "B").id("edge").property("name1", "A");
    assertThat(graph)
        .withAction(g -> g.getEdge("A", "B").property(Map.of("name1", "value1")))
        .containsExactly(EdgePropertyEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").edgeId("edge").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void updateProperty1Remove(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getEdge("A", "B").id("edge").property("name1", "A");
    assertThat(graph)
        .withAction(g -> g.getEdge("A", "B").property("name1", null))
        .containsExactly(EdgePropertyEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").edgeId("edge").property("name1", null).build());
  }

  @TestObservablePropertyGraphs
  void removeProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getEdge("A", "B").id("edge").property("name1", "A");
    assertThat(graph)
        .withAction(g -> g.getEdge("A", "B").removeProperty("name1"))
        .containsExactly(EdgePropertyEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").edgeId("edge").property("name1", null).build());
  }
}
