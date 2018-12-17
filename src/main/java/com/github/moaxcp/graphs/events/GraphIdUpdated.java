package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class GraphIdUpdated<K> {

    private final K graphId;
    private final K oldGraphId;

    private GraphIdUpdated(Builder<K> builder) {
        graphId = requireNonNull(builder.graphId, "graphId must not be null.");
        oldGraphId = requireNonNull(builder.oldGraphId, "oldGraphId must not be null.");
    }

    public final K getGraphId() {
        return graphId;
    }

    public final K getOldGraphId() {
        return oldGraphId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphIdUpdated<?> that = (GraphIdUpdated<?>) o;
        return Objects.equals(graphId, that.graphId) && Objects.equals(oldGraphId, that.oldGraphId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graphId, oldGraphId);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> {

        private K graphId;
        private K oldGraphId;

        public Builder<K> graphId(K graphId) {
            this.graphId = graphId;
            return this;
        }

        public Builder<K> oldGraphId(K oldGraphId) {
            this.oldGraphId = oldGraphId;
            return this;
        }

        public GraphIdUpdated<K> build() {
            return new GraphIdUpdated<>(this);
        }
    }
}
