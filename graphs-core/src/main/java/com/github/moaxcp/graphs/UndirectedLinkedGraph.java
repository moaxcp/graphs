package com.github.moaxcp.graphs;

public class UndirectedLinkedGraph<ID> extends AbstractLinkedGraph<ID> {

    public UndirectedLinkedGraph() {

    }

    public UndirectedLinkedGraph(ID id) {
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
