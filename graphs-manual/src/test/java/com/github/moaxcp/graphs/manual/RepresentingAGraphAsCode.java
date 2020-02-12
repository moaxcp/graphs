package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.greenrobot.*;
import org.greenrobot.eventbus.*;
import org.junit.jupiter.api.*;

import static com.github.moaxcp.graphs.truth.Truth.*;

public class RepresentingAGraphAsCode {
  @Test
  void graphsCore() {
    // tag::graphsCore[]
    Graph<String> undirectedGraph = new UndirectedGraph<>();
    Graph<String> directedGraph = new DirectedGraph<>();
    // end::graphsCore[]
  }

  @Test
  void graphsGreenrobot() {
    // tag::graphsGreenrobot[]
    EventBus bus = EventBus.builder().build();
    Graph<String> undirectedGraph = new UndirectedEventGraph<>(bus);
    Graph<String> directedGraph = new DirectedEventGraph<>(bus);
    // end::graphsGreenrobot[]
  }

  @Test
  void addAVertex() {
    var graph = new UndirectedGraph<String>();
    // tag::vertex[]
    graph.vertex("A");
    graph.getVertex("B");
    assertThat(graph).hasVertex("A");
    assertThat(graph).hasVertex("B");
    // end::vertex[]
  }

  @Test
  void modifyVertex() {
    var graph = new DirectedGraph<String>();
    // tag::modifyVertex[]
    graph.vertex("A", "color", "red");
    graph.vertex("A", "color", "blue");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    // end::modifyVertex[]
  }

  @Test
  void modifyVertexEvents() {
    // tag::modifyVertexEvents[]
    EventBus bus = EventBus.builder().build();
    var graph = new DirectedEventGraph<String>(bus);
    assertThat(bus)
      .withAction(() -> graph.vertex("A", "color", "red"))
      .containsExactly(VertexCreatedEvent
        .<String>builder().vertexId("A").property("color", "red").build());
    // end::modifyVertexEvents[]
  }

  @Test
  void addAnEdge() {
    var graph = new DirectedGraph<String>();
    // tag::edge[]
    graph.edge("A", "B", "color", "red");
    graph.getEdge("A", "C", "color", "green");
    assertThat(graph).hasEdge("A", "B").withProperty("color").hasValue("red");
    assertThat(graph).hasEdge("A", "C").withProperty("color").hasValue("green");
    // end::edge[]
  }

  @Test
  void modifyEdge() {
    var graph = new DirectedGraph<String>();
    // tag::modifyEdge[]
    graph.edge("A", "B", "color", "red");
    graph.edge("A", "B", "color", "green");
    assertThat(graph).hasEdge("A", "B").withProperty("color").hasValue("green");
    // end::modifyEdge[]
  }

  @Test
  void modifyEdgeEvents() {
    // tag::modifyEdgeEvents[]
    EventBus bus = EventBus.builder().build();
    var graph = new DirectedEventGraph<String>(bus);
    assertThat(bus)
      .withAction(() -> graph.edge("A", "B", "color", "green"))
      .containsExactly(
        VertexCreatedEvent.<String>builder().vertexId("A").build(),
        VertexCreatedEvent.<String>builder().vertexId("B").build(),
        EdgeCreatedEvent.<String>builder().fromId("A").toId("B")
          .property("color", "green").build())
    .inOrder();
    // end::modifyEdgeEvents[]
  }

  @Test
  void inheritedProperties() {
    var graph = new DirectedGraph<String>();
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
