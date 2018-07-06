package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

import java.util.Objects;

public abstract class EdgeEvent extends Event {
    private Graph graph;
    private Graph.Edge edge;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public EdgeEvent withGraph(Graph graph) {
        this.graph = graph;
        return this;
    }

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
        Objects.requireNonNull(graph);
        if(!graph.getEdges().contains(edge)) {
            throw new IllegalStateException("graph does not contain edge");
        }
    }
}
