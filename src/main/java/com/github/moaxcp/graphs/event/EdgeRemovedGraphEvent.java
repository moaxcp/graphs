package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.SimpleGraph.*;

public class EdgeRemovedGraphEvent extends BaseEdgeGraphEvent {
    @Override
    public EdgeRemovedGraphEvent withEdge(Edge edge) {
        setEdge(edge);
        return this;
    }

    @Override
    public EdgeRemovedGraphEvent withGraph(SimpleGraph graph) {
        setGraph(graph);
        return this;
    }
}
