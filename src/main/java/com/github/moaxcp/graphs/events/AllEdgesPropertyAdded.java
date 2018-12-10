package com.github.moaxcp.graphs.events;

public final class AllEdgesPropertyAdded extends PropertyEvent {

    private AllEdgesPropertyAdded(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder extends PropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public AllEdgesPropertyAdded build() {
            return new AllEdgesPropertyAdded(this);
        }
    }
}
