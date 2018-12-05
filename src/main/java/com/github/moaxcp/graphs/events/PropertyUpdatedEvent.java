package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public abstract class PropertyUpdatedEvent extends PropertyEvent {
    private final Object oldValue;

    protected PropertyUpdatedEvent(Builder builder) {
        super(builder);
        oldValue = requireNonNull(builder.oldValue, "oldValue must not be null.");
    }

    public Object getOldValue() {
        return oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyUpdatedEvent that = (PropertyUpdatedEvent) o;
        return Objects.equals(getGraphId(), that.getGraphId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getValue(), that.getValue()) && Objects.equals(getOldValue(), that.getOldValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue(), getOldValue());
    }

    public abstract static class Builder<T extends Builder> extends PropertyEvent.Builder<T> {
        private Object oldValue;

        public final T oldValue(Object oldValue) {
            this.oldValue = oldValue;
            return self();
        }
    }
}
