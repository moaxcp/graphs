package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.PropertyGraph.*;
import com.google.common.truth.*;
import lombok.experimental.*;
import org.greenrobot.eventbus.*;

@UtilityClass
public class Truth {
    public static GraphSubject assertThat(PropertyGraph graph) {
        return GraphSubject.assertThat(graph);
    }

    public static EdgeSubject assertThat(Edge edge) {
        return EdgeSubject.assertThat(edge);
    }

    public static VertexSubject assertThat(Vertex vertex) {
        return VertexSubject.assertThat(vertex);
    }

    public static IterableSubject assertEvents(Runnable action) {
        return GreenRobotEventBusSubject.assertEvents(action);
    }

    public static GreenRobotEventBusSubject assertThat(EventBus actual) {
        return GreenRobotEventBusSubject.assertThat(actual);
    }
}
