package com.github.moaxcp.graphs;

public class DirectedGraph<ID> extends AbstractGraph<ID> {

    public DirectedGraph() {

    }

    public DirectedGraph(ID id) {
        super(id);
    }

    @Override
    protected EdgeKey<ID> newEdgeKey(ID from, ID to) {
        return new DirectedEdgeKey<>(from, to);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
