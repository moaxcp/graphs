package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VertexTest {
    @Test
    void testSetId() {
        assertThrows(UnsupportedOperationException.class, () -> new Vertex("id").setId("key"));
    }

    @Test
    void testToString() {
        Vertex vertex = new Vertex("id");
        vertex.put("key", "value");
        assertThat(vertex.toString()).isEqualTo("Vertex 'id' {id=id, key=value}");
    }
}
