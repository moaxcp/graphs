package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class AllEdgesPropertyUpdated<K> extends PropertyUpdatedEvent<K> {

    private AllEdgesPropertyUpdated(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends PropertyUpdatedEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public AllEdgesPropertyUpdated<K> build() {
            return new AllEdgesPropertyUpdated<>(this);
        }
    }
}
