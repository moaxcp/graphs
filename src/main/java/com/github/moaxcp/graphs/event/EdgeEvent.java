package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

import java.util.Objects;

public abstract class EdgeEvent extends Event {
    private Graph.Edge edge;

    public Graph.Edge getEdge() {
        return edge;
    }

    public void setEdge(Graph.Edge edge) {
        this.edge = edge;
    }

    public EdgeEvent withEdge(Graph.Edge edge) {
        this.edge = edge;
        return this;
    }

    public void checkEvent() {
        Objects.requireNonNull(edge);
        Objects.requireNonNull(getGraph());
    }
}
