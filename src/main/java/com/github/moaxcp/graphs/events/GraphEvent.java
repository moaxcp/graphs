package com.github.moaxcp.graphs.events;

import java.util.*;

public abstract class GraphEvent {
    private final Object graphId;

    GraphEvent(Builder builder) {
        graphId = builder.graphId;
    }

    public final Optional<Object> getGraphId() {
        return Optional.ofNullable(graphId);
    }

    public abstract static class Builder<T extends Builder> {
        private Object graphId;

        public final T self() {
            return (T) this;
        }

        public final T graphId(Object graphId) {
            this.graphId = graphId;
            return self();
        }

        public abstract GraphEvent build();
    }
}
