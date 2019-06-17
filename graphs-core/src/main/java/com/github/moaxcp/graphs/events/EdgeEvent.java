package com.github.moaxcp.graphs.events;

import static java.util.Objects.requireNonNull;

public abstract class EdgeEvent<K> extends GraphEvent<K> {
    private final K from;
    private final K to;

    protected EdgeEvent(Builder<K, ? extends Builder> builder) {
        super(builder);
        from = requireNonNull(builder.from, "from must not be null.");
        to = requireNonNull(builder.to, "to must not be null.");
    }

    public final K getFrom() {
        return from;
    }

    public final K getTo() {
        return to;
    }

    @SuppressWarnings("squid:S2176")
    public abstract static class Builder<K, S extends Builder<K, S>> extends GraphEvent.Builder<K, S> {
        private K from;
        private K to;

        public final S from(K from) {
            this.from = from;
            return self();
        }

        public final S to(K to) {
            this.to = to;
            return self();
        }
    }
}
