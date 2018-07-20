package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph.Edge;

import java.util.Objects;

public abstract class BaseEdgeGraphEvent extends BaseGraphEvent implements EdgeGraphEvent {
    private Edge edge;

    @Override
    public Edge getEdge() {
        return edge;
    }

    @Override
    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    @Override
    public void check() {
        Objects.requireNonNull(edge);
    }
}
