package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import com.github.moaxcp.graphs.events.EdgeCreatedEvent;
import com.github.moaxcp.graphs.events.EdgePropertyEvent;
import com.github.moaxcp.graphs.events.VertexCreatedEvent;
import com.github.moaxcp.graphs.events.VertexPropertyEvent;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class RepresentingAPropertyGraphAsCode {
  @Test
  void graphsCore() {
    // tag::graphsCore[]
    PropertyGraph<String> undirectedGraph = new UndirectedPropertyGraph<>();
    PropertyGraph<String> directedGraph = new DirectedPropertyGraph<>();
    ObservablePropertyGraph<String> undirectedObservableGraph = new ObservableUndirectedPropertyGraph<>();
    ObservablePropertyGraph<String> directedObservableGraph = new ObservableDirectedPropertyGraph<>();
    // end::graphsCore[]
  }

  @Test
  void addAVertex() {
    PropertyGraph<String> graph = new UndirectedPropertyGraph<>();
    // tag::vertex[]
    graph = graph.vertex("A");
    Vertex<String> vertex = graph.getVertex("B");
    assertThat(graph).hasVertex("A");
    assertThat(graph).hasVertex("B");
    // end::vertex[]
  }

  @Test
  void vertexEvent() {
    // tag::vertexEvent[]
    ObservablePropertyGraph<String> graph = new ObservableUndirectedPropertyGraph<>();
    graph = graph.vertex("A");
    assertThat(graph).withAction(g -> g.vertex("B"))
        .containsExactly(VertexCreatedEvent.<String>builder().vertexId("B").build());
    // end::vertexEvent[]
  }

  @Test
  void modifyVertex() {
    var graph = new DirectedPropertyGraph<String>();
    // tag::modifyVertex[]
    graph.vertex("A", "color", "red");
    graph.vertex("A", "color", "blue");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    // end::modifyVertex[]
  }

  @Test
  void modifyVertexEvents() {
    var graph = new ObservableDirectedPropertyGraph<String>();
    // tag::modifyVertexEvents[]
    assertThat(graph).withAction(g -> {
      g.vertex("A", "color", "red");
      g.vertex("A", "color", "blue");
    }).containsExactly(
        VertexCreatedEvent.<String>builder()
            .vertexId("A").property("color", "red").build(),
        VertexPropertyEvent.<String>builder()
            .vertexId("A").property("color", "blue").build());
    // end::modifyVertexEvents[]
  }

  @Test
  void addAnEdge() {
    PropertyGraph<String> graph = new DirectedPropertyGraph<>();
    // tag::edge[]
    graph = graph.edge("A", "B", "color", "red");
    Edge<String> edge = graph.getEdge("A", "C", "color", "green");
    assertThat(graph).hasEdge("A", "B").withProperty("color").hasValue("red");
    assertThat(graph).hasEdge("A", "C").withProperty("color").hasValue("green");
    // end::edge[]
  }

  @Test
  void addAnEdgeEvent() {
    ObservablePropertyGraph<String> graph = new ObservableDirectedPropertyGraph<>();
    // tag::edgeEvents[]
    assertThat(graph).withAction(g -> {
      g = g.edge("A", "B", "color", "red");
      Edge<String> edge = g.getEdge("A", "C", "color", "green");
    }).containsExactly(
        VertexCreatedEvent.<String>builder().vertexId("A").build(),
        VertexCreatedEvent.<String>builder().vertexId("B").build(),
        EdgeCreatedEvent.<String>builder().sourceId("A").targetId("B").property("color", "red").build(),
        VertexCreatedEvent.<String>builder().vertexId("C").build(),
        EdgeCreatedEvent.<String>builder().sourceId("A").targetId("C").property("color", "green").build()
    );
    // end::edgeEvents[]
  }

  @Test
  void modifyEdge() {
    var graph = new DirectedPropertyGraph<String>();
    // tag::modifyEdge[]
    graph.edge("A", "B", "color", "red");
    graph.edge("A", "B", "color", "green");
    assertThat(graph).hasEdge("A", "B").withProperty("color").hasValue("green");
    // end::modifyEdge[]
  }

  @Test
  void modifyEdgeEvents() {
    var graph = new ObservableDirectedPropertyGraph<String>();
    // tag::modifyEdgeEvents[]
    assertThat(graph).withAction(g -> {
      g.edge("A", "B", "color", "red");
      g.edge("A", "B", "color", "green");
    }).containsExactly(
        VertexCreatedEvent.<String>builder().vertexId("A").build(),
        VertexCreatedEvent.<String>builder().vertexId("B").build(),
        EdgeCreatedEvent.<String>builder().sourceId("A").targetId("B").property("color", "red").build(),
        EdgePropertyEvent.<String>builder().sourceId("A").targetId("B").property("color", "green").build()
    );
    // end::modifyEdgeEvents[]
  }

  @Test
  void inheritedProperties() {
    var graph = new DirectedPropertyGraph<String>();
    // tag::inheritedProperties[]
    graph.vertexProperty("color", "green");
    graph.edgeProperty("color", "red");
    graph.edge("A", "B");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("green");
    assertThat(graph).hasVertex("B").withProperty("color").hasValue("green");
    assertThat(graph).hasEdge("A", "B").withProperty("color").hasValue("red");
    // end::inheritedProperties[]
  }
}
