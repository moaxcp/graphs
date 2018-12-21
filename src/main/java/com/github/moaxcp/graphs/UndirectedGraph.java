package com.github.moaxcp.graphs;

import java.util.*;

public class UndirectedGraph<T> extends AbstractSimpleGraph<T> {
    public class UndirectedEdge extends AbstractEdge {
        protected UndirectedEdge(T from, T to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public boolean isDirected() {
            return false;
        }
    }

    public class UndirectedVertex extends AbstractVertex {
        protected UndirectedVertex(T id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public Set<Edge<T>> traverseEdges() {
            return adjacentEdges();
        }
    }

    public UndirectedGraph() {

    }

    public UndirectedGraph(T id) {
        super(id);
    }

    Edge<T> newEdge(T from, T to, Map<String, Object> inherited) {
        return new UndirectedEdge(from, to, inherited);
    }

    @Override
    EdgeKey<T> newEdgeKey(T from, T to) {
        return new UndirectedEdgeKey<>(from, to);
    }

    Vertex<T> newVertex(T id, Map<String, Object> inherited) {
        return new UndirectedVertex(id, inherited);
    }

    @Override
    public boolean isDirected() {
        return false;
    }
}
