package com.github.moaxcp.graphs;

import java.util.Objects;

public abstract class IdentifiedElement extends Element {

    public IdentifiedElement(Object id) {
        Objects.requireNonNull(id);
        attributes.put("id", id);
    }
    
    public Object getId() {
        return attributes.get("id");
    }
    
    public void setId(Object id) {
        attributes.put("id", id);
    }
    
    @Override
    public Object put(String key, Object value) {
        if(key.equals("id")) {
            var id = getId();
            setId(value);
            return id;
        }
        return super.put(key, value);
    }
}
