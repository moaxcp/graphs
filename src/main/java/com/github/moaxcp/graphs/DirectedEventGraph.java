package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.events.Builders.directedGraphCreated;
import java.util.*;
import org.greenrobot.eventbus.EventBus;

public class DirectedEventGraph extends AbstractEventGraph {
    public class DirectedEventEdge extends EventEdge {
        private DirectedEventEdge(Object from, Object to, Map<String, Object> inherited) {
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

    public class DirectedEventVertex extends EventVertex {
        private DirectedEventVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge> traverseEdges() {
            return outEdges();
        }
    }

    public DirectedEventGraph(EventBus bus) {
        super(bus);
        getBus().post(directedGraphCreated().build());
    }

    public DirectedEventGraph(Object id, EventBus bus) {
        super(id, bus);
        getBus().post(directedGraphCreated().graphId(id).build());
    }

    @Override
    Vertex newVertex(Object id, Map<String, Object> inherited) {
        return new DirectedEventVertex(id, inherited);
    }

    @Override
    Edge newEdge(Object from, Object to, Map<String, Object> inherited) {
        return new DirectedEventEdge(from, to, inherited);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
