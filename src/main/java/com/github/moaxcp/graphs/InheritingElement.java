package com.github.moaxcp.graphs;

import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import java.util.*;

abstract class InheritingElement<S> {
    private static final String NAME_MUST_NOT_BE_NULL = "name must not be null.";
    private static final String VALUE_MUST_NOT_BE_NULL = "value must not be null.";
    private static final String NAME_MUST_NOT_BE_EMPTY = "name must not be empty.";
    private Map<String, Object> inherited;
    private Map<String, Object> local = new LinkedHashMap<>();

    InheritingElement(Map<String, Object> inherited) {
        this.inherited = unmodifiableMap(requireNonNull(inherited, "inherited must not be null."));
    }

    public Map<String, Object> inherited() {
        return inherited;
    }

    @SuppressWarnings("unchecked")
    S self() {
        return (S) this;
    }

    /**
     * Returns an unmodifiable {@link Map} of all properties set on this Element.
     * @return all properties set on this element
     */
    public Map<String, Object> local() {
        return unmodifiableMap(local);
    }

    /**
     * Returns the value mapped to name.
     * @param name  of property to return
     * @return value mapped to name
     * @throws NullPointerException if name is null
     */
    public Optional<Object> getProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        Object value = local.get(name);
        if (value != null) {
            return Optional.of(value);
        }
        return ofNullable(inherited.get(name));
    }

    /**
     * Maps name to value
     * @param name
     * @param value
     */
    public void setProperty(String name, Object value) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requireNonNull(value, VALUE_MUST_NOT_BE_NULL);
        if(name.isEmpty()) {
            throw new IllegalArgumentException(NAME_MUST_NOT_BE_EMPTY);
        }
        local.put(name, value);
    }

    public S property(String name, Object value) {
        setProperty(name, value);
        return self();
    }

    public S removeProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!local.containsKey(name)) {
            throw new IllegalArgumentException("element does not contain property named '" + name + "'.");
        }
        local.remove(name);
        return self();
    }
}
