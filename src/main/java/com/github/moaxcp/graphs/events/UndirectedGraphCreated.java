package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class UndirectedGraphCreated extends GraphEvent {

    private UndirectedGraphCreated(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedGraphCreated that = (UndirectedGraphCreated) o;
        return Objects.equals(getGraphId(), that.getGraphId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId());
    }

    public final static class Builder extends GraphEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public UndirectedGraphCreated build() {
            return new UndirectedGraphCreated(this);
        }
    }
}
