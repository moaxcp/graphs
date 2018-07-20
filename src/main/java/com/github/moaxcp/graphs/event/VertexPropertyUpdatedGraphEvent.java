package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class VertexPropertyUpdatedGraphEvent extends VertexPropertyGraphEvent implements PropertyUpdatedGraphEvent {
    private Object oldValue;

    @Override
    public Object getOldValue() {
        return oldValue;
    }

    @Override
    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    @Override
    public VertexPropertyUpdatedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public VertexPropertyUpdatedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public VertexPropertyUpdatedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public VertexPropertyUpdatedGraphEvent withVertex(Graph.Vertex vertex) {
        setVertex(vertex);
        return this;
    }

    @Override
    public VertexPropertyUpdatedGraphEvent withOldValue(Object oldValue) {
        setOldValue(oldValue);
        return this;
    }
}
