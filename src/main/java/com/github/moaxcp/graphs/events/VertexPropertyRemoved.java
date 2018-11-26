package com.github.moaxcp.graphs.events;

public class VertexPropertyRemoved extends VertexProperty {

    private VertexPropertyRemoved(Builder builder) {
        super(builder);
    }

    public static class Builder extends VertexProperty.Builder<Builder> {

        @Override
        public VertexPropertyRemoved build() {
            return new VertexPropertyRemoved(this);
        }
    }
}
