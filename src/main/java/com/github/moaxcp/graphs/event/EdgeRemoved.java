package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class EdgeRemoved extends EdgeEvent {

    public EdgeRemoved withGraph(Graph graph) {
        super.withGraph(graph);
        return this;
    }

    public EdgeRemoved withEdge(Graph.Edge edge) {
        super.withEdge(edge);
        return this;
    }
}
