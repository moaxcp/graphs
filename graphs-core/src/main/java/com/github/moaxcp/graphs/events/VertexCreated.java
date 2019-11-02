package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.unmodifiableMap;

@ToString
public final class VertexCreated<K> extends VertexEvent<K> {

  private final Map<String, Object> properties;

  private VertexCreated(Builder<K> builder) {
    super(builder);
    if (builder.properties == null) {
      properties = new LinkedHashMap<>();
    } else {
      this.properties = builder.properties;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    VertexCreated<?> that = (VertexCreated<?>) o;
    return Objects.equals(getGraphId(), that.getGraphId()) &&
      Objects.equals(getVertexId(), that.getVertexId()) &&
      Objects.equals(properties, that.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getGraphId(), getVertexId(), properties);
  }

  public Map<String, Object> getProperties() {
    return unmodifiableMap(properties);
  }

  @SuppressWarnings("squid:S2176")
  public static final class Builder<K> extends VertexEvent.Builder<K, Builder<K>> {

    private Map<String, Object> properties;

    public Builder<K> properties(Map<String, Object> properties) {
      this.properties = new LinkedHashMap<>(properties);
      return this;
    }

    @Override
    public Builder<K> self() {
      return this;
    }

    @Override
    public VertexCreated<K> build() {
      return new VertexCreated<>(this);
    }
  }
}
