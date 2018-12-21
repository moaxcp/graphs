package com.github.moaxcp.graphs;

import java.util.*;

public class DirectedGraph<T> extends AbstractSimpleGraph<T> {
    public class DirectedEdge extends AbstractEdge {
        protected DirectedEdge(T from, T to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public boolean isDirected() {
            return true;
        }
    }

    public class DirectedVertex extends AbstractVertex {
        protected DirectedVertex(T id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge<T>> traverseEdges() {
            return outEdges();
        }
    }

    public DirectedGraph() {

    }

    public DirectedGraph(T id) {
        super(id);
    }

    Edge<T> newEdge(T from, T to, Map<String, Object> inherited) {
        return new DirectedEdge(from, to, inherited);
    }

    @Override
    EdgeKey<T> newEdgeKey(T from, T to) {
        return new DirectedEdgeKey<>(from, to);
    }

    Vertex<T> newVertex(T id, Map<String, Object> inherited) {
        return new DirectedVertex(id, inherited);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
