package com.github.moaxcp.graphs;

import static java.util.Objects.requireNonNull;

abstract class EdgeKey<T> {
    private T from;
    private T to;

    public EdgeKey(T from, T to) {
        this.from = requireNonNull(from);
        this.to = requireNonNull(to);
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }
}
