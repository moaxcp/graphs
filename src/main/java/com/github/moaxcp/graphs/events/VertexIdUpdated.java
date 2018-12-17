package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class VertexIdUpdated<K> extends VertexEvent<K> {

    private final K oldVertexId;

    public final K getOldVertexId() {
        return oldVertexId;
    }

    private VertexIdUpdated(Builder<K> builder) {
        super(builder);
        oldVertexId = requireNonNull(builder.oldVertexId, "oldVertexId must not be null.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VertexIdUpdated<?> that = (VertexIdUpdated<?>) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
            Objects.equals(getVertexId(), that.getVertexId()) &&
            Objects.equals(oldVertexId, that.oldVertexId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getVertexId(), oldVertexId);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends VertexEvent.Builder<K, Builder<K>> {
        private K oldVertexId;

        public Builder<K> oldVertexId(K oldVertexId) {
            this.oldVertexId = oldVertexId;
            return self();
        }

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public VertexIdUpdated<K> build() {
            return new VertexIdUpdated<>(this);
        }
    }
}
