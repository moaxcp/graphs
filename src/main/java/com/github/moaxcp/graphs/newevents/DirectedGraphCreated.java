package com.github.moaxcp.graphs.newevents;

public class DirectedGraphCreated extends GraphEvent {

    private DirectedGraphCreated(Builder builder) {
        super(builder);
    }

    public static class Builder extends GraphEvent.Builder<Builder> {
        @Override
        public DirectedGraphCreated build() {
            return new DirectedGraphCreated(this);
        }
    }
}
