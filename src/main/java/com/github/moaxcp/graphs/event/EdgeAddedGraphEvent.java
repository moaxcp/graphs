package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.SimpleGraph.*;

public class EdgeAddedGraphEvent extends BaseEdgeGraphEvent {
    @Override
    public EdgeAddedGraphEvent withEdge(Edge edge) {
        setEdge(edge);
        return this;
    }

    @Override
    public EdgeAddedGraphEvent withGraph(SimpleGraph graph) {
        setGraph(graph);
        return this;
    }
}
