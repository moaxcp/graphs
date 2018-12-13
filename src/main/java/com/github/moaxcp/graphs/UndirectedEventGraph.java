package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.events.Builders.*;
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

        @Override
        public boolean equals(T from, T to) {
            return (Objects.equals(getFrom(), from) || Objects.equals(getFrom(), to)) && (Objects.equals(getTo(), to) || Objects.equals(getTo(), from));
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
        getBus().post(undirectedGraphCreated().build());
    }

    public UndirectedEventGraph(T id, EventBus bus) {
        super(id, bus);
        getBus().post(undirectedGraphCreated().graphId(id).build());
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
    public boolean isDirected() {
        return false;
    }
}
