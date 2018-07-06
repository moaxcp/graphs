package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class EdgeAdded extends EdgeEvent {

    public EdgeAdded withGraph(Graph graph) {
        super.withGraph(graph);
        return this;
    }

    public EdgeAdded withEdge(Graph.Edge edge) {
        super.withEdge(edge);
        return this;
    }
}
