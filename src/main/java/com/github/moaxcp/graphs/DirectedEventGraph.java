package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.events.DirectedGraphCreated;
import org.greenrobot.eventbus.EventBus;

public class DirectedEventGraph<T> extends AbstractEventGraph<T> {

    public DirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(new DirectedGraphCreated.Builder<T>().build());
    }

    public DirectedEventGraph(T id, EventBus bus) {
        super(id, bus);
        getBus().post(new DirectedGraphCreated.Builder<T>().graphId(id).build());
    }

    @Override
    EdgeKey<T> newEdgeKey(T from, T to) {
        return new DirectedEdgeKey<>(from, to);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
