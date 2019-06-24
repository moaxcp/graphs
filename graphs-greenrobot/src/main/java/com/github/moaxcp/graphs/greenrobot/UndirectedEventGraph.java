package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.EdgeKey;
import com.github.moaxcp.graphs.UndirectedEdgeKey;
import com.github.moaxcp.graphs.events.UndirectedGraphCreated;
import org.greenrobot.eventbus.EventBus;

public class UndirectedEventGraph<ID> extends AbstractEventGraph<ID> {

    public UndirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(new UndirectedGraphCreated.Builder<ID>().build());
    }

    public UndirectedEventGraph(ID id, EventBus bus) {
        super(id, bus);
        getBus().post(new UndirectedGraphCreated.Builder<ID>().graphId(id).build());
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
