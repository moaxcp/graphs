package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.PropertyAddedGraphEvent;
import com.github.moaxcp.graphs.event.PropertyRemovedGraphEvent;
import com.github.moaxcp.graphs.event.PropertyUpdatedGraphEvent;
import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Element provides easy access to the EventBus and properties to child objects.
 */
public abstract class Element {
    EventBus bus;
    Map<String, Object> local;

    Element(EventBus bus) {
        this.bus = bus;
        local = new LinkedHashMap<>();
    }

    EventBus getBus() {
        return bus;
    }

    /**
     * Returns an unmodifiable {@link Map} of all properties set on this Element.
     * @return all properties set on this element
     */
    public Map<String, Object> getLocal() {
        return Collections.unmodifiableMap(local);
    }

    /**
     * Returns the value mapped to name.
     * @param name  of property to return
     * @return value mapped to name
     * @throws NullPointerException if name is null
     */
    public Object getProperty(String name) {
        Objects.requireNonNull(name, "name must not be null.");
        return local.get(name);
    }

    /**
     * Maps name to value
     * @param name
     * @param value
     */
    public void setProperty(String name, Object value) {
        Objects.requireNonNull(name, "name must not be null.");
        Objects.requireNonNull(value, "value must not be null.");
        if(name.isEmpty()) {
            throw new IllegalArgumentException("name must not be empty.");
        }

        if(local.containsKey(name)) {
            Object old = local.put(name, value);
            notifyUpdateProperty(name, value, old);
            return;
        }
        local.put(name, value);
        notifyAddProperty(name, value);
    }

    public void addProperties(Map<String, Object> properties) {
        for(Map.Entry<String, Object> entry : properties.entrySet()) {
            setProperty(entry.getKey(), entry.getValue());
        }
    }

    public abstract Element withProperty(String name, Object value);

    public void removeProperty(String name) {
        Objects.requireNonNull(name);
        if(!local.containsKey(name)) {
            throw new IllegalArgumentException("element does not contain property named '" + name + "'.");
        }
        Object remove = local.remove(name);
        notifyRemoveProperty(name, remove);
    }

    void notifyAddProperty(String name, Object value) {
        bus.post(propertyAddedEvent(name, value));
    }
    void notifyRemoveProperty(String name, Object value) {
        bus.post(propertyRemovedEvent(name, value));
    }
    void notifyUpdateProperty(String name, Object value, Object oldValue) {
        bus.post(propertyUpdatedEvent(name, value, oldValue));
    }

    abstract PropertyAddedGraphEvent propertyAddedEvent(String name, Object value);
    abstract PropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value);
    abstract PropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue);
}
