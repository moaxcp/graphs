package com.github.moaxcp.graphs.greenrobot;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.Objects;

public class DirectedGraph extends UndirectedGraph {
    public DirectedGraph() {
        super();
    }

    public DirectedGraph(EventBus bus) {
        super(bus);
    }

    public DirectedGraph(Object id) {
        super(id);
    }

    public DirectedGraph(Object id, EventBus bus) {
        super(id, bus);
    }

    public class DirectedEdge extends UndirectedEdge {
        protected DirectedEdge(Object from, Object to, Map<String, Object> inherited, EventBus bus) {
            super(from, to, inherited, bus);
        }

        @Override
        protected DirectedEdge self() {
            return this;
        }

        @Override
        public boolean equals(Object from, Object to) {
            return (Objects.equals(getFrom(), from) && (Objects.equals(getTo(), to)));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DirectedEdge)) {
                return false;
            }
            UndirectedEdge edge = (UndirectedEdge) obj;
            return (Objects.equals(getFrom(), edge.getFrom()) && (Objects.equals(getTo(), edge.getTo())));
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFrom(), getTo());
        }
    }

    @Override
    DirectedEdge newEdge(Object from, Object to) {
        return new DirectedEdge(from, to, edgeProperties, getBus());
    }
}
