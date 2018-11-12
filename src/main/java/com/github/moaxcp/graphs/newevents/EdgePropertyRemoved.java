package com.github.moaxcp.graphs.newevents;

public class EdgePropertyRemoved extends EdgeProperty {

    private EdgePropertyRemoved(Builder builder) {
        super(builder);
    }

    public static class Builder extends EdgeProperty.Builder<Builder> {

        @Override
        public EdgePropertyRemoved build() {
            return new EdgePropertyRemoved(this);
        }
    }
}
