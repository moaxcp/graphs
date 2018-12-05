package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public abstract class VertexPropertyEvent extends PropertyEvent {
    private final Object vertexId;

    VertexPropertyEvent(Builder builder) {
        super(builder);
        vertexId = requireNonNull(builder.vertexId);
    }

    public Object getVertexId() {
        return vertexId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexPropertyEvent vertexPropertyAdded = (VertexPropertyEvent) o;
        return Objects.equals(getGraphId(), vertexPropertyAdded.getGraphId()) && Objects.equals(getVertexId(), vertexPropertyAdded.getVertexId()) && Objects.equals(getName(), vertexPropertyAdded.getName()) && Objects.equals(getValue(), vertexPropertyAdded.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getVertexId(), getName(), getValue());
    }

    public abstract static class Builder<T extends Builder> extends PropertyEvent.Builder<T> {
        private Object vertexId;

        public T vertexId(Object vertexId) {
            this.vertexId = vertexId;
            return self();
        }
    }
}
