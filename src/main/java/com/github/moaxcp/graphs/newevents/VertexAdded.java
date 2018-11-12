package com.github.moaxcp.graphs.newevents;

public class VertexAdded extends GraphEvent {

    private Object vertexId;

    protected VertexAdded(Builder builder) {
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
        public VertexAdded build() {
            return new VertexAdded(this);
        }
    }
}
