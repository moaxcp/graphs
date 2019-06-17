package com.github.moaxcp.graphs.events;

public final class EdgeIdAdded<K> extends EdgeRequiredIdEvent<K> {

    protected EdgeIdAdded(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public final static class Builder<K> extends EdgeRequiredIdEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeIdAdded<K> build() {
            return new EdgeIdAdded<>(this);
        }
    }
}
