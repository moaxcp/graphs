package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public abstract class VertexEvent<K> extends GraphEvent<K> {
    private final K vertexId;

    VertexEvent(Builder<K, ? extends Builder> builder) {
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
        VertexEvent vertexPropertyAdded = (VertexEvent) o;
        return Objects.equals(getGraphId(), vertexPropertyAdded.getGraphId()) &&
                Objects.equals(getVertexId(), vertexPropertyAdded.getVertexId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getVertexId());
    }

    @SuppressWarnings("squid:S2176")
    public abstract static class Builder<K, S extends Builder<K, S>> extends GraphEvent.Builder<K, S> {
        private K vertexId;

        public S vertexId(K vertexId) {
            this.vertexId = vertexId;
            return self();
        }
    }
}
