package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

import static java.util.Objects.*;

@ToString
public final class GraphIdUpdated<K> extends GraphRequiredIdEvent<K> {
    private final K oldGraphId;

    private GraphIdUpdated(Builder<K> builder) {
        super(builder);
        oldGraphId = requireNonNull(builder.oldGraphId, "oldGraphId must not be null.");
    }

    public final K getOldGraphId() {
        return oldGraphId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphIdUpdated<?> that = (GraphIdUpdated<?>) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
            Objects.equals(oldGraphId, that.oldGraphId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), oldGraphId);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends GraphRequiredIdEvent.Builder<K, Builder<K>> {

        private K oldGraphId;

        public Builder<K> oldGraphId(K oldGraphId) {
            this.oldGraphId = oldGraphId;
            return self();
        }

        public Builder<K> self() {
            return this;
        }

        public GraphIdUpdated<K> build() {
            return new GraphIdUpdated<>(this);
        }
    }
}
