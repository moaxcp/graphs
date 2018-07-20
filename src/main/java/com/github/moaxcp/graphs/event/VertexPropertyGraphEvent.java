package com.github.moaxcp.graphs.event;

public abstract class VertexPropertyGraphEvent extends BaseVertexGraphEvent implements PropertyGraphEvent {
    private String name;
    private Object value;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}
