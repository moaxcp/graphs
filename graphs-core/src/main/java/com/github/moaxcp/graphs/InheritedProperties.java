package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;

/**
 * Implements local properties which are overriden by inherited properties.
 */
@ToString
class InheritedProperties extends LocalProperties {
  private final Map<String, Object> inherited;

  InheritedProperties(@NonNull Map<String, Object> local, @NonNull Map<String, Object> inherited) {
    super(local);
    this.inherited = inherited;
  }

  /**
   * Returns inherited properties as an unmodifiableMap.
   *
   * @return inherited properties
   */
  public final Map<String, Object> inherited() {
    return Collections.unmodifiableMap(inherited);
  }

  /**
   * Returns the value of a property. If the property is local its value is returned before checking for an
   * inherited property.
   *
   * @param <T> return type of property
   * @param name of property to return
   * @return value mapped to name
   * @throws NullPointerException if name is null
   */
  @Override
  public final <T> Optional<T> findProperty(@NonNull String name) {
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
    T value = super.getProperty(name);
    if(value == null) {
      return (T) inherited.get(name);
    }
    return value;
  }
}
