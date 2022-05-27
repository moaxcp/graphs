package com.github.moaxcp.graphs;

import java.util.Objects;

/**
 * Used by directed implementations of {@link AbstractPropertyGraph}. AbstractGraph uses instances of this class as identifiers
 * for Edges in the graph.
 * @param <ID> type of identifier
 */
public final class DirectedEdgeKey<ID> extends EdgeKey<ID> {

    /**
     * Creates a DirectedEdgeKey with given from and to params.
     * @param from id of vertex
     * @param to id of vertex
     */
    public DirectedEdgeKey(ID from, ID to) {
        super(from, to);
    }

    /**
     * {@inheritDoc}
     *
     * Two DirectedEdgeKeys are equal when both from and to members match exactly.
     * @param o other object
     * @return true if from and to match exactly
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedEdgeKey that = (DirectedEdgeKey) o;
        return Objects.equals(getFrom(), that.getFrom()) && Objects.equals(getTo(), that.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo());
    }
}
