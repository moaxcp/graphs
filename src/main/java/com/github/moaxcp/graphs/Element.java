package com.github.moaxcp.graphs;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class Element implements Map<String, Object> {

    protected final Map<String, Object> attributes;
    
    public Element() {
        attributes = new LinkedHashMap<>();
    }
    
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public int size() {
        return attributes.size();
    }

    @Override
    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return attributes.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return attributes.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return attributes.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return attributes.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return attributes.remove(key);
    }

    @Override
    public void putAll(Map m) {
        attributes.putAll(m);
    }

    @Override
    public void clear() {
        attributes.clear();
    }

    @Override
    public Set<String> keySet() {
        return attributes.keySet();
    }

    @Override
    public Collection<Object> values() {
        return attributes.values();
    }

    @Override
    public Set<Map.Entry<String, Object>> entrySet() {
        return attributes.entrySet();
    }

    @Override
    public String toString() {
        return attributes.toString();
    }
}
