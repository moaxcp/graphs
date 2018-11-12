package com.github.moaxcp.graphs.newevents;

public class GraphPropertyRemoved extends Property {

    private GraphPropertyRemoved(Builder builder) {
        super(builder);
    }
    
    public static class Builder extends Property.Builder<Builder> {

        @Override
        public GraphPropertyRemoved build() {
            return new GraphPropertyRemoved(this);
        }
    }
}
