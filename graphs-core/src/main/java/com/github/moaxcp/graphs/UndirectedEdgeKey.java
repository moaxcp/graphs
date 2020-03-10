package com.github.moaxcp.graphs;

import java.util.*;

/**
 * Used by undirected implementations of {@link AbstractGraph}. AbstractGraph uses instances of this class as
 * identifiers for Edges in the graph. In the case of undirected graphs two edges are equal if they connect the same
 * endpoints regardless of order.
 * @param <ID> type of identifier
 */
public final class UndirectedEdgeKey<ID> extends EdgeKey<ID> {

    /**
     * Creates an UndirectedEdgeKey with given from and to params.
     * @param from id of vertex
     * @param to id of vertex
     */
    public UndirectedEdgeKey(ID from, ID to) {
        super(from, to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedEdgeKey that = (UndirectedEdgeKey) o;
        return (Objects.equals(getFrom(), that.getFrom()) || Objects.equals(getFrom(), that.getTo())) && (Objects.equals(getTo(), that.getTo()) || Objects.equals(getTo(), that.getFrom()));
    }

    @Override
    public int hashCode() {
        return getFrom().hashCode() + getTo().hashCode();
    }
}
