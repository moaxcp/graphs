package com.github.moaxcp.graphs.events;

public final class VertexCreated extends VertexEvent {

    private VertexCreated(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder extends VertexEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public VertexCreated build() {
            return new VertexCreated(this);
        }
    }
}
