package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.Graph.Vertex;

public class VertexAddedGraphEvent extends BaseVertexGraphEvent {
    @Override
    public VertexAddedGraphEvent withVertex(Vertex vertex) {
        setVertex(vertex);
        return this;
    }

    @Override
    public VertexAddedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }
}
