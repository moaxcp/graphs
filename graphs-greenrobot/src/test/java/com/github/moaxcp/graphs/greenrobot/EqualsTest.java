package com.github.moaxcp.graphs.greenrobot;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

public class EqualsTest {

    @Test
    void undirected() {
        EqualsVerifier
                .forClass(UndirectedEventGraph.class)
                .withIgnoredFields("edgeIds", "adjacentEdges", "inEdges", "outEdges", "bus")
                .withPrefabValues(EventBus.class, EventBus.getDefault(), EventBus.builder().build())
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    void directed() {
        EqualsVerifier
                .forClass(DirectedEventGraph.class)
                .withIgnoredFields("edgeIds", "adjacentEdges", "inEdges", "outEdges", "bus")
                .withPrefabValues(EventBus.class, EventBus.getDefault(), EventBus.builder().build())
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }
}
