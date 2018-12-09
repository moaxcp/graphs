package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.SimpleGraph.*;
import com.google.common.truth.IterableSubject;
import org.greenrobot.eventbus.EventBus;

public class Truth {
    public static GraphSubject assertThat(SimpleGraph graph) {
        return GraphSubject.assertThat(graph);
    }

    public static EventGraphSubject assertThat(SimpleEventGraph graph) {
        return EventGraphSubject.assertThat(graph);
    }

    public static EdgeSubject assertThat(Edge edge) {
        return EdgeSubject.assertThat(edge);
    }

    public static VertexSubject assertThat(Vertex vertex) {
        return VertexSubject.assertThat(vertex);
    }

    public static IterableSubject assertEvents(Runnable action) {
        return EventBusSubject.assertEvents(action);
    }

    public static EventBusSubject assertThat(EventBus actual) {
        return EventBusSubject.assertThat(actual);
    }
}
