package com.github.moaxcp.graphs.events;

public final class AllVerticesPropertyUpdated<K> extends PropertyUpdatedEvent<K> {

    private AllVerticesPropertyUpdated(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends PropertyUpdatedEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public AllVerticesPropertyUpdated<K> build() {
            return new AllVerticesPropertyUpdated<>(this);
        }
    }
}
