package com.github.moaxcp.graphs.events;

public class GraphPropertyAdded extends Property {

    private GraphPropertyAdded(Builder builder) {
        super(builder);
    }

    public static class Builder extends Property.Builder<Builder> {

        @Override
        public GraphPropertyAdded build() {
            return new GraphPropertyAdded(this);
        }
    }
}
