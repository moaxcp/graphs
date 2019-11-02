package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class EdgeRemoved<K> extends EdgeOptionalIdEvent<K> {

    private EdgeRemoved(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends EdgeOptionalIdEvent.Builder<K, Builder<K>> {

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
