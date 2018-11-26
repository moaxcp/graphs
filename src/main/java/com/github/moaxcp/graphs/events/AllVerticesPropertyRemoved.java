package com.github.moaxcp.graphs.events;

public class AllVerticesPropertyRemoved extends Property {

    private AllVerticesPropertyRemoved(Builder builder) {
        super(builder);
    }

    public static class Builder extends Property.Builder<Builder> {

        @Override
        public AllVerticesPropertyRemoved build() {
            return new AllVerticesPropertyRemoved(this);
        }
    }
}
