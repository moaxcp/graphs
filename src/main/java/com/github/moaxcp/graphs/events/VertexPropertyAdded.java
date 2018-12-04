package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class VertexPropertyAdded extends VertexPropertyEvent {

    private VertexPropertyAdded(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexPropertyAdded vertexPropertyAdded = (VertexPropertyAdded) o;
        return Objects.equals(getGraphId(), vertexPropertyAdded.getGraphId()) && Objects.equals(getVertexId(), vertexPropertyAdded.getVertexId()) && Objects.equals(getName(), vertexPropertyAdded.getName()) && Objects.equals(getValue(), vertexPropertyAdded.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getVertexId(), getName(), getValue());
    }

    public static class Builder extends VertexPropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public VertexPropertyAdded build() {
            return new VertexPropertyAdded(this);
        }
    }
}
