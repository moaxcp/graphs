package com.github.moaxcp.graphs.events;

import lombok.*;

import java.util.*;

@ToString
public abstract class EdgeOptionalIdEvent<K> extends EdgeIdEvent<K> {

    protected EdgeOptionalIdEvent(Builder<K, ? extends Builder> builder) {
        super(builder);
    }

    public final Optional<K> getEdgeId() {
        return Optional.ofNullable(edgeId);
    }

    @SuppressWarnings("squid:S2176")
    public abstract static class Builder<K, S extends Builder<K, S>> extends EdgeIdEvent.Builder<K, S> {

    }
}
