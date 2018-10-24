package com.github.moaxcp.graphs;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class OptionallyIdentifiedInheritingElement extends InheritingElement {

    protected OptionallyIdentifiedInheritingElement(Map<String, Object> inherited, EventBus bus) {
        super(inherited, bus);
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

    @Override
    public void setProperty(String name, Object value) {
        if ("id".equals(name)) {
            setId(value);
            return;
        }
        super.setProperty(name, value);
    }
}
