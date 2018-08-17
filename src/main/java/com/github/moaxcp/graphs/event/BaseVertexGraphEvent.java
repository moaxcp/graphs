package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph.Vertex;

import java.util.Objects;

public abstract class BaseVertexGraphEvent extends BaseGraphEvent implements VertexGraphEvent {
    private Vertex vertex;

    @Override
    public Vertex getVertex() {
        return vertex;
    }

    @Override
    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    @Override
    public void check() {
        super.check();
        Objects.requireNonNull(vertex);
    }
}
