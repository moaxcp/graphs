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

    public final Optional<Object> getEdgeId() {
        return Optional.ofNullable(edgeId);
    }

    public final Object getFrom() {
        return from;
    }

    public final Object getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgePropertyEvent that = (EdgePropertyEvent) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getEdgeId(), that.getEdgeId()) &&
                Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getEdgeId(), getFrom(), getTo(), getName(), getValue());
    }

    public static abstract class Builder<T extends Builder> extends PropertyEvent.Builder<T> {
        private Object edgeId;
        private Object from;
        private Object to;

        public final T edgeId(Object edgeId) {
            this.edgeId = edgeId;
            return self();
        }

        public final T from(Object from) {
            this.from = from;
            return self();
        }

        public final T to(Object to) {
            this.to = to;
            return self();
        }
    }
}
