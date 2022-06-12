package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import lombok.experimental.UtilityClass;
import org.greenrobot.eventbus.EventBus;

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

    public static GreenRobotEventBusSubject assertThat(EventBus actual) {
        return GreenRobotEventBusSubject.assertThat(actual);
    }

    public static GuavaEventBusSubject assertThat(com.google.common.eventbus.EventBus actual) {
        return GuavaEventBusSubject.assertThat(actual);
    }
}
