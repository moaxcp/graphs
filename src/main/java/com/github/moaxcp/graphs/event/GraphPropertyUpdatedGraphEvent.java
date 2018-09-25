package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

import java.util.Objects;

public class GraphPropertyUpdatedGraphEvent extends GraphPropertyGraphEvent implements PropertyUpdatedGraphEvent {

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
    public GraphPropertyUpdatedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public GraphPropertyUpdatedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public GraphPropertyUpdatedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public GraphPropertyUpdatedGraphEvent withOldValue(Object oldValue) {
        setOldValue(oldValue);
        return this;
    }

    @Override
    public void check() {
        super.check();
        Objects.requireNonNull(oldValue);
    }
}
