package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class VertexPropertyUpdated extends VertexPropertyEvent {
    private final Object oldValue;

    private VertexPropertyUpdated(Builder builder) {
        super(builder);
        oldValue = requireNonNull(builder.oldValue);
    }

    public Object getOldValue() {
        return oldValue;
    }

    public static Builder builder() {
        return new Builder();
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
    public final static class Builder extends VertexPropertyEvent.Builder<Builder> {
        private Object oldValue;

        public Builder oldValue(Object oldValue) {
            this.oldValue = oldValue;
            return this;
        }

        @Override
        public VertexPropertyUpdated build() {
            return new VertexPropertyUpdated(this);
        }
    }
}
