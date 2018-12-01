package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class VertexRemoved extends GraphEvent {

    private final Object vertexId;

    private VertexRemoved(Builder builder) {
        super(builder);
        vertexId = builder.vertexId;
    }

    public Object getVertexId() {
        return vertexId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexRemoved that = (VertexRemoved) o;
        return Objects.equals(getGraphId(), that.getGraphId()) && Objects.equals(vertexId, that.vertexId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), vertexId);
    }

    static class Builder extends GraphEvent.Builder<Builder> {
        private Object vertexId;

        public Builder vertexId(Object vertexId) {
            this.vertexId = vertexId;
            return this;
        }

        @Override
        public VertexRemoved build() {
            return new VertexRemoved(this);
        }
    }
}
