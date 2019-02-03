package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.DirectedGraphCreated;
import org.greenrobot.eventbus.EventBus;

public class DirectedEventGraph<ID> extends AbstractEventGraph<ID> {

    public DirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(new DirectedGraphCreated.Builder<ID>().build());
    }

    public DirectedEventGraph(ID id, EventBus bus) {
        super(id, bus);
        getBus().post(new DirectedGraphCreated.Builder<ID>().graphId(id).build());
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
