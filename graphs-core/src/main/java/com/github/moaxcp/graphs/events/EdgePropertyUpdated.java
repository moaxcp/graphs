package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class EdgePropertyUpdated<K> extends EdgePropertyEvent<K> {
    private final Object oldValue;

    private EdgePropertyUpdated(Builder<K> builder) {
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
        EdgePropertyUpdated that = (EdgePropertyUpdated) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getEdgeId(), that.getEdgeId()) &&
                Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(getOldValue(), that.getOldValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getEdgeId(), getFrom(), getTo(), getName(), getValue(), getOldValue());
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends EdgePropertyEvent.Builder<K, Builder<K>> {
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
        public EdgePropertyUpdated<K> build() {
            return new EdgePropertyUpdated<>(this);
        }
    }
}
