package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class GraphIdAdded<K> {

    private final K graphId;

    private GraphIdAdded(Builder<K> builder) {
        graphId = requireNonNull(builder.graphId, "graphId must not be null.");
    }

    public final K getGraphId() {
        return graphId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphIdAdded<?> that = (GraphIdAdded<?>) o;
        return Objects.equals(graphId, that.graphId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graphId);
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
