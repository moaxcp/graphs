package com.github.moaxcp.graphs.events;

public final class AllVerticesPropertyUpdated extends PropertyUpdatedEvent {

    private AllVerticesPropertyUpdated(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder extends PropertyUpdatedEvent.Builder<Builder> {
        private Builder() {

        }

        @Override
        public AllVerticesPropertyUpdated build() {
            return new AllVerticesPropertyUpdated(this);
        }
    }
}
