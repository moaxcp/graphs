package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class GraphPropertyUpdated<K> extends PropertyUpdatedEvent<K> {

    private GraphPropertyUpdated(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends PropertyUpdatedEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public GraphPropertyUpdated<K> build() {
            return new GraphPropertyUpdated<>(this);
        }
    }
}
