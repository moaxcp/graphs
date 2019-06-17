package com.github.moaxcp.graphs.events;

public final class AllEdgesPropertyAdded<K> extends PropertyEvent<K> {

    private AllEdgesPropertyAdded(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends PropertyEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public AllEdgesPropertyAdded<K> build() {
            return new AllEdgesPropertyAdded<>(this);
        }
    }
}
