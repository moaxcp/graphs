package com.github.moaxcp.graphs.newevents;

import static java.util.Objects.requireNonNull;

public class AllVerticesPropertyUpdated extends Property {
    private Object oldValue;

    private AllVerticesPropertyUpdated(Builder builder) {
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
        public AllVerticesPropertyUpdated build() {
            return new AllVerticesPropertyUpdated(this);
        }
    }
}
