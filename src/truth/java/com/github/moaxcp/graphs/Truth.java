package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.SimpleGraph.*;

public class Truth {
    public static GraphSubject assertThat(SimpleGraph graph) {
        return GraphSubject.assertThat(graph);
    }

    public static EdgeSubject assertThat(Edge edge) {
        return EdgeSubject.assertThat(edge);
    }

    public static VertexSubject assertThat(Vertex vertex) {
        return VertexSubject.assertThat(vertex);
    }
}
