package com.github.moaxcp.graphs.greenrobot;

import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import testframework.stubs.TestEvent;
import testframework.stubs.TestHandler;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphTest {
    UndirectedEventGraph graph = new UndirectedEventGraph("graph");

    @Test
    void testPublishSubscribe() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        graph.publish(new TestEvent());
        assertThat(handler.event).isInstanceOf(TestEvent.class);
    }
}
