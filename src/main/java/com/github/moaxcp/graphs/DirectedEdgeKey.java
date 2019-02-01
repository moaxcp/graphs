package com.github.moaxcp.graphs;

import java.util.Objects;

public final class DirectedEdgeKey<T> extends EdgeKey<T> {

    public DirectedEdgeKey(T from, T to) {
        super(from, to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedEdgeKey that = (DirectedEdgeKey) o;
        return Objects.equals(getFrom(), that.getFrom()) && Objects.equals(getTo(), that.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo());
    }
}
