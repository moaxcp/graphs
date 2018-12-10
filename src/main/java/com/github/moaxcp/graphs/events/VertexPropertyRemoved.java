package com.github.moaxcp.graphs.events;

public final class VertexPropertyRemoved extends VertexPropertyEvent {

    private VertexPropertyRemoved(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder extends VertexPropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public VertexPropertyRemoved build() {
            return new VertexPropertyRemoved(this);
        }
    }
}
