package com.github.moaxcp.graphs;

public class UndirectedGraph<T> extends AbstractGraph<T> {

    public UndirectedGraph() {

    }

    public UndirectedGraph(T id) {
        super(id);
    }

    @Override
    EdgeKey<T> newEdgeKey(T from, T to) {
        return new UndirectedEdgeKey<>(from, to);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
