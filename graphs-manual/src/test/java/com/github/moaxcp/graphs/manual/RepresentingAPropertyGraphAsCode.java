package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.greenrobot.DirectedEventPropertyGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventPropertyGraph;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class RepresentingAPropertyGraphAsCode {
  @Test
  void graphsCore() {
    // tag::graphsCore[]
    PropertyGraph<String> undirectedGraph = new UndirectedPropertyGraph<>();
    PropertyGraph<String> directedGraph = new DirectedPropertyGraph<>();
    // end::graphsCore[]
  }

  @Test
  void graphsGreenrobot() {
    // tag::graphsGreenrobot[]
    var bus = EventBus.builder().build();
    var undirectedEvent = UndirectedGraphCreatedEvent.<String>builder().graphId("undirected").build();
    var directedEvent = DirectedGraphCreatedEvent.<String>builder().graphId("directed").build();
    assertThat(bus).withAction(() -> {
      PropertyGraph<String> undirectedGraph = new UndirectedEventPropertyGraph<>("undirected", bus);
      PropertyGraph<String> directedGraph = new DirectedEventPropertyGraph<>("directed", bus);
    }).containsExactly(undirectedEvent, directedEvent);
    // end::graphsGreenrobot[]
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
  void addAVertexGreenrobot() {
    EventBus bus = new EventBus();
    PropertyGraph<String> graph = new UndirectedEventPropertyGraph<>(bus);
    // tag::vertexEvent[]
    assertThat(bus).withAction(() -> {
      graph.vertex("A");
      graph.getVertex("B");
    }).containsExactly(VertexCreatedEvent.<String>builder().vertexId("A").build(), VertexCreatedEvent.<String>builder().vertexId("B").build())
        .inOrder();
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
    EventBus bus = EventBus.builder().build();
    var graph = new DirectedEventPropertyGraph<String>(bus);
    // tag::modifyVertexEvents[]
    assertThat(bus).withAction(() -> {
        graph.vertex("A", "color", "red");
        graph.vertex("A", "color", "blue");
      }).containsExactly(VertexCreatedEvent.<String>builder().vertexId("A").property("color", "red").build(),
          VertexPropertyEvent.<String>builder().vertexId("A").property("color", "blue").build())
        .inOrder();
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
  void addAnEdgeGreenRobot() {
    EventBus bus = new EventBus();
    PropertyGraph<String> graph = new DirectedEventPropertyGraph<>(bus);
    // tag::edgeEvent[]
    assertThat(bus).withAction(() -> {
      graph.edge("A", "B", "color", "red");
      graph.getEdge("A", "C", "color", "green");
    }).containsExactly(VertexCreatedEvent.<String>builder().vertexId("A").build(),
        VertexCreatedEvent.<String>builder().vertexId("B").build(),
        EdgeCreatedEvent.<String>builder().sourceId("A").targetId("B").property("color", "red").build(),
        VertexCreatedEvent.<String>builder().vertexId("C").build(),
        EdgeCreatedEvent.<String>builder().sourceId("A").targetId("C").property("color", "green").build())
        .inOrder();
    // end::edgeEvent[]
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
    // tag::modifyEdgeEvents[]
    EventBus bus = EventBus.builder().build();
    var graph = new DirectedEventPropertyGraph<String>(bus);
    assertThat(bus)
      .withAction(() -> {
        graph.edge("A", "B", "color", "red");
        graph.edge("A", "B", "color", "green");
      }).containsExactly(
          VertexCreatedEvent.<String>builder().vertexId("A").build(),
          VertexCreatedEvent.<String>builder().vertexId("B").build(),
          EdgeCreatedEvent.<String>builder().sourceId("A").targetId("B").property("color", "red").build(),
          EdgePropertyEvent.<String>builder().sourceId("A").targetId("B").property("color", "green").build())
    .inOrder();
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
