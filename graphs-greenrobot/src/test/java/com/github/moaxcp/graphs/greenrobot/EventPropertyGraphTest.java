package com.github.moaxcp.graphs.greenrobot;

import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class EventPropertyGraphTest {
    @Test
    void undirectedEventGraphBus() {
        var bus = EventBus.builder().build();
        var graph = new UndirectedEventPropertyGraph(bus);
        assertThat(graph.getBus()).isSameInstanceAs(bus);
    }

    @Test
    void undirectedEventGraphIdWithBus() {
        var bus = EventBus.builder().build();
        var graph = new UndirectedEventPropertyGraph<>("id", bus);
        assertThat(graph.getBus()).isSameInstanceAs(bus);
    }
    @Test
    void directedEventGraphBus() {
        var bus = EventBus.builder().build();
        var graph = new DirectedEventPropertyGraph(bus);
        assertThat(graph.getBus()).isSameInstanceAs(bus);
    }

    @Test
    void directedEventGraphIdWithBus() {
        var bus = EventBus.builder().build();
        var graph = new DirectedEventPropertyGraph<>("id", bus);
        assertThat(graph.getBus()).isSameInstanceAs(bus);
    }
}
