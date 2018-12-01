package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class EdgePropertyAdded extends EdgeProperty {

    private EdgePropertyAdded(Builder builder) {
        super(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgePropertyAdded that = (EdgePropertyAdded) o;
        return Objects.equals(getGraphId(), that.getGraphId()) &&
                Objects.equals(getEdgeId(), that.getEdgeId()) &&
                Objects.equals(getFrom(), that.getFrom()) &&
                Objects.equals(getTo(), that.getTo()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getEdgeId(), getFrom(), getTo(), getName(), getValue());
    }

    public static class Builder extends EdgeProperty.Builder<Builder> {

        @Override
        public EdgePropertyAdded build() {
            return new EdgePropertyAdded(this);
        }
    }
}
