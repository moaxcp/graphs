package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

import static java.util.Collections.*;

@ToString
public final class VertexRemoved<K> extends VertexEvent<K> {

    private final Map<String, Object> properties;

    private VertexRemoved(Builder<K> builder) {
        super(builder);
        if(builder.properties == null) {
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

        VertexRemoved<?> that = (VertexRemoved<?>) o;
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
        public VertexRemoved<K> build() {
            return new VertexRemoved<>(this);
        }
    }
}
