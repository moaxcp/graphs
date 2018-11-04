package com.github.moaxcp.graphs.element;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public abstract class InheritingElement<T extends InheritingElement<T>> extends Element<T> {
    protected Map<String, Object> inherited;

    public InheritingElement(Map<String, Object> inherited, EventBus bus) {
        super(bus);
        this.inherited = Collections.unmodifiableMap(inherited);
    }

    public Map<String, Object> inherited() {
        return inherited;
    }

    @Override
    public Optional<Object> getProperty(String name) {
        Optional<Object> value = super.getProperty(name);
        if (value.isPresent()) {
            return value;
        }
        return Optional.ofNullable(inherited.get(name));
    }
}
