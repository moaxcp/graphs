package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import org.greenrobot.eventbus.*;

public class DirectedEventGraph<ID> extends AbstractEventGraph<ID> {

    public DirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(DirectedGraphCreatedEvent.<ID>builder().build());
    }

    public DirectedEventGraph(ID id, EventBus bus) {
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
