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
}
