package com.github.moaxcp.graphs.events;

import java.util.*;

import static java.util.Objects.*;

public abstract class VertexPropertyEvent<K> extends PropertyEvent<K> {
    private final K vertexId;

    VertexPropertyEvent(Builder<K, ? extends Builder> builder) {
        super(builder);
        vertexId = requireNonNull(builder.vertexId, "vertexId must not be null.");
    }

    public K getVertexId() {
        return vertexId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexPropertyEvent vertexPropertyEvent = (VertexPropertyEvent) o;
        return Objects.equals(getGraphId(), vertexPropertyEvent.getGraphId()) && Objects.equals(getVertexId(), vertexPropertyEvent.getVertexId()) && Objects.equals(getName(), vertexPropertyEvent.getName()) && Objects.equals(getValue(), vertexPropertyEvent.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getVertexId(), getName(), getValue());
    }

    @SuppressWarnings("squid:S2176")
    public abstract static class Builder<K, S extends Builder<K, S>> extends PropertyEvent.Builder<K, S> {
        private K vertexId;

        public S vertexId(K vertexId) {
            this.vertexId = vertexId;
            return self();
        }
    }
}
