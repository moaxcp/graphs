package com.github.moaxcp.graphs;

import static com.google.common.truth.Truth.assertThat;
import nl.jqno.equalsverifier.*;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import testframework.*;

public class EventGraphTest {
    @Test
    void undirectedEventGraphBus() {
        var bus = EventBus.builder().build();
        var graph = new UndirectedEventGraph(bus);
        assertThat(graph.getBus()).isSameAs(bus);
    }

    @Test
    void undirectedEventGraphIdWithBus() {
        var bus = EventBus.builder().build();
        var graph = new UndirectedEventGraph<>("id", bus);
        assertThat(graph.getBus()).isSameAs(bus);
    }
    @Test
    void directedEventGraphBus() {
        var bus = EventBus.builder().build();
        var graph = new DirectedEventGraph(bus);
        assertThat(graph.getBus()).isSameAs(bus);
    }

    @Test
    void directedEventGraphIdWithBus() {
        var bus = EventBus.builder().build();
        var graph = new DirectedEventGraph<>("id", bus);
        assertThat(graph.getBus()).isSameAs(bus);
    }

    @NonEventSimpleGraphs
    void testNonEventSimpleGraphEquals(Graph<String> graph) {
        EqualsVerifier
            .forClass(graph.getClass())
            .withIgnoredFields("edgeIds", "adjacentEdges", "inEdges", "outEdges")
            .suppress(Warning.NONFINAL_FIELDS)
            .verify();
    }

    @EventSimpleGraphs
    void testEventSimpleGraphEquals(Graph<String> graph) {
        EqualsVerifier
            .forClass(graph.getClass())
            .withIgnoredFields("edgeIds", "adjacentEdges", "inEdges", "outEdges", "bus")
            .withPrefabValues(EventBus.class, EventBus.getDefault(), EventBus.builder().build())
            .suppress(Warning.NONFINAL_FIELDS)
            .verify();
    }
}
