package com.github.moaxcp.graphs.events;

public final class EdgeRemoved<K> extends EdgeEvent<K> {

    private EdgeRemoved(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends EdgeEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeRemoved<K> build() {
            return new EdgeRemoved<>(this);
        }
    }
}
