package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class EdgeIdRemoved<K> extends EdgeRequiredIdEvent<K> {

    protected EdgeIdRemoved(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public final static class Builder<K> extends EdgeRequiredIdEvent.Builder<K, Builder<K>> {
        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeIdRemoved<K> build() {
            return new EdgeIdRemoved<>(this);
        }
    }
}
