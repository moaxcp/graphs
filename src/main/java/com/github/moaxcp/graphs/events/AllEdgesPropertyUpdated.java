package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class AllEdgesPropertyUpdated extends Property {
    private final Object oldValue;

    private AllEdgesPropertyUpdated(Builder builder) {
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
        AllEdgesPropertyUpdated that = (AllEdgesPropertyUpdated) o;
        return Objects.equals(getGraphId(), that.getGraphId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getValue(), that.getValue()) && Objects.equals(oldValue, that.oldValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue(), oldValue);
    }

    public static class Builder extends Property.Builder<Builder> {
        private Object oldValue;

        public Builder oldValue(Object oldValue) {
            this.oldValue = oldValue;
            return this;
        }

        @Override
        public AllEdgesPropertyUpdated build() {
            return new AllEdgesPropertyUpdated(this);
        }
    }
}
