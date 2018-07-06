package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.EdgeAdded;
import com.github.moaxcp.graphs.event.Event;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        assertThrows(UnsupportedOperationException.class, () -> graph.getVertices().put("id", graph.new Vertex("id")));
    }

    @Test
    void testGetEdges() {
        assertThat(graph.getEdges()).isEmpty();
    }

    @Test
    void testGetEdgesUnmodifiable() {
        assertThrows(UnsupportedOperationException.class, () -> graph.getEdges().add(graph.new Edge("from", "to")));
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
}
