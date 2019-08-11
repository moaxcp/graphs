package com.github.moaxcp.graphs.events;

import java.util.Optional;

public class EdgePropertiesEvent<K> extends PropertiesEvent<K> {
    private final K from;
    private final K to;
    private final K id;

    private EdgePropertiesEvent(Builder<K> builder) {
        super(builder);
        from = builder.from;
        to = builder.to;
        id = builder.id;
    }

    public K getFrom() {
        return from;
    }

    public K getTo() {
        return to;
    }

    public Optional<K> getId() {
        return Optional.ofNullable(id);
    }

    public static final class Builder<K> extends PropertiesEvent.Builder<K, Builder<K>> {
        private K from;
        private K to;
        private K id;

        public Builder<K> from(K from) {
            this.from = from;
            return this;
        }

        public Builder<K> to(K to) {
            this.to = to;
            return this;
        }

        public Builder<K> id(K id) {
            this.id = id;
            return this;
        }

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public EdgePropertiesEvent<K> build() {
            buildProperties();
            return new EdgePropertiesEvent<>(this);
        }
    }
}
