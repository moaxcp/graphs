package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphTest {
    Graph<String> graph = new Graph<>("graph");
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
        Graph<String>.Vertex vertex = graph.vertex("id");
        assertThat(vertex).containsExactly("id", "id");
        assertThat(graph.getVertices()).containsExactly("id", vertex);
    }

    @Test
    void testAddNewEdge() {
        Graph<String>.Edge edge = graph.edge("from", "to");
        assertThat(edge).containsExactly("from", "from", "to", "to");
        assertThat(graph.getVertices()).containsKey("from");
        assertThat(graph.getVertices()).containsKey("to");
        assertThat(graph.getEdges()).containsExactly(edge);
    }

    @Test
    void testAddExistingEdge() {
        Graph<String>.Edge first = graph.edge("from", "to");
        Graph<String>.Edge second = graph.edge("from", "to");
        assertThat(first).isSameAs(second);
    }
}
