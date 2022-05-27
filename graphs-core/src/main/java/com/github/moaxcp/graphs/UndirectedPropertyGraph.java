package com.github.moaxcp.graphs;

public class UndirectedPropertyGraph<ID> extends AbstractPropertyGraph<ID> {

    public UndirectedPropertyGraph() {

    }

    public UndirectedPropertyGraph(ID id) {
        super(id);
    }

    @Override
    protected EdgeKey<ID> newEdgeKey(ID source, ID target) {
        return new UndirectedEdgeKey<>(source, target);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
