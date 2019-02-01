package com.github.moaxcp.graphs;

import java.util.Objects;

final class UndirectedEdgeKey<T> extends EdgeKey<T> {

    public UndirectedEdgeKey(T from, T to) {
        super(from, to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedEdgeKey that = (UndirectedEdgeKey) o;
        return (Objects.equals(getFrom(), that.getFrom()) || Objects.equals(getFrom(), that.getTo())) && (Objects.equals(getTo(), that.getTo()) || Objects.equals(getTo(), that.getFrom()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo());
    }
}
