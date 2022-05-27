package com.github.moaxcp.graphs;

public class DirectedPropertyGraph<ID> extends AbstractPropertyGraph<ID> {

    public DirectedPropertyGraph() {

    }

    public DirectedPropertyGraph(ID id) {
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
