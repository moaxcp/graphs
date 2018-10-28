package com.github.moaxcp.graphs;

public class Truth {
    public static GraphSubject assertThat(Graph graph) {
        return GraphSubject.assertThat(graph);
    }

    public static EdgeSubject assertThat(Graph.Edge edge) {
        return EdgeSubject.assertThat(edge);
    }

    public static VertexSubject assertThat(Graph.Vertex vertex) {
        return VertexSubject.assertThat(vertex);
    }
}
