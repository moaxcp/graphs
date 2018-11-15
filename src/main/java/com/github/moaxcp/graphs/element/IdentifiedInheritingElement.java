package com.github.moaxcp.graphs.element;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.Objects;

public abstract class IdentifiedInheritingElement<T extends IdentifiedInheritingElement<T>> extends InheritingElement<T> {

    protected IdentifiedInheritingElement(Object id, Map<String, Object> inherited, EventBus bus) {
        super(inherited, bus);
        Objects.requireNonNull(id, "id must not be null.");
        super.setProperty("id", id);
    }

    public Object getId() {
        return getProperty("id").get();
    }

    public void setId(Object id) {
        Objects.requireNonNull(id, "id must not be null.");
        super.setProperty("id", id);
    }

    public T id(Object id) {
        setId(id);
        return self();
    }

    @Override
    public void setProperty(String name, Object value) {
        if ("id".equals(name)) {
            setId(value);
            return;
        }
        super.setProperty(name, value);
    }

    @Override
    public T removeProperty(String name) {
        if ("id".equals(name)) {
            throw new IllegalArgumentException("id can not be removed.");
        }
        return super.removeProperty(name);
    }
}
