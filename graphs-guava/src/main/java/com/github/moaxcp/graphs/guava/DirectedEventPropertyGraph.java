package com.github.moaxcp.graphs.guava;

import com.github.moaxcp.graphs.DirectedEdgeKey;
import com.github.moaxcp.graphs.EdgeKey;
import com.github.moaxcp.graphs.events.DirectedGraphCreatedEvent;
import com.google.common.eventbus.EventBus;

public class DirectedEventPropertyGraph<ID> extends AbstractEventPropertyGraph<ID> {

    public DirectedEventPropertyGraph(EventBus bus) {
        super(bus);
        getBus().post(DirectedGraphCreatedEvent.<ID>builder().build());
    }

    public DirectedEventPropertyGraph(ID id, EventBus bus) {
        super(id, bus);
        getBus().post(DirectedGraphCreatedEvent.<ID>builder().graphId(id).build());
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
