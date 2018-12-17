package com.github.moaxcp.graphs.events;

public final class VertexCreated<K> extends VertexEvent<K> {

    private VertexCreated(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends VertexEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public VertexCreated<K> build() {
            return new VertexCreated<>(this);
        }
    }
}
