package com.github.moaxcp.graphs.element;

import org.greenrobot.eventbus.EventBus;

import java.util.Optional;

public abstract class OptionallyIdentifiedElement<T extends OptionallyIdentifiedElement<T>> extends Element<T> {

    protected OptionallyIdentifiedElement(EventBus bus) {
        super(bus);
    }

    public Optional<Object> getId() {
        return getProperty("id");
    }

    public void setId(Object id) {
        if (id == null && !getId().isPresent()) {
            return;
        }
        if (id == null && getId().isPresent()) {
            removeProperty("id");
            return;
        }
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
}
