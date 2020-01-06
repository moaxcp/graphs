package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import org.greenrobot.eventbus.*;

public class DirectedEventLinkedGraph<ID> extends AbstractEventLinkedGraph<ID> {

    public DirectedEventLinkedGraph(EventBus bus) {
        super(bus);
        getBus().post(DirectedGraphCreatedEvent.<ID>builder().build());
    }

    public DirectedEventLinkedGraph(ID id, EventBus bus) {
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
