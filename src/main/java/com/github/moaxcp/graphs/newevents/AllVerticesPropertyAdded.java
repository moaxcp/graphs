package com.github.moaxcp.graphs.newevents;

public class AllVerticesPropertyAdded extends Property {

    private AllVerticesPropertyAdded(Builder builder) {
        super(builder);
    }

    public static class Builder extends Property.Builder<Builder> {

        @Override
        public AllVerticesPropertyAdded build() {
            return new AllVerticesPropertyAdded(this);
        }
    }
}
