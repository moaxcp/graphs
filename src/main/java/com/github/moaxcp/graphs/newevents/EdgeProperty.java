package com.github.moaxcp.graphs.newevents;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public abstract class EdgeProperty extends Property {
    private Object edgeId;
    private Object from;
    private Object to;

    protected EdgeProperty(Builder<?> builder) {
        super(builder);
        edgeId = builder.edgeId;
        from = requireNonNull(builder.from);
        to = requireNonNull(builder.to);
    }

    public Optional<Object> getEdgeId() {
        return Optional.of(edgeId);
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    public static abstract class Builder<T> extends Property.Builder<Builder<T>> {
        private Object edgeId;
        private Object from;
        private Object to;

        public Builder edgeId(Object edgeId) {
            this.edgeId = edgeId;
            return this;
        }

        public Builder from(Object from) {
            this.from = from;
            return this;
        }

        public Builder to(Object to) {
            this.to = to;
            return this;
        }
    }
}
