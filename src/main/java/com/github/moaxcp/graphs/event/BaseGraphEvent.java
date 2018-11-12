package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;

import java.util.Objects;

public abstract class BaseGraphEvent implements GraphEvent {

    private UndirectedGraph graph;

    @Override
    public UndirectedGraph getGraph() {
        return graph;
    }

    @Override
    public void setGraph(UndirectedGraph graph) {
        this.graph = graph;
    }

    @Override
    public void check() {
        Objects.requireNonNull(graph);
    }
}
