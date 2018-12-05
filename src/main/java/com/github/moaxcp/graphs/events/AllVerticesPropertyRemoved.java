package com.github.moaxcp.graphs.events;

public final class AllVerticesPropertyRemoved extends PropertyEvent {

    private AllVerticesPropertyRemoved(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder extends PropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public AllVerticesPropertyRemoved build() {
            return new AllVerticesPropertyRemoved(this);
        }
    }
}
