package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.SimpleGraph.*;

public class VertexPropertyAddedGraphEvent extends VertexPropertyGraphEvent implements PropertyAddedGraphEvent {
    @Override
    public VertexPropertyAddedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public VertexPropertyAddedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public VertexPropertyAddedGraphEvent withGraph(SimpleGraph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public VertexPropertyAddedGraphEvent withVertex(Vertex vertex) {
        setVertex(vertex);
        return this;
    }
}
