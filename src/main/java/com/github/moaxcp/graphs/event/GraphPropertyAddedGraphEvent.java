package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;

public class GraphPropertyAddedGraphEvent extends GraphPropertyGraphEvent implements PropertyAddedGraphEvent {
    @Override
    public GraphPropertyAddedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public GraphPropertyAddedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public GraphPropertyAddedGraphEvent withGraph(SimpleGraph graph) {
        setGraph(graph);
        return this;
    }
}
