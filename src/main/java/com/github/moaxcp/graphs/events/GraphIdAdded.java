package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

public final class GraphIdAdded<K> {

    private final K graphId;

    private GraphIdAdded(Builder<K> builder) {
        graphId = requireNonNull(builder.graphId, "graphId must not be null.");
    }

    public final K getGraphId() {
        return graphId;
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> {

        private K graphId;

        public Builder<K> graphId(K graphId) {
            this.graphId = graphId;
            return this;
        }

        public GraphIdAdded<K> build() {
            return new GraphIdAdded<>(this);
        }
    }
}
