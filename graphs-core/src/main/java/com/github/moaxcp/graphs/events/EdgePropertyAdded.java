package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class EdgePropertyAdded<K> extends EdgePropertyEvent<K> {

    private EdgePropertyAdded(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends EdgePropertyEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgePropertyAdded<K> build() {
            return new EdgePropertyAdded<>(this);
        }
    }
}
