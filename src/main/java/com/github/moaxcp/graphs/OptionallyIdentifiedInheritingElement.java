package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;

import java.util.Map;
import java.util.Objects;

public abstract class OptionallyIdentifiedInheritingElement extends InheritingElement {
    protected OptionallyIdentifiedInheritingElement(String id, Map<String, Object> inherited, EventBus bus) {
        this(inherited, bus);
        Objects.requireNonNull(id);
        local.put("id", id);
    }

    protected OptionallyIdentifiedInheritingElement(Map<String, Object> inherited, EventBus bus) {
        super(inherited, bus);
    }

    public String getId() {
        return (String) getProperty("id");
    }

    public void setId(String id) {
        if(id == null && getId() == null) {
            return;
        }
        if(id == null && getId() != null) {
            removeProperty("id");
            return;
        }
        super.setProperty("id", id);
    }

    @Override
    public void setProperty(String name, Object value) {
        if("id".equals(name)) {
            setId((String) value);
            return;
        }
        super.setProperty(name, value);
    }
}
