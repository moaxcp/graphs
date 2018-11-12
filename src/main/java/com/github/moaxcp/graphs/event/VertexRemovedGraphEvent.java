package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedGraph.Vertex;

public class VertexRemovedGraphEvent extends BaseVertexGraphEvent {
    @Override
    public VertexRemovedGraphEvent withVertex(Vertex vertex) {
        setVertex(vertex);
        return this;
    }

    @Override
    public VertexRemovedGraphEvent withGraph(UndirectedGraph graph) {
        setGraph(graph);
        return this;
    }
}
