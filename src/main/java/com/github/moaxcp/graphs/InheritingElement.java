package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;

import java.util.Collections;
import java.util.Map;

public abstract class InheritingElement extends Element {
    protected Map<String, Object> inherited;

    public InheritingElement(Map<String, Object> inherited, EventBus bus) {
        super(bus);
        this.inherited = Collections.unmodifiableMap(inherited);
    }

    public Map<String, Object> inherited() {
        return Collections.unmodifiableMap(inherited);
    }

    @Override
    public Object getProperty(String name) {
        Object value = super.getProperty(name);
        if(value != null) {
            return value;
        }
        return inherited.get(name);
    }
}
