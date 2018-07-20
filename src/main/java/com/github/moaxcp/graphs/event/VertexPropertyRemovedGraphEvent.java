package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class VertexPropertyRemovedGraphEvent extends VertexPropertyGraphEvent implements PropertyRemovedGraphEvent {
    @Override
    public VertexPropertyRemovedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public VertexPropertyRemovedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public VertexPropertyRemovedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public VertexPropertyRemovedGraphEvent withVertex(Graph.Vertex vertex) {
        setVertex(vertex);
        return this;
    }
}
