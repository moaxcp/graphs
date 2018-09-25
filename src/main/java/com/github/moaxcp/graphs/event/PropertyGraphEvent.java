package com.github.moaxcp.graphs.event;

public interface PropertyGraphEvent extends GraphEvent {
    String getName();

    void setName(String name);

    Object getValue();

    void setValue(Object value);

    PropertyGraphEvent withName(String name);

    PropertyGraphEvent withValue(Object value);
}
