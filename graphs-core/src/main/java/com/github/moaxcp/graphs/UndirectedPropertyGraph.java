package com.github.moaxcp.graphs;

public class UndirectedPropertyGraph<ID> extends AbstractPropertyGraph<ID> {

    public UndirectedPropertyGraph() {

    }

    public UndirectedPropertyGraph(ID id) {
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
