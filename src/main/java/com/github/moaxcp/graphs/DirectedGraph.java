package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.EdgeAddedGraphEvent;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.Objects;

public class DirectedGraph extends Graph {
    public class DirectedEdge extends Edge {
        protected DirectedEdge(Object from, Object to, Map<String, Object> inherited, EventBus bus) {
            super(from, to, inherited, bus);
        }

        @Override
        protected DirectedEdge self() {
            return this;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DirectedEdge)) {
                return false;
            }
            Edge edge = (Edge) obj;
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
