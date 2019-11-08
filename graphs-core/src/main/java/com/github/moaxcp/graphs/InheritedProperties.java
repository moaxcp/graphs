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
   *
   * @return inherited properties
   */
  public final Map<String, Object> inherited() {
    return inherited;
  }

  /**
   * Returns an unmodifiable {@link Map} of all properties set on this Element.
   *
   * @return all properties set on this element
   */
  public final Map<String, Object> local() {
    return unmodifiableMap(local);
  }

  /**
   * Returns the value of a property. If the property is local its value is returned before checking for an
   * inherited property.
   *
   * @param name of property to return
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
    if (name.isEmpty()) {
      throw new IllegalArgumentException(NAME_MUST_NOT_BE_EMPTY);
    }
    if(!local.containsKey(name) && value == null) {
        throw new NullPointerException(VALUE_MUST_NOT_BE_NULL);
    }
  }

  public final InheritedProperties property(Map<String, Object> properties) {
    for (var entry : properties.entrySet()) {
      checkEntry(entry.getKey(), entry.getValue());
      if (entry.getValue() == null) {
        local.remove(entry.getKey());
      } else {
        local.put(entry.getKey(), entry.getValue());
      }
    }
    return this;
  }

  /**
   * Removes local property
   *
   * @param name of property
   * @return this element
   */
  public InheritedProperties removeProperty(String name) {
    requireNonNull(name, NAME_MUST_NOT_BE_NULL);
    if (!local.containsKey(name)) {
      throw new IllegalArgumentException("element does not contain property named '" + name + "'.");
    }
    local.remove(name);
    return this;
  }
}
