package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.*;
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
    graph.vertex("A");
    graph.vertex("A", "color", "blue");
    assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    // end::modifyVertex[]
  }

  @Test
  void modifyVertexEvents() {
    EventBus bus = EventBus.builder().build();
    var graph = new DirectedEventGraph<String>(bus);

  }

  @Test
  void addAnEdge() {
    var graph = new DirectedGraph<String>();
    // tag::edge[]
    graph.edge("A", "B", "color", "red");
    graph.getEdge("A", "C", "color", "green");
    assertThat(graph).hasEdge("A", "B").withLocalProperty("color").isEqualTo("red");
    assertThat(graph).hasEdge("A", "C").withLocalProperty("color").isEqualTo("green");
    // end::edge[]
  }
}
