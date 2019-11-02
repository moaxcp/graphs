package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@ToString
public final class EdgeFromUpdated<K> extends EdgeOptionalIdEvent<K> {

    private final K oldFrom;

    protected EdgeFromUpdated(Builder<K> builder) {
        super(builder);
        oldFrom = requireNonNull(builder.oldFrom, "oldFrom must not be null.");
    }

    public K getOldFrom() {
        return oldFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeFromUpdated<?> that = (EdgeFromUpdated<?>) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getEdgeId(), that.getEdgeId()) &&
                Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo()) &&
                Objects.equals(oldFrom, that.oldFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), oldFrom);
    }

    public static final class Builder<K> extends EdgeOptionalIdEvent.Builder<K, Builder<K>> {

        private K oldFrom;


        public Builder<K> oldFrom(K oldFrom) {
            this.oldFrom = oldFrom;
            return self();
        }
        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeFromUpdated<K> build() {
            return new EdgeFromUpdated<>(this);
        }
    }
}
