package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class AllVerticesPropertyAdded extends Property {

    private AllVerticesPropertyAdded(Builder builder) {
        super(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllVerticesPropertyAdded vertexPropertyAdded = (AllVerticesPropertyAdded) o;
        return Objects.equals(getGraphId(), vertexPropertyAdded.getGraphId()) &&
                Objects.equals(getName(), vertexPropertyAdded.getName()) &&
                Objects.equals(getValue(), vertexPropertyAdded.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue());
    }

    public static class Builder extends Property.Builder<Builder> {

        @Override
        public AllVerticesPropertyAdded build() {
            return new AllVerticesPropertyAdded(this);
        }
    }
}
