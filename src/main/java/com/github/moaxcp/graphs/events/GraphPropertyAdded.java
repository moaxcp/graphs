package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class GraphPropertyAdded extends PropertyEvent {

    private GraphPropertyAdded(Builder builder) {
        super(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphPropertyAdded that = (GraphPropertyAdded) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue());
    }

    public static class Builder extends PropertyEvent.Builder<Builder> {

        @Override
        public GraphPropertyAdded build() {
            return new GraphPropertyAdded(this);
        }
    }
}
