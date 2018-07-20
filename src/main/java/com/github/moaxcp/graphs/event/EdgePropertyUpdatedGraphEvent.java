package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class EdgePropertyUpdatedGraphEvent extends EdgePropertyGraphEvent implements PropertyUpdatedGraphEvent {

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
    public EdgePropertyUpdatedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public EdgePropertyUpdatedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public EdgePropertyUpdatedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public EdgePropertyUpdatedGraphEvent withEdge(Graph.Edge edge) {
        setEdge(edge);
        return this;
    }

    @Override
    public EdgePropertyUpdatedGraphEvent withOldValue(Object oldValue) {
        setOldValue(oldValue);
        return this;
    }
}
