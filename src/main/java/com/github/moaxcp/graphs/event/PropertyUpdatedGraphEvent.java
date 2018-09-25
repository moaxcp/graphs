package com.github.moaxcp.graphs.event;

public interface PropertyUpdatedGraphEvent extends PropertyGraphEvent {
    Object getOldValue();

    void setOldValue(Object oldValue);

    PropertyUpdatedGraphEvent withOldValue(Object oldValue);
}
