package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import org.greenrobot.eventbus.*;

public class UndirectedEventPropertyGraph<ID> extends AbstractEventPropertyGraph<ID> {

    public UndirectedEventPropertyGraph(EventBus bus) {
        super(bus);
        getBus().post(UndirectedGraphCreatedEvent.<ID>builder().build());
    }

    public UndirectedEventPropertyGraph(ID id, EventBus bus) {
        super(id, bus);
        getBus().post(UndirectedGraphCreatedEvent.<ID>builder().graphId(id).build());
    }

    @Override
    protected EdgeKey<ID> newEdgeKey(ID from, ID to) {
        return new UndirectedEdgeKey<>(from, to);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
