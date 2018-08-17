package com.github.moaxcp.graphs.event;

import java.util.Objects;

public abstract class GraphPropertyGraphEvent extends BaseGraphEvent implements PropertyGraphEvent {
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

    @Override
    public void check() {
        super.check();
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
    }
}
