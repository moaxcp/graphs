package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.SimpleGraph.*;

public class VertexRemovedGraphEvent extends BaseVertexGraphEvent {
    @Override
    public VertexRemovedGraphEvent withVertex(Vertex vertex) {
        setVertex(vertex);
        return this;
    }

    @Override
    public VertexRemovedGraphEvent withGraph(SimpleGraph graph) {
        setGraph(graph);
        return this;
    }
}
