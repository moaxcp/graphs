package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.events.DirectedGraphCreated;
import java.util.*;
import org.greenrobot.eventbus.EventBus;

public class DirectedEventGraph<T> extends AbstractEventGraph<T> {
    public class DirectedEventEdge extends EventEdge {
        private DirectedEventEdge(T from, T to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public boolean isDirected() {
            return true;
        }

        @Override
        public boolean equals(T from, T to) {
            return (Objects.equals(getFrom(), from) && (Objects.equals(getTo(), to)));
        }
    }

    public class DirectedEventVertex extends EventVertex {
        private DirectedEventVertex(T id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge<T>> traverseEdges() {
            return outEdges();
        }
    }

    public DirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(new DirectedGraphCreated.Builder<T>().build());
    }

    public DirectedEventGraph(T id, EventBus bus) {
        super(id, bus);
        getBus().post(new DirectedGraphCreated.Builder<T>().graphId(id).build());
    }

    @Override
    Vertex<T> newVertex(T id, Map<String, Object> inherited) {
        return new DirectedEventVertex(id, inherited);
    }

    @Override
    Edge<T> newEdge(T from, T to, Map<String, Object> inherited) {
        return new DirectedEventEdge(from, to, inherited);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
