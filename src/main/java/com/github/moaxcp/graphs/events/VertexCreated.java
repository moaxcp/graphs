package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class VertexCreated extends GraphEvent {

    private final Object vertexId;

    protected VertexCreated(Builder builder) {
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
        VertexCreated that = (VertexCreated) o;
        return Objects.equals(getGraphId(), that.getGraphId()) && Objects.equals(vertexId, that.vertexId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), vertexId);
    }

    public static class Builder extends GraphEvent.Builder<Builder> {
        private Object vertexId;

        public Builder vertexId(Object vertexId) {
            this.vertexId = vertexId;
            return this;
        }

        @Override
        public VertexCreated build() {
            return new VertexCreated(this);
        }
    }
}