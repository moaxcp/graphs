package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.events.Builders.*;
import java.util.*;
import org.greenrobot.eventbus.EventBus;

public class UndirectedEventGraph extends AbstractEventGraph {
    public class UndirectedEventEdge extends EventEdge {
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

    public class UndirectedEventVertex extends EventVertex {
        protected UndirectedEventVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge> traverseEdges() {
            return adjacentEdges();
        }
    }

    public UndirectedEventGraph() {
        getBus().post(undirectedGraphCreated().build());
    }

    public UndirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(undirectedGraphCreated().build());
    }

    public UndirectedEventGraph(Object id) {
        super(id);
        getBus().post(undirectedGraphCreated().graphId(id).build());
    }

    public UndirectedEventGraph(Object id, EventBus bus) {
        super(id, bus);
        getBus().post(undirectedGraphCreated().graphId(id).build());
    }

    @Override
    Vertex newVertex(Object id, Map<String, Object> inherited) {
        return new UndirectedEventVertex(id, inherited);
    }

    @Override
    Edge newEdge(Object from, Object to, Map<String, Object> inherited) {
        return new UndirectedEventEdge(from, to, inherited);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
