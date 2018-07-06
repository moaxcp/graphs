package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class EdgeAttributeUpdated extends EdgeAttributeEvent {
    private Object oldValue;

    public EdgeAttributeUpdated withGraph(Graph graph) {
        super.withGraph(graph);
        return this;
    }

    public EdgeAttributeUpdated withEdge(Graph.Edge edge) {
        super.withEdge(edge);
        return this;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public EdgeAttributeUpdated withOldValue(Object oldValue) {
        this.oldValue = oldValue;
        return this;
    }
}
