package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.*;

public abstract class EdgePropertyEvent extends PropertyEvent {
    private final Object edgeId;
    private final Object from;
    private final Object to;

    protected EdgePropertyEvent(Builder<?> builder) {
        super(builder);
        edgeId = builder.edgeId;
        from = requireNonNull(builder.from);
        to = requireNonNull(builder.to);
    }

    public Optional<Object> getEdgeId() {
        return Optional.ofNullable(edgeId);
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    public static abstract class Builder<T> extends PropertyEvent.Builder<Builder<T>> {
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
