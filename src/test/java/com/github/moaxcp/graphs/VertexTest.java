package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VertexTest {
    Graph graph = new Graph("graph");
    @Test
    void testSetId() {
        Graph.Vertex vertex = graph.vertex("id");
        assertThrows(UnsupportedOperationException.class, () -> vertex.setId("key"));
    }

    @Test
    void testToString() {
        Graph.Vertex vertex = graph.vertex("id");
        vertex.put("key", "value");
        assertThat(vertex.toString()).isEqualTo("Vertex 'id' {id=id, key=value}");
    }
}
