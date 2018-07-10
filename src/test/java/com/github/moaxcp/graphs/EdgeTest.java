package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class EdgeTest {
    Graph graph = new Graph("graph");
    @Test
    void testEdge() {
        Graph.Edge edge = graph.edge("from", "to");
        assertThat(edge.getFrom()).isEqualTo("from");
        assertThat(edge.getTo()).isEqualTo("to");
    }

    @Test
    void testSetFrom() {
        Graph.Edge edge = graph.edge("from", "to");
        edge.setFrom("A");
        assertThat(graph.getVertices()).containsKey("A");
        assertThat(edge).containsExactly("from", "A", "to", "to");
        assertThat(graph.getEdges()).contains(edge);
    }

    @Test
    void testSetTo() {
        Graph.Edge edge = graph.edge("from", "to");
        edge.setTo("B");
        assertThat(graph.getVertices()).containsKey("B");
        assertThat(edge).containsExactly("from", "from", "to", "B");
        assertThat(graph.getEdges()).contains(edge);
    }
}
