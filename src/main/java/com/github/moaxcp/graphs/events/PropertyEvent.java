package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public abstract class PropertyEvent extends GraphEvent {

    private final String name;
    private final Object value;

    protected PropertyEvent(Builder builder) {
        super(builder);
        name = requireNonNull(builder.name, "name must not be null.");
        value = requireNonNull(builder.value, "value must not be null.");
    }

    public final String getName() {
        return name;
    }

    public final Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyEvent that = (PropertyEvent) o;
        return Objects.equals(getGraphId(), that.getGraphId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue());
    }

    @SuppressWarnings("squid:S2176")
    public static abstract class Builder<T extends Builder> extends GraphEvent.Builder<T> {
        private String name;
        private Object value;

        public final T name(String name) {
            this.name = name;
            return self();
        }

        public final T value(Object value) {
            this.value = value;
            return self();
        }
    }
}
