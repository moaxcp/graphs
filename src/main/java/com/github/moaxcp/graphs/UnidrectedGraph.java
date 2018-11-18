package com.github.moaxcp.graphs;

import java.util.Map;

public class UnidrectedGraph extends AbstractSimpleGraph {
    public class UndirectedEdge extends AbstractEdge {
        protected UndirectedEdge(Object from, Object to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }
    }

    public class UndirectedVertex extends AbstractVertex {
        private UndirectedVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }
    }

    Edge newEdge(Object from, Object to, Map<String, Object> inherited) {
        return new UndirectedEdge(from, to, inherited);
    }

    Vertex newVertex(Object id, Map<String, Object> inherited) {
        return new UndirectedVertex(id, inherited);
    }
}
