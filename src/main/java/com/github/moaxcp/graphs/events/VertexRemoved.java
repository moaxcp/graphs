package com.github.moaxcp.graphs.events;

public class VertexRemoved extends GraphEvent {

    private Object vertexId;

    protected VertexRemoved(Builder builder) {
        super(builder);
        vertexId = builder.vertexId;
    }

    public Object getVertexId() {
        return vertexId;
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
