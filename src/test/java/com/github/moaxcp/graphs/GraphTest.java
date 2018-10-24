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
