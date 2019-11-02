package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class EdgePropertyRemoved<K> extends EdgePropertyEvent<K> {

    private EdgePropertyRemoved(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends EdgePropertyEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgePropertyRemoved<K> build() {
            return new EdgePropertyRemoved<>(this);
        }
    }
}
