package com.github.moaxcp.graphs.events;

public final class VertexPropertyAdded extends VertexPropertyEvent {

    private VertexPropertyAdded(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder extends VertexPropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public VertexPropertyAdded build() {
            return new VertexPropertyAdded(this);
        }
    }
}
