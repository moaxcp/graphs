package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class EdgeAttributeAdded extends EdgeAttributeEvent {

    public EdgeAttributeAdded withGraph(Graph graph) {
        super.withGraph(graph);
        return this;
    }

    public EdgeAttributeAdded withEdge(Graph.Edge edge) {
        super.withEdge(edge);
        return this;
    }
}
