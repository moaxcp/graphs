package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class EdgeAttributeRemoved extends EdgeAttributeEvent {

    public EdgeAttributeRemoved withGraph(Graph graph) {
        super.withGraph(graph);
        return this;
    }

    public EdgeAttributeRemoved withEdge(Graph.Edge edge) {
        super.withEdge(edge);
        return this;
    }
}
