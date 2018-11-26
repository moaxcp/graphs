package com.github.moaxcp.graphs.events;

public class AllEdgesPropertyAdded extends Property {

    private AllEdgesPropertyAdded(Builder builder) {
        super(builder);
    }

    public static class Builder extends Property.Builder<Builder> {

        @Override
        public AllEdgesPropertyAdded build() {
            return new AllEdgesPropertyAdded(this);
        }
    }
}
