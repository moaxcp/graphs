package publicapi.events;

import com.github.moaxcp.graphs.ObservableDirectedPropertyGraph;
import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.ObservableUndirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraphObserver;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.TestObservablePropertyGraphs;
import com.github.moaxcp.graphs.truth.Observer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class ObservablePropertyGraphEvents {

  static Stream<Function<PropertyGraphObserver<String>[], ObservablePropertyGraph<String>>> observer() {
    return Stream.of(
        ObservableUndirectedPropertyGraph::new,
        ObservableDirectedPropertyGraph::new
    );
  }

  @TestObservablePropertyGraphs
  void addObserver(ObservablePropertyGraph<String> graph) {
    var observer = new Observer<String>();
    graph.addObserver(observer);
    assertThat(graph.getObservers()).containsExactly(observer);
  }

  @TestObservablePropertyGraphs
  void removeObserver(ObservablePropertyGraph<String> graph) {
    var observer = new Observer<String>();
    graph.addObserver(observer);

    graph.removeObserver(observer);

    assertThat(graph.getObservers()).isEmpty();
  }

  @Test
  void directedGraphCreated() {
    var observer = new Observer<String>();
    new ObservableDirectedPropertyGraph<>("graph", observer);
    assertThat(observer.getEvents())
        .containsExactly(DirectedGraphCreatedEvent.builder().graphId("graph").build());
  }

  @Test
  void undirectedGraphCreated() {
    var observer = new Observer<String>();
    new ObservableUndirectedPropertyGraph<>("graph", observer);
    assertThat(observer.getEvents())
        .containsExactly(UndirectedGraphCreatedEvent.builder().graphId("graph").build());
  }

  @TestObservablePropertyGraphs
  void addId(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.setId("graph"))
        .containsExactly(GraphPropertyEvent.<String>builder().newId("graph").build());
  }

  @TestObservablePropertyGraphs
  void updateId(ObservablePropertyGraph<String> graph) {
    graph.setId("old");
    assertThat(graph).withAction(g -> g.setId("graph"))
        .containsExactly(GraphPropertyEvent.<String>builder().graphId("old").newId("graph").build());
  }

  @TestObservablePropertyGraphs
  void removeId(ObservablePropertyGraph<String> graph) {
    graph.setId("graph");
    assertThat(graph).withAction(g -> g.setId(null))
        .containsExactly(GraphPropertyEvent.<String>builder().graphId("graph").build());
  }

  @TestObservablePropertyGraphs
  void addProperty(ObservablePropertyGraph<String> graph) {
    graph.setId("graph");
    assertThat(graph).withAction(g -> g.property("name", "value"))
        .containsExactly(GraphPropertyEvent.<String>builder().graphId("graph").property("name", "value").build());
  }

  @TestObservablePropertyGraphs
  void removeProperty(ObservablePropertyGraph<String> graph) {
    graph.setId("graph");
    graph.property("name", "value");
    assertThat(graph).withAction(g -> g.removeProperty("name"))
        .containsExactly(GraphPropertyEvent.<String>builder().graphId("graph").property("name", null).build());
  }

  @TestObservablePropertyGraphs
  void updateProperty(ObservablePropertyGraph<String> graph) {
    graph.setId("graph");
    graph.property("name", "value");
    assertThat(graph).withAction(g -> g.property("name", "value2"))
        .containsExactly(GraphPropertyEvent.<String>builder().graphId("graph").property("name", "value2").build());
  }

  @TestObservablePropertyGraphs
  void addAllEdgesProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    assertThat(graph).withAction(g -> g.edgeProperty("name", "value"))
        .containsExactly(EdgeInheritedPropertyEvent.<String>builder().graphId("graph").property("name", "value").build());
  }

  @TestObservablePropertyGraphs
  void removeAllEdgesProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.edgeProperty("name", "value");
    assertThat(graph).withAction(g -> g.removeEdgeProperty("name"))
        .containsExactly(EdgeInheritedPropertyEvent.<String>builder().graphId("graph").property("name", null).build());
  }

  @TestObservablePropertyGraphs
  void updateAllEdgesProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.edgeProperty("name", "value");
    assertThat(graph).withAction(g -> g.edgeProperty("name", "value2"))
        .containsExactly(EdgeInheritedPropertyEvent.<String>builder().graphId("graph").property("name", "value2").build());
  }

  @TestObservablePropertyGraphs
  void addAllVerticesProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertexProperty("name", "value");
    assertThat(graph).withAction(g -> g.vertexProperty("name", "value2"))
        .containsExactly(VertexInheritedPropertyEvent.<String>builder().graphId("graph").property("name", "value2").build());
  }

  @TestObservablePropertyGraphs
  void removeAllVerticesProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertexProperty("name", "value");
    assertThat(graph).withAction(g -> g.removeVertexProperty("name"))
        .containsExactly(VertexInheritedPropertyEvent.<String>builder().graphId("graph").property("name", null).build());
  }

  @TestObservablePropertyGraphs
  void updateAllVerticesProperty(ObservablePropertyGraph<String> graph) {
    graph.id("graph");
    graph.vertexProperty("name", "value");
    assertThat(graph).withAction(g -> g.vertexProperty("name", "value2"))
        .containsExactly(VertexInheritedPropertyEvent.<String>builder().graphId("graph").property("name", "value2").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreated(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A"))
        .containsExactly(VertexCreatedEvent.<String>builder().vertexId("A").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedOneProperty(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A", "name", "value"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name", "value").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedTwoProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedThreeProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedFourProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedFiveProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedSixProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedSevenProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6",
            "name7", "value7"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedEightProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6",
            "name7", "value7",
            "name8", "value8"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7")
            .property("name8", "value8").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedNineProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6",
            "name7", "value7",
            "name8", "value8",
            "name9", "value9"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7")
            .property("name8", "value8")
            .property("name9", "value9").build());
  }

  @TestObservablePropertyGraphs
  void vertexCreatedTenProperties(ObservablePropertyGraph<String> graph) {
    assertThat(graph).withAction(g -> g.vertex("A",
            "name1", "value1",
            "name2", "value2",
            "name3", "value3",
            "name4", "value4",
            "name5", "value5",
            "name6", "value6",
            "name7", "value7",
            "name8", "value8",
            "name9", "value9",
            "name10", "value10"))
        .containsExactly(VertexCreatedEvent.<String>builder()
            .vertexId("A")
            .property("name1", "value1")
            .property("name2", "value2")
            .property("name3", "value3")
            .property("name4", "value4")
            .property("name5", "value5")
            .property("name6", "value6")
            .property("name7", "value7")
            .property("name8", "value8")
            .property("name9", "value9")
            .property("name10", "value10").build());
  }
}
