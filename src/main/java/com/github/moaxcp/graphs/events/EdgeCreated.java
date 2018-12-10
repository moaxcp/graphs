package com.github.moaxcp.graphs.events;

public final class EdgeCreated extends EdgeEvent {

    private EdgeCreated(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder extends EdgeEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public EdgeCreated build() {
            return new EdgeCreated(this);
        }
    }
}
