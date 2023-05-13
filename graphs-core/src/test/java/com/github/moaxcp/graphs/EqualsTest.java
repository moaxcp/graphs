package com.github.moaxcp.graphs;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class EqualsTest {

    @Test
    void undirectedEmpty() {
        assertThat(new UndirectedPropertyGraph<>()).isEqualTo(new UndirectedPropertyGraph<>());
    }

    @Test
    void directedEmpty() {
        assertThat(new DirectedPropertyGraph<>()).isEqualTo(new DirectedPropertyGraph<>());
    }

    @Test
    void undirected() {
        EqualsVerifier
                .forClass(UndirectedPropertyGraph.class)
                .withIgnoredFields("edgeIds", "adjacentEdges", "inEdges", "outEdges")
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    void directed() {
        EqualsVerifier
                .forClass(DirectedPropertyGraph.class)
                .withIgnoredFields("edgeIds", "adjacentEdges", "inEdges", "outEdges")
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }
}
