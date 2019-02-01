package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.UndirectedGraphCreated;
import org.greenrobot.eventbus.EventBus;

public class UndirectedEventGraph<T> extends AbstractEventGraph<T> {

    public UndirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(new UndirectedGraphCreated.Builder<T>().build());
    }

    public UndirectedEventGraph(T id, EventBus bus) {
        super(id, bus);
        getBus().post(new UndirectedGraphCreated.Builder<T>().graphId(id).build());
    }

    @Override
    protected EdgeKey<T> newEdgeKey(T from, T to) {
        return new UndirectedEdgeKey<>(from, to);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
