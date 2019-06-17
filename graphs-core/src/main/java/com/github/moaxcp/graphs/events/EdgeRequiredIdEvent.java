package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

public abstract class EdgeRequiredIdEvent<K> extends EdgeIdEvent<K> {

    protected EdgeRequiredIdEvent(Builder<K, ? extends Builder> builder) {
        super(builder);
        requireNonNull(edgeId, "edgeId must not be null.");
    }

    public final K getEdgeId() {
        return edgeId;
    }

    @SuppressWarnings("squid:S2176")
    public abstract static class Builder<K, S extends Builder<K, S>> extends EdgeIdEvent.Builder<K, S> {

    }
}
