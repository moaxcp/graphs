package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.Graph.*;
import com.github.moaxcp.graphs.greenrobot.EventGraph;
import com.google.common.truth.IterableSubject;
import org.greenrobot.eventbus.EventBus;

public class Truth {
    public static GraphSubject assertThat(Graph graph) {
        return GraphSubject.assertThat(graph);
    }

    public static EventGraphSubject assertThat(EventGraph graph) {
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
