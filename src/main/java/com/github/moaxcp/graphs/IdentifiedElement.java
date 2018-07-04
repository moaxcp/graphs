package com.github.moaxcp.graphs;

import java.util.Objects;

public abstract class IdentifiedElement<K> extends Element {

    public IdentifiedElement(K id) {
        Objects.requireNonNull(id);
        attributes.put("id", id);
    }
    
    public K getId() {
        return (K) attributes.get("id");
    }
    
    public void setId(K id) {
        attributes.put("id", id);
    }
    
    @Override
    public Object put(String key, Object value) {
        if(key.equals("id")) {
            var id = getId();
            setId((K) value);
            return id;
        }
        return super.put(key, value);
    }
}
