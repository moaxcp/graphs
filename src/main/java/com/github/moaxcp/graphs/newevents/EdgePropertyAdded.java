package com.github.moaxcp.graphs.newevents;

public class EdgePropertyAdded extends EdgeProperty {

    private EdgePropertyAdded(Builder builder) {
        super(builder);
    }

    public static class Builder extends EdgeProperty.Builder<Builder> {

        @Override
        public EdgePropertyAdded build() {
            return new EdgePropertyAdded(this);
        }
    }
}
