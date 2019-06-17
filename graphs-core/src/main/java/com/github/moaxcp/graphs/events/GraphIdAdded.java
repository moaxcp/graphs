package com.github.moaxcp.graphs.events;

public final class GraphIdAdded<K> extends GraphRequiredIdEvent<K> {

    private GraphIdAdded(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends GraphRequiredIdEvent.Builder<K, Builder<K>> {

        public Builder<K> self() {
            return this;
        }

        public GraphIdAdded<K> build() {
            return new GraphIdAdded<>(this);
        }
    }
}
