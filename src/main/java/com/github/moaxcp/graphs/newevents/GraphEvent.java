package com.github.moaxcp.graphs.newevents;

import java.util.Optional;

public abstract class GraphEvent {
    private Object id;

    protected GraphEvent(Builder builder) {
        id = builder.id;
    }

    public Optional<Object> getId() {
        return Optional.of(id);
    }

    public static abstract class Builder<T extends Builder<T>> {
        private Object id;

        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }

        public T id(Object id) {
            this.id = id;
            return self();
        }

        public abstract GraphEvent build();
    }
}
