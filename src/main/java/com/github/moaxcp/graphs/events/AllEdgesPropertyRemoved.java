package com.github.moaxcp.graphs.events;

public class AllEdgesPropertyRemoved extends Property {

    private AllEdgesPropertyRemoved(Builder builder) {
        super(builder);
    }

    public static class Builder extends Property.Builder<Builder> {

        @Override
        public AllEdgesPropertyRemoved build() {
            return new AllEdgesPropertyRemoved(this);
        }
    }
}
