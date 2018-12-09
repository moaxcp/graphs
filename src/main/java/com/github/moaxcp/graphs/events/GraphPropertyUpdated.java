package com.github.moaxcp.graphs.events;

public final class GraphPropertyUpdated extends PropertyUpdatedEvent {

    private GraphPropertyUpdated(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("squid:S2176")
    public final static class Builder extends PropertyUpdatedEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public GraphPropertyUpdated build() {
            return new GraphPropertyUpdated(this);
        }
    }
}
