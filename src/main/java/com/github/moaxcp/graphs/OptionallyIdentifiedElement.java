package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;

import java.util.Objects;

public abstract class OptionallyIdentifiedElement extends Element {

    protected OptionallyIdentifiedElement(EventBus bus) {
        super(bus);
    }

    protected OptionallyIdentifiedElement(String id, EventBus bus) {
        super(bus);
        Objects.requireNonNull(id);
        local.put("id", id);
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
