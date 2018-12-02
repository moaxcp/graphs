package com.github.moaxcp.graphs.events;

import java.util.Objects;

public final class AllEdgesPropertyRemoved extends PropertyEvent {

    private AllEdgesPropertyRemoved(Builder builder) {
        super(builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllEdgesPropertyRemoved that = (AllEdgesPropertyRemoved) o;
        return Objects.equals(getGraphId(), that.getGraphId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGraphId(), getName(), getValue());
    }

    public static class Builder extends PropertyEvent.Builder<Builder> {

        @Override
        public AllEdgesPropertyRemoved build() {
            return new AllEdgesPropertyRemoved(this);
        }
    }
}
