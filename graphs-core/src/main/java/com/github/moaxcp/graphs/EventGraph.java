package com.github.moaxcp.graphs;

/**
 * An implementation of graphs which sends {@link com.github.moaxcp.graphs.events.GraphEvent}s when the graph is
 * modified.
 * @param <ID> type of all identifiers in graph
 */
public interface EventGraph<ID> extends Graph<ID> {
}
