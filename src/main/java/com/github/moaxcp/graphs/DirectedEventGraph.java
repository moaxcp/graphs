package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.events.Builders.*;
import java.util.*;
import org.greenrobot.eventbus.EventBus;

public class DirectedEventGraph extends AbstractSimpleGraph {
    public class DirectedEventEdge extends AbstractEdge {
        protected DirectedEventEdge(Object from, Object to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public boolean isDirected() {
            return true;
        }

        @Override
        public boolean equals(Object from, Object to) {
            return (Objects.equals(getFrom(), from) && (Objects.equals(getTo(), to)));
        }
    }

    public class DirectedEventVertex extends AbstractVertex {
        private DirectedEventVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge> traverseEdges() {
            return outEdges();
        }
    }

    private EventBus bus;

    public DirectedEventGraph() {
        this(EventBus.getDefault());
    }

    public DirectedEventGraph(EventBus bus) {
        this.bus = bus;
        bus.post(directedGraphCreated().build());
    }

    public DirectedEventGraph(Object id) {
        super(id);
        bus = EventBus.getDefault();
        bus.post(directedGraphCreated().graphId(id).build());
    }

    public DirectedEventGraph(Object id, EventBus bus) {
        super(id);
        this.bus = bus;
        bus.post(directedGraphCreated().graphId(id).build());
    }

    protected EventBus getBus() {
        return bus;
    }

    @Override
    Edge newEdge(Object from, Object to, Map<String, Object> inherited) {
        return new DirectedEventEdge(from, to, inherited);
    }

    @Override
    Edge addEdge(Object from, Object to) {
        var edge = super.addEdge(from, to);
        bus.post(edgeCreated().graphId(getId().orElse(null)).from(from).to(to).build());
        return edge;
    }

    @Override
    Vertex newVertex(Object id, Map<String, Object> inherited) {
        return new DirectedEventVertex(id, inherited);
    }

    @Override
    Vertex addVertex(Object id) {
        var vertex = super.addVertex(id);
        bus.post(vertexCreated().graphId(DirectedEventGraph.this.getId().orElse(null)).vertexId(id).build());
        return vertex;
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
