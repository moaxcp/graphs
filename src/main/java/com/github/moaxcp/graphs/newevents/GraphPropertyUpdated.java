package com.github.moaxcp.graphs.newevents;

import static java.util.Objects.requireNonNull;

public class GraphPropertyUpdated extends Property {

    private Object oldValue;

    private GraphPropertyUpdated(Builder builder) {
        super(builder);
        oldValue = requireNonNull(builder.oldValue);
    }

    public Object getOldValue() {
        return oldValue;
    }

    public static class Builder extends Property.Builder<Builder> {
        private Object oldValue;

        public Builder oldValue(Object oldValue) {
            this.oldValue = oldValue;
            return this;
        }
        @Override
        public GraphPropertyUpdated build() {
            return new GraphPropertyUpdated(this);
        }
    }
}
