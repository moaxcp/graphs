package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class AllVerticesPropertyRemoved<K> extends PropertyEvent<K> {

    private AllVerticesPropertyRemoved(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends PropertyEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public AllVerticesPropertyRemoved<K> build() {
            return new AllVerticesPropertyRemoved<>(this);
        }
    }
}
