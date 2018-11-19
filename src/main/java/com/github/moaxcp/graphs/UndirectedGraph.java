package com.github.moaxcp.graphs;

import java.util.Map;

public class UndirectedGraph extends AbstractSimpleGraph {
    public class UndirectedEdge extends AbstractEdge {
        protected UndirectedEdge(Object from, Object to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public boolean isDirected() {
            return false;
        }
    }

    public class UndirectedVertex extends AbstractVertex {
        private UndirectedVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }
    }

    public UndirectedGraph() {

    }

    public UndirectedGraph(Object id) {
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
