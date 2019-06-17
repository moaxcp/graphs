package com.github.moaxcp.graphs.events;

public final class GraphPropertyAdded<K> extends PropertyEvent<K> {

    private GraphPropertyAdded(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends PropertyEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public GraphPropertyAdded<K> build() {
            return new GraphPropertyAdded<>(this);
        }
    }
}
