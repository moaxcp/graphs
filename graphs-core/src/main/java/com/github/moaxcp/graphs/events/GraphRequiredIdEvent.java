package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

import static java.util.Objects.*;

@ToString
public abstract class GraphRequiredIdEvent<K> {

    private final K graphId;

    protected GraphRequiredIdEvent(Builder<K, ? extends Builder> builder) {
        graphId = requireNonNull(builder.graphId, "graphId must not be null.");
    }

    public final K getGraphId() {
        return graphId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphRequiredIdEvent<?> that = (GraphRequiredIdEvent<?>) o;
        return Objects.equals(graphId, that.graphId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graphId);
    }

    @SuppressWarnings("squid:S2176")
    public abstract static class Builder<K, S extends Builder<K, S>> {

        private K graphId;

        public abstract S self();

        public S graphId(K graphId) {
            this.graphId = graphId;
            return self();
        }

        public abstract GraphRequiredIdEvent<K> build();
    }
}
