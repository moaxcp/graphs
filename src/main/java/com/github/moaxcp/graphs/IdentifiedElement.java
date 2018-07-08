package com.github.moaxcp.graphs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class IdentifiedElement extends Element {

    public IdentifiedElement(String id) {
        Objects.requireNonNull(id);
        attributes.put("id", id);
    }
    
    public String getId() {
        return (String) get("id");
    }
    
    public void setId(String id) {
        attributes.put("id", id);
    }
    
    @Override
    public Object put(String key, Object value) {
        if(key.equals("id")) {
            var id = getId();
            setId((String) value);
            return id;
        }
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        if(m.containsKey("id")) {
            Map<String, Object> removed = new LinkedHashMap<>(m);
            String id = (String) removed.remove("id");
            setId(id);
            attributes.putAll(removed);
            return;
        }
        attributes.putAll(m);
    }

    @Override
    public Object remove(Object key) {
        if("id".equals(key)) {
            throw new IllegalArgumentException("cannot remove id.");
        }
        return super.remove(key);
    }
}
