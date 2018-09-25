package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.EdgeAddedGraphEvent;
import com.github.moaxcp.graphs.event.EdgeRemovedGraphEvent;
import com.github.moaxcp.graphs.event.VertexAddedGraphEvent;
import com.github.moaxcp.graphs.event.VertexRemovedGraphEvent;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.github.moaxcp.graphs.GraphSubject.assertThat;

public class GraphTest {
    Graph graph = new Graph("graph");
    @Test
    void testGetVertices() {
        assertThat(graph.getVertices()).isEmpty();
    }

    @Test
    void testGetVerticesIsUnmodifiable() {
        Graph.Vertex vertex = graph.vertex("id");
        assertThrows(UnsupportedOperationException.class, () -> graph.getVertices().put("id", vertex));
    }

    @Test
    void testGetEdges() {
        assertThat(graph.getEdges()).isEmpty();
    }

    @Test
    void testGetEdgesUnmodifiable() {
        Graph.Edge edge = graph.edge("from", "to");
        assertThrows(UnsupportedOperationException.class, () -> graph.getEdges().add(edge));
    }

    @Test
    void testAddNewVertex() {
        graph.vertex("id");
        assertThat(graph).hasVertex("id");
    }

    @Test
    void testAddNewVertexEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var vertex = graph.vertex("id");
        assertThat(handler.event).isInstanceOf(VertexAddedGraphEvent.class);
        var event = (VertexAddedGraphEvent) handler.event;
        assertThat(event.getGraph()).isSameAs(graph);
        assertThat(event.getVertex()).isSameAs(vertex);
    }

    @Test
    void testAddExistingVertex() {
        Graph.Vertex vertexA = graph.vertex("A");
        Graph.Vertex vertexB = graph.vertex("A");
        assertThat(vertexA).isSameAs(vertexB);
        assertThat(graph.getVertices()).containsExactly("A", vertexB);
    }

    @Test
    void testRemoveVertex() {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        graph.removeVertex("A");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getVertices()).doesNotContainKey("A");
    }

    @Test
    void testRemoveVertexEvent() {
        var firstEdge = graph.edge("A", "B");
        var secondEdge = graph.edge("A", "C");
        graph.edge("Z", "Y");
        var vertex = graph.vertex("A");

        var handler = new TestHandler();
        EventBus.getDefault().register(handler);

        graph.removeVertex("A");
        assertThat(handler.events).hasSize(3);
        assertThat(handler.events.get(0)).isInstanceOf(EdgeRemovedGraphEvent.class);
        var firstEvent = (EdgeRemovedGraphEvent) handler.events.get(0);
        assertThat(firstEvent.getEdge()).isSameAs(firstEdge);
        var secondEvent = (EdgeRemovedGraphEvent) handler.events.get(1);
        assertThat(secondEvent.getEdge()).isSameAs(secondEdge);
        var thirdEvent = (VertexRemovedGraphEvent) handler.events.get(2);
        assertThat(thirdEvent.getVertex()).isSameAs(vertex);

    }

    @Test
    void testAddNewEdge() {
        Graph.Edge edge = graph.edge("from", "to");
        assertThat(edge.getLocal()).containsExactly("from", "from", "to", "to");
        assertThat(graph.getVertices()).containsKey("from");
        assertThat(graph.getVertices()).containsKey("to");
        assertThat(graph.getEdges()).containsExactly(edge);
    }

    @Test
    void testAddNewEdgeEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var edge = graph.edge("from", "to");
        assertThat(handler.event).isInstanceOf(EdgeAddedGraphEvent.class);
        var event = (EdgeAddedGraphEvent) handler.event;
        assertThat(event.getGraph()).isSameAs(graph);
        assertThat(event.getEdge()).isSameAs(edge);
    }

    @Test
    void testAddExistingEdge() {
        Graph.Edge first = graph.edge("from", "to");
        Graph.Edge second = graph.edge("from", "to");
        assertThat(first).isSameAs(second);
    }

    @Test
    void testRemoveEdge() {
        graph.edge("from", "to");
        graph.removeEdge("from", "to");
        assertThat(graph.getVertices()).containsKey("from");
        assertThat(graph.getVertices()).containsKey("to");
        assertThat(graph.getEdges()).hasSize(0);
    }

    @Test
    void testRemoveEdgeEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var edge = graph.edge("from", "to");
        graph.removeEdge("from", "to");
        assertThat(handler.event).isInstanceOf(EdgeRemovedGraphEvent.class);
        var event = (EdgeRemovedGraphEvent) handler.event;
        assertThat(event.getGraph()).isSameAs(graph);
        assertThat(event.getEdge()).isSameAs(edge);
    }

    @Test
    void testRemoveEdgeDoesNotExist() {
        graph.removeEdge("from", "to");
        assertThat(graph.getEdges()).isEmpty();
        assertThat(graph.getVertices()).isEmpty();
    }

    @Test
    void testPublishSubscribe() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        graph.publish(new TestEvent());
        assertThat(handler.event).isInstanceOf(TestEvent.class);
    }

    @Test
    void testAdjacentEdges() {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        var edges = graph.adjacentEdges("A");
        assertThat(edges).hasSize(2);
        for(Graph.Edge edge : edges) {
            assertThat(edge.getLocal().values()).contains("A");
        }
    }
}
