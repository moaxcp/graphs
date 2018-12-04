package com.github.moaxcp.graphs.events;

import java.util.*;

public abstract class GraphEvent {
    private final Object graphId;

    GraphEvent(Builder builder) {
        graphId = builder.graphId;
    }

    public Optional<Object> getGraphId() {
        return Optional.ofNullable(graphId);
    }

    public static abstract class Builder<T extends Builder> {
        private Object graphId;

        public T self() {
            return (T) this;
        }

        public T graphId(Object graphId) {
            this.graphId = graphId;
            return self();
        }

        public abstract GraphEvent build();
    }
}
