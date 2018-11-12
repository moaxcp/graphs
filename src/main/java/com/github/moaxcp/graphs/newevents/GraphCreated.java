package com.github.moaxcp.graphs.newevents;

public class GraphCreated extends GraphEvent {

    private GraphCreated(Builder builder) {
        super(builder);
    }

    public static class Builder extends GraphEvent.Builder<Builder> {
        @Override
        public GraphCreated build() {
            return new GraphCreated(this);
        }
    }
}
