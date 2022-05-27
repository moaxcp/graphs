package com.github.moaxcp.graphs;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

public class EqualsTest {
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
