package com.github.moaxcp.graphs;

import java.util.*;

public class UndirectedEventGraph extends AbstractSimpleEventGraph {
    public class UndirectedEdge extends AbstractEdge {
        protected UndirectedEdge(Object from, Object to, Map<String, Object> inherited) {
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

    public class UndirectedVertex extends AbstractVertex {
        private UndirectedVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge> traverseEdges() {
            return adjacentEdges();
        }
    }

    public UndirectedEventGraph() {

    }

    public UndirectedEventGraph(Object id) {
        super(id);
    }

    Edge newEdge(Object from, Object to, Map<String, Object> inherited) {
        return new UndirectedEdge(from, to, inherited);
    }

    Vertex newVertex(Object id, Map<String, Object> inherited) {
        return new UndirectedVertex(id, inherited);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
