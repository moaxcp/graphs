package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.Graph.Vertex;

public class VertexRemovedGraphEvent extends BaseVertexGraphEvent {
    @Override
    public VertexRemovedGraphEvent withVertex(Vertex vertex) {
        setVertex(vertex);
        return this;
    }

    @Override
    public VertexRemovedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }
}
