package com.github.moaxcp.graphs;

import static com.google.common.truth.Truth.assertThat;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

public class GraphTest {
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
}
