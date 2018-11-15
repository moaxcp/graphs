package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.SimpleGraph.*;

public class VertexAddedGraphEvent extends BaseVertexGraphEvent {
    @Override
    public VertexAddedGraphEvent withVertex(Vertex vertex) {
        setVertex(vertex);
        return this;
    }

    @Override
    public VertexAddedGraphEvent withGraph(SimpleGraph graph) {
        setGraph(graph);
        return this;
    }
}
