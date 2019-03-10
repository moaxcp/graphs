package com.github.moaxcp.graphs.events;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class EdgeToUpdated<K> extends EdgeOptionalIdEvent<K> {

    private final K oldTo;

    protected EdgeToUpdated(Builder<K> builder) {
        super(builder);
        oldTo = requireNonNull(builder.oldTo, "oldTo must not be null.");
    }

    public K getOldTo() {
        return oldTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeToUpdated<?> that = (EdgeToUpdated<?>) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getEdgeId(), that.getEdgeId()) &&
                Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo()) &&
                Objects.equals(oldTo, that.oldTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), oldTo);
    }

    public static final class Builder<K> extends EdgeOptionalIdEvent.Builder<K, Builder<K>> {

        private K oldTo;


        public Builder<K> oldTo(K oldTo) {
            this.oldTo = oldTo;
            return self();
        }
        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeToUpdated<K> build() {
            return new EdgeToUpdated<>(this);
        }
    }
}
