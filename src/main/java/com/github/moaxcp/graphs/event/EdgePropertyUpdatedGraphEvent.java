package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.SimpleGraph.*;
import java.util.*;

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
    public EdgePropertyUpdatedGraphEvent withGraph(SimpleGraph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public EdgePropertyUpdatedGraphEvent withEdge(Edge edge) {
        setEdge(edge);
        return this;
    }

    @Override
    public EdgePropertyUpdatedGraphEvent withOldValue(Object oldValue) {
        setOldValue(oldValue);
        return this;
    }

    @Override
    public void check() {
        super.check();
        Objects.requireNonNull(oldValue);
    }
}
