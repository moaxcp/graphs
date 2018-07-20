package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

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
    public VertexPropertyAddedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public VertexPropertyAddedGraphEvent withVertex(Graph.Vertex vertex) {
        setVertex(vertex);
        return this;
    }
}
