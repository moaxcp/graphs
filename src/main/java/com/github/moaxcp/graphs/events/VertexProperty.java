package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

public abstract class VertexProperty extends Property {
    private final Object vertexId;

    VertexProperty(Builder builder) {
        super(builder);
        vertexId = requireNonNull(builder.vertexId);
    }

    public Object getVertexId() {
        return vertexId;
    }

    public abstract static class Builder<T> extends Property.Builder<Builder<T>> {
        private Object vertexId;

        public Builder vertexId(Object vertexId) {
            this.vertexId = vertexId;
            return this;
        }
    }
}
