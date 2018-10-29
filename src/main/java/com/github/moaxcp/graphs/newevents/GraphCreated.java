package com.github.moaxcp.graphs.newevents;

import java.util.Optional;

public class GraphCreated extends GraphEvent {

    private GraphCreated(Builder builder) {
        super(builder);
    }

    public static class Builder extends GraphEvent.Builder<Builder> {
        protected Builder() {

        }
        @Override
        public GraphCreated build() {
            return new GraphCreated(this);
        }
    }
}
