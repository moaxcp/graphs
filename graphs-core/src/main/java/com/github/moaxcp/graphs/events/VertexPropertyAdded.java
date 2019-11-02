package com.github.moaxcp.graphs.events;

import lombok.*;

@ToString
public final class VertexPropertyAdded<K> extends VertexPropertyEvent<K> {

    private VertexPropertyAdded(Builder<K> builder) {
        super(builder);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends VertexPropertyEvent.Builder<K, Builder<K>> {

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public VertexPropertyAdded<K> build() {
            return new VertexPropertyAdded<>(this);
        }
    }
}
