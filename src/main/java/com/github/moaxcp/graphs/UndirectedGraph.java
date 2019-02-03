package com.github.moaxcp.graphs;

public class UndirectedGraph<ID> extends AbstractGraph<ID> {

    public UndirectedGraph() {

    }

    public UndirectedGraph(ID id) {
        super(id);
    }

    @Override
    protected EdgeKey<ID> newEdgeKey(ID from, ID to) {
        return new UndirectedEdgeKey<>(from, to);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
