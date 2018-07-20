package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.Graph.Edge;

public class EdgeAddedGraphEvent extends BaseEdgeGraphEvent {
    @Override
    public EdgeAddedGraphEvent withEdge(Edge edge) {
        setEdge(edge);
        return this;
    }

    @Override
    public EdgeAddedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }
}
