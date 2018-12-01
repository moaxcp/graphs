package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class AllVerticesPropertyRemoved extends Property {

    private AllVerticesPropertyRemoved(Builder builder) {
        super(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllVerticesPropertyRemoved that = (AllVerticesPropertyRemoved) o;
        return Objects.equals(getGraphId(), that.getGraphId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue());
    }

    public static class Builder extends Property.Builder<Builder> {

        @Override
        public AllVerticesPropertyRemoved build() {
            return new AllVerticesPropertyRemoved(this);
        }
    }
}
