package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.Event;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Graph.Vertex vertex = graph.vertex("id");
        assertThat(vertex).containsExactly("id", "id");
        assertThat(graph.getVertices()).containsExactly("id", vertex);
    }

    @Test
    void testAddExistingVertex() {
        Graph.Vertex vertexA = graph.vertex("A");
        Graph.Vertex vertexB = graph.vertex("A");
        assertThat(vertexA).isSameAs(vertexB);
        assertThat(graph.getVertices()).containsExactly("A", vertexB);
    }

    @Test
    void testAddNewEdge() {
        Graph.Edge edge = graph.edge("from", "to");
        assertThat(edge).containsExactly("from", "from", "to", "to");
        assertThat(graph.getVertices()).containsKey("from");
        assertThat(graph.getVertices()).containsKey("to");
        assertThat(graph.getEdges()).containsExactly(edge);
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
    void testRemoveEdgeDoesNotExist() {
        graph.removeEdge("from", "to");
        assertThat(graph.getEdges()).isEmpty();
        assertThat(graph.getVertices()).isEmpty();
    }

    @Test
    void testPublishSubscribe() {
        class TestEvent extends Event {
            @Override
            public void checkEvent() {

            }
        }
        class Handler {
            Event event;
            void handle(Event event) {
                this.event = event;
            }
        }
        var handler = new Handler();
        graph.subscribe(TestEvent.class, handler::handle);
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
            assertThat(edge.values()).contains("A");
        }
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
}
