package com.github.moaxcp.graphs;

import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import java.util.*;

/**
 * Implements local properties for an element along with inherited properties.
 * @param <SELF> type of subclass
 */
class InheritingElement<SELF> {
    private static final String NAME_MUST_NOT_BE_NULL = "name must not be null.";
    private static final String VALUE_MUST_NOT_BE_NULL = "value must not be null.";
    private static final String NAME_MUST_NOT_BE_EMPTY = "name must not be empty.";
    private final Map<String, Object> inherited;
    private final Map<String, Object> local = new LinkedHashMap<>();

    InheritingElement(Map<String, Object> inherited) {
        this.inherited = unmodifiableMap(requireNonNull(inherited, "inherited must not be null."));
    }

    /**
     * Returns inherited properties as an unmodifiableMap.
     * @return inherited properties
     */
    public final Map<String, Object> inherited() {
        return inherited;
    }

    /**
     * Returns this object as a subclass type
     * @return this object as a subclass type
     */
    @SuppressWarnings("unchecked")
    final SELF self() {
        return (SELF) this;
    }

    /**
     * Returns an unmodifiable {@link Map} of all properties set on this Element.
     * @return all properties set on this element
     */
    public final Map<String, Object> local() {
        return unmodifiableMap(local);
    }

    /**
     * Returns the value of a property. If the property is local its value is returned before checking for an
     * inherited property.
     * @param name  of property to return
     * @return value mapped to name
     * @throws NullPointerException if name is null
     */
    public final Optional<Object> getProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        Object value = local.get(name);
        if (value != null) {
            return Optional.of(value);
        }
        return ofNullable(inherited.get(name));
    }

    /**
     * Adds a local property to this element.
     * @param name of property
     * @param value of property
     */
    public void setProperty(String name, Object value) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requireNonNull(value, VALUE_MUST_NOT_BE_NULL);
        if(name.isEmpty()) {
            throw new IllegalArgumentException(NAME_MUST_NOT_BE_EMPTY);
        }
        local.put(name, value);
    }

    /**
     * Adds a local property to this element returning it.
     * @param name of property
     * @param value of property
     * @return this element
     */
    public final SELF property(String name, Object value) {
        setProperty(name, value);
        return self();
    }

    /**
     * Removes local property
     * @param name of property
     * @return this element
     */
    public SELF removeProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!local.containsKey(name)) {
            throw new IllegalArgumentException("element does not contain property named '" + name + "'.");
        }
        local.remove(name);
        return self();
    }
}
