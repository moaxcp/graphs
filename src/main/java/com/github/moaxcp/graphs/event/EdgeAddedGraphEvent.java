package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedGraph.Edge;

public class EdgeAddedGraphEvent extends BaseEdgeGraphEvent {
    @Override
    public EdgeAddedGraphEvent withEdge(Edge edge) {
        setEdge(edge);
        return this;
    }

    @Override
    public EdgeAddedGraphEvent withGraph(UndirectedGraph graph) {
        setGraph(graph);
        return this;
    }
}
