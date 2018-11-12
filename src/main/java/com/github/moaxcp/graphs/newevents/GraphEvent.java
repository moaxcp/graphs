package com.github.moaxcp.graphs.newevents;

import java.util.Optional;

public abstract class GraphEvent {
    private Object graphId;

    protected GraphEvent(Builder builder) {
        graphId = builder.graphId;
    }

    public Optional<Object> getGraphId() {
        return Optional.of(graphId);
    }

    public static abstract class Builder<T extends Builder<T>> {
        private Object graphId;

        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }

        public T graphId(Object graphId) {
            this.graphId = graphId;
            return self();
        }

        public abstract GraphEvent build();
    }
}
