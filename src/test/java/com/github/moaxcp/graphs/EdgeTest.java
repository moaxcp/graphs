package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EdgeTest {
    @Test
    void testConstructorAndGet() {
        Edge edge = new Edge("from", "to");
        assertThat(edge.getFrom()).isEqualTo("from");
        assertThat(edge.getTo()).isEqualTo("to");
    }

    @Test
    void testSetFrom() {
        Edge edge = new Edge("from", "to");
        assertThrows(UnsupportedOperationException.class, () -> edge.setFrom("a"));
    }

    @Test
    void testSetTo() {
        Edge edge = new Edge("from", "to");
        assertThrows(UnsupportedOperationException.class, () -> edge.setTo("a"));
    }
}
