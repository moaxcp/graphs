package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class GraphPropertyRemovedGraphEvent extends GraphPropertyGraphEvent implements PropertyRemovedGraphEvent {
    @Override
    public GraphPropertyRemovedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public GraphPropertyRemovedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public GraphPropertyRemovedGraphEvent withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }
}
