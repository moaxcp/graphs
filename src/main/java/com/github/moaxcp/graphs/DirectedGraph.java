package com.github.moaxcp.graphs;

import java.util.*;

public class DirectedGraph extends AbstractSimpleGraph {
    public class DirectedEdge extends AbstractEdge {
        protected DirectedEdge(Object from, Object to, Map<String, Object> inherited) {
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

        @Override
        public int hashCode() {
            return Objects.hash(getFrom(), getTo());
        }
    }

    public class DirectedVertex extends AbstractVertex {
        private DirectedVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge> traverseEdges() {
            return outEdges();
        }
    }

    public DirectedGraph() {

    }

    public DirectedGraph(Object id) {
        super(id);
    }

    Edge newEdge(Object from, Object to, Map<String, Object> inherited) {
        return new DirectedEdge(from, to, inherited);
    }

    Vertex newVertex(Object id, Map<String, Object> inherited) {
        return new DirectedVertex(id, inherited);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
