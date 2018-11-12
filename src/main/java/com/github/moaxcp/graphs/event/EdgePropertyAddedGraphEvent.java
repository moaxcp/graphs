package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;

public class EdgePropertyAddedGraphEvent extends EdgePropertyGraphEvent implements PropertyAddedGraphEvent {
    @Override
    public EdgePropertyAddedGraphEvent withName(String name) {
        setName(name);
        return this;
    }

    @Override
    public EdgePropertyAddedGraphEvent withValue(Object value) {
        setValue(value);
        return this;
    }

    @Override
    public EdgePropertyAddedGraphEvent withGraph(UndirectedGraph graph) {
        setGraph(graph);
        return this;
    }

    @Override
    public EdgePropertyAddedGraphEvent withEdge(UndirectedGraph.Edge edge) {
        setEdge(edge);
        return this;
    }
}
