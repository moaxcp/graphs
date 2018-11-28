package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class DirectedGraphCreated extends GraphEvent {

    private DirectedGraphCreated(Builder builder) {
        super(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedGraphCreated that = (DirectedGraphCreated) o;
        return Objects.equals(getGraphId(), that.getGraphId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId());
    }

    public static class Builder extends GraphEvent.Builder<Builder> {
        @Override
        public DirectedGraphCreated build() {
            return new DirectedGraphCreated(this);
        }
    }
}