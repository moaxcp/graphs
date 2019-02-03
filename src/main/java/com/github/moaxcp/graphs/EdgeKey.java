package com.github.moaxcp.graphs;

import static java.util.Objects.requireNonNull;

/**
 * Key for identifying edges within a collection such as Map. Extensions of this class define how two edges are equal
 * in terms of the graph. Implementing classes must define equals and hashCode for the type of Edge used
 * in the graph. Classes implementing AbstractGraph should typically use DirectedEdgeKey or UndirectedEdgeKey
 * instead of extending this class.
 *
 * @param <ID> type of identifiers
 */
public abstract class EdgeKey<ID> {
    private ID from;
    private ID to;

    /**
     * Creates an EdgeKey with given from and to params.
     * @param from
     * @param to
     */
    public EdgeKey(ID from, ID to) {
        this.from = requireNonNull(from);
        this.to = requireNonNull(to);
    }

    /**
     * Returns 'from' vertex id.
     * @return from id of vertex
     */
    public final ID getFrom() {
        return from;
    }

    /**
     * Returns 'to' vertex id.
     * @return to id of vertex
     */
    public final ID getTo() {
        return to;
    }
}
