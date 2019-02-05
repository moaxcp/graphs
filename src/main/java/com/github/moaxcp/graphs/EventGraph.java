package com.github.moaxcp.graphs;

/**
 * An implementation of graphs which sends {@link com.github.moaxcp.graphs.events.GraphEvent}s when the graph is
 * modified.
 * @param <ID> type of all identifiers in graph
 * @param <BUS> type of event bus
 */
public interface EventGraph<ID, BUS> extends Graph<ID> {
    BUS getBus();
}
