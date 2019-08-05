package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class VertexPropertiesEvent<K> extends PropertiesEvent<K> {

    private final K vertexId;

    private VertexPropertiesEvent(Builder<K> builder) {
        super(builder);
        vertexId = builder.vertexId;
    }

    public K getVertexId() {
        return vertexId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexPropertiesEvent<?> that = (VertexPropertiesEvent<?>) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
            Objects.equals(getVertexId(), that.getVertexId()) &&
            Objects.equals(getAddedProperties(), that.getAddedProperties()) &&
            Objects.equals(getUpdatedProperties(), that.getUpdatedProperties());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getVertexId(), getAddedProperties(), getUpdatedProperties());
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends PropertiesEvent.Builder<K, Builder<K>> {
        private K vertexId;

        public Builder<K> vertexId(K vertexId) {
            this.vertexId = vertexId;
            return this;
        }

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public VertexPropertiesEvent<K> build() {
            buildProperties();
            return new VertexPropertiesEvent<>(this);
        }
    }
}
