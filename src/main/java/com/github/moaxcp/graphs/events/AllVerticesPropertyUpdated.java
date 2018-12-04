package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class AllVerticesPropertyUpdated extends PropertyEvent {
    private final Object oldValue;

    private AllVerticesPropertyUpdated(Builder builder) {
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
        AllVerticesPropertyUpdated that = (AllVerticesPropertyUpdated) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(oldValue, that.oldValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue(), oldValue);
    }

    public static class Builder extends PropertyEvent.Builder<Builder> {
        private Object oldValue;

        private Builder() {

        }

        public Builder oldValue(Object oldValue) {
            this.oldValue = oldValue;
            return this;
        }

        @Override
        public AllVerticesPropertyUpdated build() {
            return new AllVerticesPropertyUpdated(this);
        }
    }
}
