package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedGraph.Vertex;

public class VertexAddedGraphEvent extends BaseVertexGraphEvent {
    @Override
    public VertexAddedGraphEvent withVertex(Vertex vertex) {
        setVertex(vertex);
        return this;
    }

    @Override
    public VertexAddedGraphEvent withGraph(UndirectedGraph graph) {
        setGraph(graph);
        return this;
    }
}
