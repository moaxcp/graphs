package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.events.UndirectedGraphCreated;
import java.util.*;
import org.greenrobot.eventbus.EventBus;

public class UndirectedEventGraph<T> extends AbstractEventGraph<T> {
    public class UndirectedEventEdge extends EventEdge {
        protected UndirectedEventEdge(T from, T to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public boolean isDirected() {
            return false;
        }
    }

    public class UndirectedEventVertex extends EventVertex {
        protected UndirectedEventVertex(T id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge<T>> traverseEdges() {
            return adjacentEdges();
        }
    }

    public UndirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(new UndirectedGraphCreated.Builder<T>().build());
    }

    public UndirectedEventGraph(T id, EventBus bus) {
        super(id, bus);
        getBus().post(new UndirectedGraphCreated.Builder<T>().graphId(id).build());
    }

    @Override
    Vertex<T> newVertex(T id, Map<String, Object> inherited) {
        return new UndirectedEventVertex(id, inherited);
    }

    @Override
    Edge<T> newEdge(T from, T to, Map<String, Object> inherited) {
        return new UndirectedEventEdge(from, to, inherited);
    }

    @Override
    EdgeKey<T> newEdgeKey(T from, T to) {
        return new UndirectedEdgeKey<>(from, to);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
