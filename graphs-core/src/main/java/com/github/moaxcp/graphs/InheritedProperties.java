package com.github.moaxcp.graphs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

/**
 * Implements local properties for an element along with inherited properties.
 */
class InheritedProperties {
    private static final String NAME_MUST_NOT_BE_NULL = "name must not be null.";
    private static final String VALUE_MUST_NOT_BE_NULL = "value must not be null.";
    private static final String NAME_MUST_NOT_BE_EMPTY = "name must not be empty.";
    private final Map<String, Object> inherited;
    private final Map<String, Object> local = new LinkedHashMap<>();

    InheritedProperties(Map<String, Object> local, Map<String, Object> inherited) {
        this.local.putAll(local);
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

    private void checkEntry(String name, Object value) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requireNonNull(value, VALUE_MUST_NOT_BE_NULL);
        if(name.isEmpty()) {
            throw new IllegalArgumentException(NAME_MUST_NOT_BE_EMPTY);
        }
    }

    /**
     * Adds a local property to this element returning it.
     * @param name of property
     * @param value of property
     * @return this element
     */
    public final InheritedProperties property(String name, Object value) {
        checkEntry(name, value);
        local.put(name, value);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        local.put(name1, value1);
        local.put(name2, value2);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2, String name3, Object value3) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        checkEntry(name3, value3);
        local.put(name1, value1);
        local.put(name2, value2);
        local.put(name3, value3);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        checkEntry(name3, value3);
        checkEntry(name4, value4);
        local.put(name1, value1);
        local.put(name2, value2);
        local.put(name3, value3);
        local.put(name4, value4);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        checkEntry(name3, value3);
        checkEntry(name4, value4);
        checkEntry(name5, value5);
        local.put(name1, value1);
        local.put(name2, value2);
        local.put(name3, value3);
        local.put(name4, value4);
        local.put(name5, value5);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        checkEntry(name3, value3);
        checkEntry(name4, value4);
        checkEntry(name5, value5);
        checkEntry(name6, value6);
        local.put(name1, value1);
        local.put(name2, value2);
        local.put(name3, value3);
        local.put(name4, value4);
        local.put(name5, value5);
        local.put(name6, value6);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        checkEntry(name3, value3);
        checkEntry(name4, value4);
        checkEntry(name5, value5);
        checkEntry(name6, value6);
        checkEntry(name7, value7);
        local.put(name1, value1);
        local.put(name2, value2);
        local.put(name3, value3);
        local.put(name4, value4);
        local.put(name5, value5);
        local.put(name6, value6);
        local.put(name7, value7);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        checkEntry(name3, value3);
        checkEntry(name4, value4);
        checkEntry(name5, value5);
        checkEntry(name6, value6);
        checkEntry(name7, value7);
        checkEntry(name8, value8);
        local.put(name1, value1);
        local.put(name2, value2);
        local.put(name3, value3);
        local.put(name4, value4);
        local.put(name5, value5);
        local.put(name6, value6);
        local.put(name7, value7);
        local.put(name8, value8);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        checkEntry(name3, value3);
        checkEntry(name4, value4);
        checkEntry(name5, value5);
        checkEntry(name6, value6);
        checkEntry(name7, value7);
        checkEntry(name8, value8);
        checkEntry(name9, value9);
        local.put(name1, value1);
        local.put(name2, value2);
        local.put(name3, value3);
        local.put(name4, value4);
        local.put(name5, value5);
        local.put(name6, value6);
        local.put(name7, value7);
        local.put(name8, value8);
        local.put(name9, value9);
        return this;
    }

    public final InheritedProperties property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
        checkEntry(name1, value1);
        checkEntry(name2, value2);
        checkEntry(name3, value3);
        checkEntry(name4, value4);
        checkEntry(name5, value5);
        checkEntry(name6, value6);
        checkEntry(name7, value7);
        checkEntry(name8, value8);
        checkEntry(name9, value9);
        checkEntry(name10, value10);
        local.put(name1, value1);
        local.put(name2, value2);
        local.put(name3, value3);
        local.put(name4, value4);
        local.put(name5, value5);
        local.put(name6, value6);
        local.put(name7, value7);
        local.put(name8, value8);
        local.put(name9, value9);
        local.put(name10, value10);
        return this;
    }

    public final InheritedProperties property(Map<String, Object> properties) {
        for(var entry : properties.entrySet()) {
            checkEntry(entry.getKey(), entry.getValue());
        }
        local.putAll(properties);
        return this;
    }

    /**
     * Removes local property
     * @param name of property
     * @return this element
     */
    public InheritedProperties removeProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!local.containsKey(name)) {
            throw new IllegalArgumentException("element does not contain property named '" + name + "'.");
        }
        local.remove(name);
        return this;
    }
}
