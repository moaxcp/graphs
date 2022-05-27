package com.github.moaxcp.graphs;

import java.util.*;

/**
 * Used by undirected implementations of {@link AbstractPropertyGraph}. AbstractGraph uses instances of this class as
 * identifiers for Edges in the graph. In the case of undirected graphs two edges are equal if they connect the same
 * endpoints regardless of order.
 * @param <ID> type of identifier
 */
public final class UndirectedEdgeKey<ID> extends EdgeKey<ID> {

    /**
     * Creates an UndirectedEdgeKey with given source and target params.
     * @param source id of vertex
     * @param target id of vertex
     */
    public UndirectedEdgeKey(ID source, ID target) {
        super(source, target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedEdgeKey that = (UndirectedEdgeKey) o;
        return (Objects.equals(getSource(), that.getSource()) || Objects.equals(getSource(), that.getTarget())) && (Objects.equals(getTarget(), that.getTarget()) || Objects.equals(getTarget(), that.getSource()));
    }

    @Override
    public int hashCode() {
        return getSource().hashCode() + getTarget().hashCode();
    }
}
