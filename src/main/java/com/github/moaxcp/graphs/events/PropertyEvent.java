package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

abstract class PropertyEvent extends GraphEvent {

    private final String name;
    private final Object value;

    protected PropertyEvent(Builder builder) {
        super(builder);
        name = requireNonNull(builder.name, "name must not be null.");
        value = requireNonNull(builder.value, "value must not be null.");
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    static abstract class Builder<T> extends GraphEvent.Builder<Builder<T>> {
        private String name;
        private Object value;

        public Builder<T> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<T> value(Object value) {
            this.value = value;
            return this;
        }
    }
}
