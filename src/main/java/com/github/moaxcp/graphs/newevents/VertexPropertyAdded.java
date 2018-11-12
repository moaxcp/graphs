package com.github.moaxcp.graphs.newevents;

public class VertexPropertyAdded extends VertexProperty {

    private VertexPropertyAdded(Builder builder) {
        super(builder);
    }

    public static class Builder extends VertexProperty.Builder<Builder> {

        @Override
        public VertexPropertyAdded build() {
            return new VertexPropertyAdded(this);
        }
    }
}
