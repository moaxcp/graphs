package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.Graph.Edge;

public class EdgeRemovedGraphEvent extends BaseEdgeGraphEvent {
    @Override
    public EdgeRemovedGraphEvent withEdge(Edge edge) {
        setEdge(edge);
        return this;
    }

    @Override
    public EdgeRemovedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }
}
