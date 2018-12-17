package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class EdgeIdAdded<K> extends GraphEvent<K> {
    private final K edgeId;
    private final K from;
    private final K to;

    protected EdgeIdAdded(Builder<K> builder) {
        super(builder);
        edgeId = requireNonNull(builder.edgeId, "edgeId must not be null.");
        from = requireNonNull(builder.from, "from must not be null.");
        to = requireNonNull(builder.to, "to must not be null.");
    }

    public final K getEdgeId() {
        return edgeId;
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
        EdgeIdAdded<?> that = (EdgeIdAdded<?>) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
            Objects.equals(edgeId, that.edgeId) &&
            Objects.equals(from, that.from) &&
            Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), edgeId, from, to);
    }

    @SuppressWarnings("squid:S2176")
    public final static class Builder<K> extends GraphEvent.Builder<K, Builder<K>> {
        private K edgeId;
        private K from;
        private K to;

        public final Builder<K> edgeId(K edgeId) {
            this.edgeId = edgeId;
            return self();
        }

        public final Builder<K> from(K from) {
            this.from = from;
            return self();
        }

        public final Builder<K> to(K to) {
            this.to = to;
            return self();
        }

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgeIdAdded<K> build() {
            return new EdgeIdAdded<>(this);
        }
    }
}
