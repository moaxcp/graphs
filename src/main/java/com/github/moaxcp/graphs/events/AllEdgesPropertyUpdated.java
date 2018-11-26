package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

public class AllEdgesPropertyUpdated extends Property {
    private Object oldValue;

    private AllEdgesPropertyUpdated(Builder builder) {
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
        public AllEdgesPropertyUpdated build() {
            return new AllEdgesPropertyUpdated(this);
        }
    }
}
