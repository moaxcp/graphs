package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;

import static java.util.Collections.*;

/**
 * Implements local properties which is used for a {@link Graph}, {@link Graph.Edge}, or {@link Graph.Vertex}. A
 * property is a name/value pair. The name and/or value cannot be null. Updating a value to null will remove it but it
 * can never be set as null.
 */
@ToString
class LocalProperties {
  private final Map<String, Object> local = new LinkedHashMap<>();

  /**
   * Creates local properties with given map.
   * @param local properties to set
   * @throws NullPointerException if local is null
   */
  LocalProperties(@NonNull Map<String, Object> local) {
    this.local.putAll(local);
  }

  /**
   * Returns an unmodifiable {@link Map} of all local properties.
   *
   * @return all local properties
   */
  final Map<String, Object> local() {
    return unmodifiableMap(local);
  }

  /**
   * Returns the value of a property.
   *
   * @param <T> return type of property
   * @param name of property to return
   * @return value mapped to name
   * @throws NullPointerException if name is null
   */
  <T> Optional<T> findProperty(@NonNull String name) {
    return Optional.ofNullable(getProperty(name));
  }

  /**
   * Returns the value of a property.
   *
   * @param <T> return type of property
   * @param name of property to return
   * @return value mapped to name
   * @throws NullPointerException if name is null
   */
  <T> T getProperty(@NonNull String name) {
    return (T) local.get(name);
  }

  private void checkEntry(@NonNull String name, Object value) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("name must not be empty.");
    }
    if(!local.containsKey(name) && value == null) {
      throw new NullPointerException("value must not be null.");
    }
  }

  final void putProperty(@NonNull String name, Object value) {
    checkEntry(name, value);
    if(value == null) {
      local.remove(name);
    } else {
      local.put(name, value);
    }
  }

  /**
   * Puts all properties in this LocalProperties.
   * @param properties
   * @throws NullPointerException if properties is null. If a key in properties is null. If a local property doesn't
   * exist and the value in properties is null.
   * @throws IllegalArgumentException if a name in properties is empty.
   */
  final void putProperties(@NonNull Map<String, Object> properties) {
    for (var entry : properties.entrySet()) {
      checkEntry(entry.getKey(), entry.getValue());
      if (entry.getValue() == null) {
        local.remove(entry.getKey());
      } else {
        local.put(entry.getKey(), entry.getValue());
      }
    }
  }

  /**
   * Removes a local property
   *
   * @param name of property
   * @throws NullPointerException if name is null
   * @throws IllegalArgumentException if the named property does not exist
   */
  final void removeProperty(@NonNull String name) {
    if (!local.containsKey(name)) {
      throw new IllegalArgumentException("local properties do not contain name '" + name + "'.");
    }
    local.remove(name);
  }
}
