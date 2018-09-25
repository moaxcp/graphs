package com.github.moaxcp.graphs;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.Objects;

public abstract class IdentifiedInheritingElement extends InheritingElement {

    protected IdentifiedInheritingElement(String id, Map<String, Object> inherited, EventBus bus) {
        super(inherited, bus);
        Objects.requireNonNull(id, "id must not be null.");
        local.put("id", id);
    }

    public String getId() {
        return (String) getProperty("id");
    }

    public void setId(String id) {
        Objects.requireNonNull(id, "id must not be null.");
        super.setProperty("id", id);
    }

    @Override
    public void setProperty(String name, Object value) {
        if ("id".equals(name)) {
            if (!(value instanceof String)) {
                throw new IllegalArgumentException("id must be set to a String object.");
            }
            setId((String) value);
            return;
        }
        super.setProperty(name, value);
    }

    @Override
    public void removeProperty(String name) {
        if ("id".equals(name)) {
            throw new IllegalArgumentException("id can not be removed.");
        }
        super.removeProperty(name);
    }
}
