package com.github.moaxcp.graphs.newevents;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class EdgeAdded extends GraphEvent {

    private Object edgeId;
    private Object from;
    private Object to;

    protected EdgeAdded(Builder builder) {
        super(builder);
        edgeId = builder.edgeId;
        from = requireNonNull(builder.from);
        to = requireNonNull(builder.to);
    }

    public Optional<Object> getEdgeId() {
        return Optional.of(edgeId);
    }

    public Object getFrom() {
        return from;
    }

    public Object getTo() {
        return to;
    }

    static class Builder extends GraphEvent.Builder<Builder> {
        private Object edgeId;
        private Object from;
        private Object to;

        public Builder edgeId(Object edgeId) {
            this.edgeId = edgeId;
            return this;
        }

        public Builder from(Object from) {
            this.from = from;
            return this;
        }

        public Builder to(Object to) {
            this.to = to;
            return this;
        }

        @Override
        public EdgeAdded build() {
            return new EdgeAdded(this);
        }
    }
}
