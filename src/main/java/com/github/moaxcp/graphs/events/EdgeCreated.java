package com.github.moaxcp.graphs.events;

public final class EdgeCreated<K> extends EdgeEvent<K> {

    private EdgeCreated(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends EdgeEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeCreated<K> build() {
            return new EdgeCreated<>(this);
        }
    }
}
