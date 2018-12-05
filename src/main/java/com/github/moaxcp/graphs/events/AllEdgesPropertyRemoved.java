package com.github.moaxcp.graphs.events;

public final class AllEdgesPropertyRemoved extends PropertyEvent {

    private AllEdgesPropertyRemoved(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder extends PropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public AllEdgesPropertyRemoved build() {
            return new AllEdgesPropertyRemoved(this);
        }
    }
}
