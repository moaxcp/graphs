package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.events.Builders.undirectedGraphCreated;
import java.util.*;
import org.greenrobot.eventbus.EventBus;

public class UndirectedEventGraph extends AbstractSimpleGraph {
    public class UndirectedEventEdge extends AbstractEdge {
        protected UndirectedEventEdge(Object from, Object to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public boolean isDirected() {
            return false;
        }

        @Override
        public boolean equals(Object from, Object to) {
            return (Objects.equals(getFrom(), from) || Objects.equals(getFrom(), to)) && (Objects.equals(getTo(), to) || Objects.equals(getTo(), from));
        }
    }

    public class UndirectedEventVertex extends AbstractVertex {
        protected UndirectedEventVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge> traverseEdges() {
            return adjacentEdges();
        }
    }

    private EventBus bus;

    public UndirectedEventGraph() {
        this(EventBus.getDefault());
    }

    public UndirectedEventGraph(EventBus bus) {
        this.bus = bus;
        bus.post(undirectedGraphCreated().build());
    }

    public UndirectedEventGraph(Object id) {
        super(id);
        bus = EventBus.getDefault();
        bus.post(undirectedGraphCreated().graphId(id).graphId(id).build());
    }

    public UndirectedEventGraph(Object id, EventBus bus) {
        super(id);
        this.bus = bus;
        bus.post(undirectedGraphCreated().graphId(id).build());
    }

    protected EventBus getBus() {
        return bus;
    }

    Edge newEdge(Object from, Object to, Map<String, Object> inherited) {
        return new UndirectedEventEdge(from, to, inherited);
    }

    Vertex newVertex(Object id, Map<String, Object> inherited) {
        return new UndirectedEventVertex(id, inherited);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
