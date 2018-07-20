package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.PropertyAddedGraphEvent;
import com.github.moaxcp.graphs.event.PropertyRemovedGraphEvent;
import com.github.moaxcp.graphs.event.PropertyUpdatedGraphEvent;
import de.muspellheim.eventbus.EventBus;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Element {
    protected EventBus bus;
    protected Map<String, Object> local;

    protected Element(EventBus bus) {
        this.bus = bus;
        local = new LinkedHashMap<>();
    }

    protected EventBus getBus() {
        return bus;
    }

    public Map<String, Object> getLocal() {
        return Collections.unmodifiableMap(local);
    }

    public Object getProperty(String name) {
        Objects.requireNonNull(name);
        return local.get(name);
    }

    public void setProperty(String name, Object value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
        if(name.isEmpty()) {
            throw new IllegalArgumentException("name must not be empty.");
        }

        if(local.containsKey(name)) {
            Object old = local.put(name, value);
            notifyUpdateProperty(name, value, old);
        }
        local.put(name, value);
        notifyAddProperty(name, value);
    }

    public abstract Element withProperty(String name, Object value);

    public void removeProperty(String name) {
        Objects.requireNonNull(name);
        if(!local.containsKey(name)) {
            throw new IllegalArgumentException("element does not conatain property named " + name);
        }
        Object remove = local.remove(name);
        notifyRemoveProperty(name, remove);
    }

    protected void notifyAddProperty(String name, Object value) {
        bus.publish(propertyAddedEvent(name, value));
    }
    protected void notifyRemoveProperty(String name, Object value) {
        bus.publish(propertyRemovedEvent(name, value));
    }
    protected void notifyUpdateProperty(String name, Object value, Object oldValue) {
        bus.publish(propertyUpdatedEvent(name, value, oldValue));
    }

    protected abstract PropertyAddedGraphEvent propertyAddedEvent(String name, Object value);
    protected abstract PropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value);
    protected abstract PropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue);
}
