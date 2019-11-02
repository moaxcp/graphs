package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class GraphIdRemoved<K> extends GraphRequiredIdEvent<K> {

    private GraphIdRemoved(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends GraphRequiredIdEvent.Builder<K, Builder<K>> {

        public Builder<K> self() {
            return this;
        }

        public GraphIdRemoved<K> build() {
            return new GraphIdRemoved<>(this);
        }
    }
}
