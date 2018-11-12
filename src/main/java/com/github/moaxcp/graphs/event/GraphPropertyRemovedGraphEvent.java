package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;

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
    public GraphPropertyRemovedGraphEvent withGraph(UndirectedGraph graph) {
        setGraph(graph);
        return this;
    }
}
