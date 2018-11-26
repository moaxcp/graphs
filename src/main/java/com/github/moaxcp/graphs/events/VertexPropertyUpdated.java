package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

public class VertexPropertyUpdated extends VertexProperty {
    private Object oldValue;

    private VertexPropertyUpdated(Builder builder) {
        super(builder);
        oldValue = requireNonNull(builder.oldValue);
    }

    public Object getOldValue() {
        return oldValue;
    }

    public static class Builder extends VertexProperty.Builder<Builder> {
        private Object oldValue;

        public Builder oldValue(Object oldValue) {
            this.oldValue = oldValue;
            return this;
        }

        @Override
        public VertexPropertyUpdated build() {
            return new VertexPropertyUpdated(this);
        }
    }
}
