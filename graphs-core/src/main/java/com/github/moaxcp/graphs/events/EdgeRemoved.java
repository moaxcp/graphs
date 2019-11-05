package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@ToString
public final class EdgeRemoved<K> extends EdgeOptionalIdEvent<K> {

    private final Map<String, Object> properties;

    private EdgeRemoved(Builder<K> builder) {
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
        EdgeRemoved<?> that = (EdgeRemoved<?>) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
          Objects.equals(getEdgeId(), that.getEdgeId()) &&
          Objects.equals(getFrom(), that.getFrom()) &&
          Objects.equals(getTo(), that.getTo()) &&
          Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getEdgeId(), getFrom(), getTo(), properties);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends EdgeOptionalIdEvent.Builder<K, Builder<K>> {

        private Map<String, Object> properties;

        public EdgeRemoved.Builder<K> properties(Map<String, Object> properties) {
            this.properties = new LinkedHashMap<>(properties);
            return this;
        }

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeRemoved<K> build() {
            return new EdgeRemoved<>(this);
        }
    }
}
