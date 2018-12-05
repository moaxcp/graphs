package com.github.moaxcp.graphs.events;

public final class GraphPropertyRemoved extends PropertyEvent {

    private GraphPropertyRemoved(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder extends PropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public GraphPropertyRemoved build() {
            return new GraphPropertyRemoved(this);
        }
    }
}
