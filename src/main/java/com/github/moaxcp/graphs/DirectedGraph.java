package com.github.moaxcp.graphs;

public class DirectedGraph<T> extends AbstractSimpleGraph<T> {

    public DirectedGraph() {

    }

    public DirectedGraph(T id) {
        super(id);
    }

    @Override
    EdgeKey<T> newEdgeKey(T from, T to) {
        return new DirectedEdgeKey<>(from, to);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
