package com.github.moaxcp.graphs.events;

public final class EdgeRemoved extends EdgeEvent {

    private EdgeRemoved(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder extends EdgeEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public EdgeRemoved build() {
            return new EdgeRemoved(this);
        }
    }
}
