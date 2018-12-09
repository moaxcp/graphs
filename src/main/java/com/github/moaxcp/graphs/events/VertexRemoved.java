package com.github.moaxcp.graphs.events;

public final class VertexRemoved extends VertexEvent {

    private VertexRemoved(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder extends VertexEvent.Builder<Builder> {
        private Builder() {

        }

        @Override
        public VertexRemoved build() {
            return new VertexRemoved(this);
        }
    }
}
