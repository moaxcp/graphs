package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class AllEdgesPropertyRemoved<K> extends PropertyEvent<K> {

    private AllEdgesPropertyRemoved(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends PropertyEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public AllEdgesPropertyRemoved<K> build() {
            return new AllEdgesPropertyRemoved<>(this);
        }
    }
}
