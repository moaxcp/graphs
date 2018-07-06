package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EdgeTest {
    Graph graph = new Graph("graph");
    @Test
    void testConstructorAndGet() {
        Graph.Edge edge = graph.new Edge("from", "to");
        assertThat(edge.getFrom()).isEqualTo("from");
        assertThat(edge.getTo()).isEqualTo("to");
    }

    @Test
    void testSetFrom() {
        Graph.Edge edge = graph.new Edge("from", "to");
        assertThrows(UnsupportedOperationException.class, () -> edge.setFrom("a"));
    }

    @Test
    void testSetTo() {
        Graph.Edge edge = graph.new Edge("from", "to");
        assertThrows(UnsupportedOperationException.class, () -> edge.setTo("a"));
    }
}
