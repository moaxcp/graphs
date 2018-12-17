package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.*;

public abstract class EdgeEvent<K> extends GraphEvent<K> {
    private final K edgeId;
    private final K from;
    private final K to;

    protected EdgeEvent(Builder<K, ? extends Builder> builder) {
        super(builder);
        edgeId = builder.edgeId;
        from = requireNonNull(builder.from);
        to = requireNonNull(builder.to);
    }

    public final Optional<K> getEdgeId() {
        return Optional.ofNullable(edgeId);
    }

    public final K getFrom() {
        return from;
    }

    public final K getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeEvent that = (EdgeEvent) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getEdgeId(), that.getEdgeId()) &&
                Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getEdgeId(), getFrom(), getTo());
    }

    @SuppressWarnings("squid:S2176")
    public abstract static class Builder<K, S extends Builder<K, S>> extends GraphEvent.Builder<K, S> {
        private K edgeId;
        private K from;
        private K to;

        public final S edgeId(K edgeId) {
            this.edgeId = edgeId;
            return self();
        }

        public final S from(K from) {
            this.from = from;
            return self();
        }

        public final S to(K to) {
            this.to = to;
            return self();
        }
    }
}
