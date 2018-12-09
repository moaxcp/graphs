package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public abstract class VertexEvent extends GraphEvent {
    private final Object vertexId;

    VertexEvent(Builder builder) {
        super(builder);
        vertexId = requireNonNull(builder.vertexId, "vertexId must not be null.");
    }

    public Object getVertexId() {
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
    public abstract static class Builder<T extends Builder> extends GraphEvent.Builder<T> {
        private Object vertexId;

        public T vertexId(Object vertexId) {
            this.vertexId = vertexId;
            return self();
        }
    }
}
