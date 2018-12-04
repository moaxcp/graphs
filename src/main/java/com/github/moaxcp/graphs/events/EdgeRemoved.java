package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.*;

public final class EdgeRemoved extends GraphEvent {

    private final Object edgeId;
    private final Object from;
    private final Object to;

    private EdgeRemoved(Builder builder) {
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

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeRemoved that = (EdgeRemoved) o;
        return Objects.equals(getGraphId(), that.getGraphId()) && Objects.equals(edgeId, that.edgeId) && Objects.equals(from, that.from) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), edgeId, from, to);
    }

    public static class Builder extends GraphEvent.Builder<Builder> {
        private Object edgeId;
        private Object from;
        private Object to;

        private Builder() {

        }

        public Builder edgeId(Object edgeId) {
            this.edgeId = edgeId;
            return self();
        }

        public Builder from(Object from) {
            this.from = from;
            return this;
        }

        public Builder to(Object to) {
            this.to = to;
            return this;
        }

        @Override
        public EdgeRemoved build() {
            return new EdgeRemoved(this);
        }
    }
}
