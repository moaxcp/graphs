package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

import java.util.Objects;

public abstract class BaseGraphEvent implements GraphEvent {

    private Graph graph;

    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void check() {
        Objects.requireNonNull(graph);
    }
}
