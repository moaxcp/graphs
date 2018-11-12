package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;

public class Truth {
    public static GraphSubject assertThat(UndirectedGraph graph) {
        return GraphSubject.assertThat(graph);
    }

    public static EdgeSubject assertThat(UndirectedGraph.Edge edge) {
        return EdgeSubject.assertThat(edge);
    }

    public static VertexSubject assertThat(UndirectedGraph.Vertex vertex) {
        return VertexSubject.assertThat(vertex);
    }
}
