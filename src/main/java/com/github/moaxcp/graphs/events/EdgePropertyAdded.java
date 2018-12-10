package com.github.moaxcp.graphs.events;

public final class EdgePropertyAdded extends EdgePropertyEvent {

    private EdgePropertyAdded(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder extends EdgePropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public EdgePropertyAdded build() {
            return new EdgePropertyAdded(this);
        }
    }
}
