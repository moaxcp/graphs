package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class DirectedGraphCreated<K> extends GraphEvent<K> {

    private DirectedGraphCreated(Builder<K> builder) {
        super(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedGraphCreated that = (DirectedGraphCreated) o;
        return Objects.equals(getGraphId(), that.getGraphId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId());
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends GraphEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public DirectedGraphCreated<K> build() {
            return new DirectedGraphCreated<>(this);
        }
    }
}
