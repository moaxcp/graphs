package com.github.moaxcp.graphs.greenrobot;

import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class EventGraphTest {
    @Test
    void undirectedEventGraphBus() {
        var bus = EventBus.builder().build();
        var graph = new UndirectedEventLinkedGraph(bus);
        assertThat(graph.getBus()).isSameInstanceAs(bus);
    }

    @Test
    void undirectedEventGraphIdWithBus() {
        var bus = EventBus.builder().build();
        var graph = new UndirectedEventLinkedGraph<>("id", bus);
        assertThat(graph.getBus()).isSameInstanceAs(bus);
    }
    @Test
    void directedEventGraphBus() {
        var bus = EventBus.builder().build();
        var graph = new DirectedEventLinkedGraph(bus);
        assertThat(graph.getBus()).isSameInstanceAs(bus);
    }

    @Test
    void directedEventGraphIdWithBus() {
        var bus = EventBus.builder().build();
        var graph = new DirectedEventLinkedGraph<>("id", bus);
        assertThat(graph.getBus()).isSameInstanceAs(bus);
    }
}
