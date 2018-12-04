package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

public abstract class VertexPropertyEvent extends PropertyEvent {
    private final Object vertexId;

    VertexPropertyEvent(Builder builder) {
        super(builder);
        vertexId = requireNonNull(builder.vertexId);
    }

    public Object getVertexId() {
        return vertexId;
    }

    public abstract static class Builder<T> extends PropertyEvent.Builder<Builder<T>> {
        private Object vertexId;

        public Builder vertexId(Object vertexId) {
            this.vertexId = vertexId;
            return this;
        }
    }
}
