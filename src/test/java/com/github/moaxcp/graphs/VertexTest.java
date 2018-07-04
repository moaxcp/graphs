package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VertexTest {
    Graph<String> graph = new Graph<>("graph");
    @Test
    void testSetId() {
        assertThrows(UnsupportedOperationException.class, () -> graph.new Vertex("id").setId("key"));
    }

    @Test
    void testToString() {
        Graph<String>.Vertex vertex = graph.new Vertex("id");
        vertex.put("key", "value");
        assertThat(vertex.toString()).isEqualTo("Vertex 'id' {id=id, key=value}");
    }
}
