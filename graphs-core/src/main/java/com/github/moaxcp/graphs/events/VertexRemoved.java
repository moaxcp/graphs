package com.github.moaxcp.graphs.events;

public final class VertexRemoved<K> extends VertexEvent<K> {

    private VertexRemoved(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends VertexEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public VertexRemoved<K> build() {
            return new VertexRemoved<>(this);
        }
    }
}
