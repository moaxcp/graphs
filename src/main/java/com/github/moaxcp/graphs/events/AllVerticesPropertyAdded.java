package com.github.moaxcp.graphs.events;

public final class AllVerticesPropertyAdded extends PropertyEvent {

    private AllVerticesPropertyAdded(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("squid:S2176")
    public final static class Builder extends PropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public AllVerticesPropertyAdded build() {
            return new AllVerticesPropertyAdded(this);
        }
    }
}
