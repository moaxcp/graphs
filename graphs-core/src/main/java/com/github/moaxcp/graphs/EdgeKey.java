package com.github.moaxcp.graphs;

import static java.util.Objects.*;

/**
 * Key for identifying edges within a collection such as Map. Extensions of this class define how two edges are equal
 * in terms of the graph. Implementing classes must define equals and hashCode for the type of Edge used
 * in the graph. Classes implementing AbstractGraph should typically use DirectedEdgeKey or UndirectedEdgeKey
 * instead of extending this class.
 *
 * @param <ID> type of identifiers
 */
public abstract class EdgeKey<ID> {
    private final ID source;
    private final ID target;

    /**
     * Creates an EdgeKey with given source and target params.
     * @param source vertex id
     * @param target vertex id
     */
    public EdgeKey(ID source, ID target) {
        this.source = requireNonNull(source);
        this.target = requireNonNull(target);
    }

    /**
     * Returns source vertex id.
     * @return source id of vertex
     */
    public final ID getSource() {
        return source;
    }

    /**
     * Returns target vertex id.
     * @return target id of vertex
     */
    public final ID getTarget() {
        return target;
    }
}
