package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;

public class EdgePropertyRemovedGraphEvent extends EdgePropertyGraphEvent implements PropertyRemovedGraphEvent {
    @Override
    public EdgePropertyRemovedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public EdgePropertyRemovedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public EdgePropertyRemovedGraphEvent withGraph(UndirectedGraph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public EdgePropertyRemovedGraphEvent withEdge(UndirectedGraph.Edge edge) {
        setEdge(edge);
        return this;
    }
}
