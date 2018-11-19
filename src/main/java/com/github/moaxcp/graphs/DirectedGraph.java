package com.github.moaxcp.graphs;

import java.util.Map;

public class DirectedGraph extends AbstractSimpleGraph {
    public class DirectedEdge extends AbstractEdge {
        protected DirectedEdge(Object from, Object to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }
    }

    public class DirectedVertex extends AbstractVertex {
        private DirectedVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }
    }

    Edge newEdge(Object from, Object to, Map<String, Object> inherited) {
        return new DirectedEdge(from, to, inherited);
    }

    Vertex newVertex(Object id, Map<String, Object> inherited) {
        return new DirectedVertex(id, inherited);
    }
}
