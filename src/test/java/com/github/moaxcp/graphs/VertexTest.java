package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VertexTest {
    Graph graph = new Graph("graph");
    @Test
    void testSetId() {
        var vertex = graph.vertex("id");
        assertThrows(UnsupportedOperationException.class, () -> vertex.setId("key"));
    }

    @Test
    void testToString() {
        var vertex = graph.vertex("id");
        vertex.put("key", "value");
        assertThat(vertex.toString()).isEqualTo("Vertex 'id' {id=id, key=value}");
    }

    @Test
    void testAdjacentEdges() {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(2);
        for(Graph.Edge edge : edges) {
            assertThat(edge.values()).contains("A");
        }
    }

    @Test
    void testConnectsTo() {
        var vertex = graph.vertex("A")
                .connectsTo("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next()).containsExactly("from", "A", "to", "B");
    }

    @Test
    void testConnectsFrom() {
        var vertex = graph.vertex("A")
                .connectsFrom("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next()).containsExactly("from", "B", "to", "A");
    }

    @Test
    void testEdgeTo() {
        var edge = graph.vertex("A")
                .edgeTo("B");
        assertThat(edge).containsExactly("from", "A", "to", "B");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }

    @Test
    void testEdgeFrom() {
        var edge = graph.vertex("A")
                .edgeFrom("B");
        assertThat(edge).containsExactly("from", "B", "to", "A");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }
}
