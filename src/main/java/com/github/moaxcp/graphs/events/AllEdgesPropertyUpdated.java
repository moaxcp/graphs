package com.github.moaxcp.graphs.events;

public final class AllEdgesPropertyUpdated extends PropertyUpdatedEvent {

    private AllEdgesPropertyUpdated(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends PropertyUpdatedEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public AllEdgesPropertyUpdated build() {
            return new AllEdgesPropertyUpdated(this);
        }
    }
}
