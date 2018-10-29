package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.EdgeAddedGraphEvent;
import com.github.moaxcp.graphs.event.EdgeRemovedGraphEvent;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import stubs.TestEvent;
import stubs.TestHandler;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.github.moaxcp.graphs.GraphSubject.assertThat;

public class GraphTest {
    Graph graph = new Graph("graph");

    @Test
    void testAddNewEdgeEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var edge = graph.edge("from", "to");
        assertThat(handler.event).isInstanceOf(EdgeAddedGraphEvent.class);
        var event = (EdgeAddedGraphEvent) handler.event;
        assertThat(event.getGraph()).isSameAs(graph);
        assertThat(event.getEdge()).isSameAs(edge);
    }

    @Test
    void testRemoveEdgeEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var edge = graph.edge("from", "to");
        graph.removeEdge("from", "to");
        assertThat(handler.event).isInstanceOf(EdgeRemovedGraphEvent.class);
        var event = (EdgeRemovedGraphEvent) handler.event;
        assertThat(event.getGraph()).isSameAs(graph);
        assertThat(event.getEdge()).isSameAs(edge);
    }

    @Test
    void testPublishSubscribe() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        graph.publish(new TestEvent());
        assertThat(handler.event).isInstanceOf(TestEvent.class);
    }
}
