package com.github.moaxcp.graphs.events;

public final class AllVerticesPropertyUpdated extends PropertyUpdatedEvent {

    private AllVerticesPropertyUpdated(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder extends PropertyUpdatedEvent.Builder<Builder> {
        private Builder() {

        }

        @Override
        public AllVerticesPropertyUpdated build() {
            return new AllVerticesPropertyUpdated(this);
        }
    }
}
