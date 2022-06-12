package com.github.moaxcp.graphs.guava;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.Test;

public class EqualsTest {

    @Test
    void undirected() {
        EqualsVerifier
                .forClass(UndirectedEventPropertyGraph.class)
                .withIgnoredFields("edgeIds", "adjacentEdges", "inEdges", "outEdges", "bus")
                .withPrefabValues(EventBus.class, new EventBus(), new EventBus())
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    void directed() {
        EqualsVerifier
                .forClass(DirectedEventPropertyGraph.class)
                .withIgnoredFields("edgeIds", "adjacentEdges", "inEdges", "outEdges", "bus")
                .withPrefabValues(EventBus.class, new EventBus(), new EventBus())
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }
}
