package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class EdgePropertyRemoved extends EdgePropertyEvent {

    private EdgePropertyRemoved(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgePropertyRemoved that = (EdgePropertyRemoved) o;
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

    public static class Builder extends EdgePropertyEvent.Builder<Builder> {

        private Builder() {

        }

        @Override
        public EdgePropertyRemoved build() {
            return new EdgePropertyRemoved(this);
        }
    }
}
