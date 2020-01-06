package com.github.moaxcp.graphs;

public class DirectedLinkedGraph<ID> extends AbstractLinkedGraph<ID> {

    public DirectedLinkedGraph() {

    }

    public DirectedLinkedGraph(ID id) {
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
