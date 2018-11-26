package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

public class EdgePropertyUpdated extends EdgeProperty {
    private Object oldValue;

    private EdgePropertyUpdated(Builder builder) {
        super(builder);
        oldValue = requireNonNull(builder.oldValue);
    }

    public Object getOldValue() {
        return oldValue;
    }

    public static class Builder extends EdgeProperty.Builder<Builder> {
        private Object oldValue;

        public Builder oldValue(Object oldValue) {
            this.oldValue = oldValue;
            return this;
        }

        @Override
        public EdgePropertyUpdated build() {
            return new EdgePropertyUpdated(this);
        }
    }
}
