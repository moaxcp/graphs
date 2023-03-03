package publicapi.events;

import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.events.VertexPropertyEvent;
import com.github.moaxcp.graphs.testframework.TestObservablePropertyGraphs;
import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class Vertex {
  @TestObservablePropertyGraphs
  void updateId(ObservablePropertyGraph<String> graph) {
    graph.id("graph").vertex("A");
    assertThat(graph).withAction(g -> g.getVertex("A").id("B")).containsExactly(VertexPropertyEvent.<String>builder().graphId("graph").vertexId("A").newId("B").build());
  }

  @TestObservablePropertyGraphs
  void addProperty1(ObservablePropertyGraph<String> graph) {
    graph.id("graph").vertex("A");
    assertThat(graph).withAction(g -> g.getVertex("A").property("name1", "value1"))
        .containsExactly(VertexPropertyEvent.<String>builder().graphId("graph").vertexId("A").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void addPropertyMap(ObservablePropertyGraph<String> graph) {
    graph.id("graph").vertex("A");
    assertThat(graph)
        .withAction(g -> g.getVertex("A").property(Map.of("name1", "value1")))
        .containsExactly(VertexPropertyEvent.<String>builder().graphId("graph").vertexId("A").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void updateProperty1(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getVertex("A").property("name1", "A");
    assertThat(graph).withAction(g -> g.getVertex("A").property("name1", "value1"))
        .containsExactly(VertexPropertyEvent.<String>builder().graphId("graph").vertexId("A").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void updatePropertyMap(ObservablePropertyGraph<String> graph) {
    graph.id("graph").getVertex("A").property("name1", "A");
    assertThat(graph)
        .withAction(g -> g.getVertex("A").property(Map.of("name1", "value1")))
        .containsExactly(VertexPropertyEvent.<String>builder().graphId("graph").vertexId("A").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void updateProperty1Remove(ObservablePropertyGraph<String> graph) {
    graph.id("graph").vertex("A", "name1", "value1");
    assertThat(graph)
        .withAction(g -> g.getVertex("A").property("name1", null))
        .containsExactly(VertexPropertyEvent.<String>builder().graphId("graph").vertexId("A").property("name1", null).build());
  }

  @TestObservablePropertyGraphs
  void updatePropertyAlreadyExists(ObservablePropertyGraph<String> graph) {
    graph.id("graph").vertex("A", "name1", "value1");
    assertThat(graph)
        .withAction(g -> g.getVertex("A").property("name1", "value1"))
        .containsExactly(VertexPropertyEvent.<String>builder().graphId("graph").vertexId("A").property("name1", "value1").build());
  }

  @TestObservablePropertyGraphs
  void removeProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph").vertex("A", "name1", "value1");
    assertThat(graph)
        .withAction(g -> g.getVertex("A").removeProperty("name1"))
        .containsExactly(VertexPropertyEvent.<String>builder().graphId("graph").vertexId("A").property("name1", null).build());
  }
}
