package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeTest {
    Graph graph = new Graph("graph");
    @Test
    void testEdge() {
        var edge = graph.edge("from", "to");
        assertThat(edge.getFrom()).isEqualTo("from");
        assertThat(edge.getTo()).isEqualTo("to");
    }

    @Test
    void testSetFrom() {
        var edge = graph.edge("from", "to");
        edge.setFrom("A");
        assertThat(graph.getVertices()).containsKey("A");
        assertThat(edge.getLocal()).containsExactly("from", "A", "to", "to");
        assertThat(graph.getEdges()).contains(edge);
    }

    @Test
    void testSetTo() {
        var edge = graph.edge("from", "to");
        edge.setTo("B");
        assertThat(graph.getVertices()).containsKey("B");
        assertThat(edge.getLocal()).containsExactly("from", "from", "to", "B");
        assertThat(graph.getEdges()).contains(edge);
    }

    @Test
    void testFrom() {
        var edge = graph.edge("from", "to");
        assertThat(edge.from().getId()).isEqualTo("from");
    }

    @Test
    void testTo() {
        var edge = graph.edge("from", "to");
        assertThat(edge.to().getId()).isEqualTo("to");
    }

    @Test
    void testSetProperty() {
        var edge = graph.edge("from", "to");
        edge.setProperty("key", "value");
        assertThat(edge.getProperty("key")).isEqualTo("value");
    }

    @Test
    void testSetPropertyFrom() {
        var edge = graph.edge("from", "to");
        edge.setProperty("from", "A");
        assertThat(edge.getFrom()).isEqualTo("A");
    }

    @Test
    void testEqualsNull() {
        var edge = graph.edge("from", "to");
        assertNotEquals(edge, null);
    }

    @Test
    void testEqualsReflexive() {
        var edge = graph.edge("from", "to");
        assertEquals(edge, edge);
    }

    @Test
    void testEqualsSymmetric() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge1);
    }

    @Test
    void testEqualsTransitive() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "to");
        var edge3 = new Graph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge3);
        assertEquals(edge1, edge3);
    }

    @Test
    void testConsistent() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertEquals(edge1, edge2);
        assertEquals(edge1, edge2);
    }

    @Test
    void testNonDirectional() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("to", "from");
        assertEquals(edge1, edge2);
    }

    @Test
    void testAlmostEqualTo() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "from");
        assertNotEquals(edge1, edge2);
    }

    @Test
    void testAlmostEqualFrom() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("to", "to");
        assertNotEquals(edge1, edge2);
    }

    @Test
    void testHashCodeConsistent() {
        var edge = graph.edge("from", "to");
        int hashCode = edge.hashCode();
        assertThat(edge.hashCode()).isEqualTo(hashCode);
        assertThat(edge.hashCode()).isEqualTo(hashCode);
    }

    @Test
    void testHashCodeForEqualEdges() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertThat(edge1.hashCode()).isEqualTo(edge2.hashCode());
    }
}
