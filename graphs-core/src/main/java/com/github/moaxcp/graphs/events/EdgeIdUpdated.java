package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

import static java.util.Objects.*;

@ToString
public final class EdgeIdUpdated<K> extends EdgeRequiredIdEvent<K> {
    private final K oldEdgeId;

    protected EdgeIdUpdated(Builder<K> builder) {
        super(builder);
        oldEdgeId = requireNonNull(builder.oldEdgeId, "oldEdgeId must not be null.");
    }

    public final K getOldEdgeId() {
        return oldEdgeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeIdUpdated that = (EdgeIdUpdated) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
            Objects.equals(edgeId, that.edgeId) &&
            Objects.equals(oldEdgeId, that.getOldEdgeId()) &&
            Objects.equals(getFrom(), that.getFrom()) &&
            Objects.equals(getTo(), that.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), edgeId, oldEdgeId, getFrom(), getTo());
    }


    @SuppressWarnings("squid:S2176")
    public final static class Builder<K> extends EdgeRequiredIdEvent.Builder<K, Builder<K>> {
        private K oldEdgeId;

        public final Builder<K> oldEdgeId(K oldEdgeId) {
            this.oldEdgeId = oldEdgeId;
            return self();
        }

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeIdUpdated<K> build() {
            return new EdgeIdUpdated<>(this);
        }
    }
}
