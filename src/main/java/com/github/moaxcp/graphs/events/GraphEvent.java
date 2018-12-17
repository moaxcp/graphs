package com.github.moaxcp.graphs.events;

import java.util.*;

public abstract class GraphEvent<K> {
    private final K graphId;

    GraphEvent(Builder<K, ? extends Builder> builder) {
        graphId = builder.graphId;
    }

    public final Optional<K> getGraphId() {
        return Optional.ofNullable(graphId);
    }

    public abstract static class Builder<K, S extends Builder<K, S>> {
        private K graphId;

        public abstract S self();

        public final S graphId(K graphId) {
            this.graphId = graphId;
            return self();
        }

        public abstract GraphEvent<K> build();
    }
}
