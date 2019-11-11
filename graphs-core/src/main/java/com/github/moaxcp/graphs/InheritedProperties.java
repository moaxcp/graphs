package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;

import static java.util.Collections.*;
import static java.util.Optional.*;

/**
 * Implements local properties which are overriden by inherited properties.
 */
class InheritedProperties extends LocalProperties {
  private final Map<String, Object> inherited;

  InheritedProperties(@NonNull Map<String, Object> local, @NonNull Map<String, Object> inherited) {
    super(local);
    this.inherited = unmodifiableMap(inherited);
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
   * Returns the value of a property. If the property is local its value is returned before checking for an
   * inherited property.
   *
   * @param name of property to return
   * @return value mapped to name
   * @throws NullPointerException if name is null
   */
  public final Optional<Object> getProperty(@NonNull String name) {
    Optional<Object> optional = super.getProperty(name);
    if (optional.isPresent()) {
      return optional;
    }
    return ofNullable(inherited.get(name));
  }
}
