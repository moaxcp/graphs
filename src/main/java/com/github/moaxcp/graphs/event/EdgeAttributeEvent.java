package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public abstract class EdgeAttributeEvent extends EdgeEvent {
    private String attributeKey;
    private Object attributeValue;

    public EdgeAttributeEvent withGraph(Graph graph) {
        super.withGraph(graph);
        return this;
    }

    public EdgeAttributeEvent withEdge(Graph.Edge edge) {
        super.withEdge(edge);
        return this;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public EdgeAttributeEvent withAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
        return this;
    }

    public Object getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(Object attributeValue) {
        this.attributeValue = attributeValue;
    }

    public EdgeAttributeEvent withAttributeValue(Object attributeValue) {
        this.attributeValue = attributeValue;
        return this;
    }
}
