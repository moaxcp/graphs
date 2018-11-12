package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedGraph.Edge;

public class EdgeRemovedGraphEvent extends BaseEdgeGraphEvent {
    @Override
    public EdgeRemovedGraphEvent withEdge(Edge edge) {
        setEdge(edge);
        return this;
    }

    @Override
    public EdgeRemovedGraphEvent withGraph(UndirectedGraph graph) {
        setGraph(graph);
        return this;
    }
}
