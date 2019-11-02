package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@ToString
public abstract class EdgeIdEvent<K> extends EdgeEvent<K> {
    final K edgeId;

    protected EdgeIdEvent(Builder<K, ? extends Builder> builder) {
        super(builder);
        edgeId = builder.edgeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeIdEvent that = (EdgeIdEvent) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(edgeId, that.edgeId) &&
                Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), edgeId, getFrom(), getTo());
    }

    @SuppressWarnings("squid:S2176")
    public abstract static class Builder<K, S extends Builder<K, S>> extends EdgeEvent.Builder<K, S> {
        private K edgeId;

        public final S edgeId(K edgeId) {
            this.edgeId = edgeId;
            return self();
        }
    }
}
