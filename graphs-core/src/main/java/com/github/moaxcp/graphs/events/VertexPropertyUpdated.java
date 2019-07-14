package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class VertexPropertyUpdated<K> extends VertexPropertyEvent<K> {
    private final Object oldValue;

    private VertexPropertyUpdated(Builder<K> builder) {
        super(builder);
        oldValue = requireNonNull(builder.oldValue);
    }

    public Object getOldValue() {
        return oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexPropertyUpdated that = (VertexPropertyUpdated) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getVertexId(), that.getVertexId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(oldValue, that.oldValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getVertexId(), getName(), getValue(), oldValue);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends VertexPropertyEvent.Builder<K, Builder<K>> {
        private Object oldValue;

        @Override
        public Builder<K> self() {
            return this;
        }

        public Builder<K> oldValue(Object oldValue) {
            this.oldValue = oldValue;
            return this;
        }

        @Override
        public VertexPropertyUpdated<K> build() {
            return new VertexPropertyUpdated<>(this);
        }
    }
}