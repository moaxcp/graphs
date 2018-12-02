package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;
import java.util.Objects;

public final class GraphPropertyUpdated extends PropertyEvent {

    private final Object oldValue;

    private GraphPropertyUpdated(Builder builder) {
        super(builder);
        oldValue = requireNonNull(builder.oldValue);
    }

    public Object getOldValue() {
        return oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphPropertyUpdated that = (GraphPropertyUpdated) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(getOldValue(), that.getOldValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue(), getOldValue());
    }

    public static class Builder extends PropertyEvent.Builder<Builder> {
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
