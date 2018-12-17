package com.github.moaxcp.graphs;

import static java.util.Objects.requireNonNull;
import com.github.moaxcp.graphs.events.*;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

public abstract class AbstractEventGraph<T> extends AbstractSimpleGraph<T> implements SimpleEventGraph<T> {

    public abstract class EventEdge extends AbstractEdge {
        protected EventEdge(T from, T to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public Edge<T> id(T id) {
            var oldId = getId();
            var edge = super.id(id);
            bus.post(new EdgeIdAdded.Builder<T>()
                .graphId(AbstractEventGraph.this.getId().orElse(null))
                .edgeId(id)
                .from(getFrom())
                .to(getTo())
                .build());
            return edge;
        }

        @Override
        public void setProperty(String name, Object value) {
            var oldValue = getProperty(name);
            super.setProperty(name, value);
            if(oldValue.isPresent()) {
                bus.post(new EdgePropertyUpdated.Builder<T>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .edgeId(getId().orElse(null))
                    .from(getFrom())
                    .to(getTo())
                    .name(name)
                    .value(value)
                    .oldValue(oldValue.orElse(null))
                    .build());
            } else {
                bus.post(new EdgePropertyAdded.Builder<T>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .edgeId(getId().orElse(null))
                    .from(getFrom())
                    .to(getTo())
                    .name(name)
                    .value(value)
                    .build());
            }
        }

        @Override
        public Edge<T> removeProperty(String name) {
            var value = getProperty(name);
            var edge = super.removeProperty(name);
            bus.post(new EdgePropertyRemoved.Builder<T>()
                .graphId(AbstractEventGraph.this.getId().orElse(null))
                .edgeId(getId().orElse(null))
                .from(getFrom())
                .to(getTo())
                .name(name)
                .value(value)
                .build());
            return edge;
        }
    }

    public abstract class EventVertex extends AbstractVertex {
        protected EventVertex(T id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public void setProperty(String name, Object value) {
            var oldValue = getProperty(name);
            super.setProperty(name, value);
            if(oldValue.isPresent()) {
                bus.post(new VertexPropertyUpdated.Builder<T>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .vertexId(getId())
                    .name(name)
                    .value(value)
                    .oldValue(oldValue.orElse(null))
                    .build());
            } else {
                bus.post(new VertexPropertyAdded.Builder<T>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .vertexId(getId())
                    .name(name)
                    .value(value)
                    .build());
            }
        }

        @Override
        public Vertex<T> removeProperty(String name) {
            var value = getProperty(name);
            var vertex = super.removeProperty(name);
            bus.post(new VertexPropertyRemoved.Builder<T>()
                .graphId(AbstractEventGraph.this.getId().orElse(null))
                .vertexId(getId())
                .name(name)
                .value(value)
                .build());
            return vertex;
        }
    }

    private EventBus bus;

    public AbstractEventGraph(EventBus bus) {
        this.bus = requireNonNull(bus, "bus must not be null.");
    }

    public AbstractEventGraph(T id, EventBus bus) {
        super(id);
        this.bus = requireNonNull(bus, "bus must not be null.");
    }

    public EventBus getBus() {
        return bus;
    }

    @Override
    public void setProperty(String name, Object value) {
        var oldValue = getProperty(name);
        super.setProperty(name, value);
        if(oldValue.isPresent()) {
            bus.post(new GraphPropertyUpdated.Builder<T>()
                .graphId(getId().orElse(null))
                .name(name).value(value)
                .oldValue(oldValue.orElse(null))
                .build());
        } else {
            bus.post(new GraphPropertyAdded.Builder<T>()
                .graphId(getId().orElse(null))
                .name(name)
                .value(value)
                .build());
        }
    }

    @Override
    public SimpleGraph<T> removeProperty(String name) {
        var value = getProperty(name);
        super.removeProperty(name);
        bus.post(new GraphPropertyRemoved.Builder<T>()
            .graphId(getId().orElse(null))
            .name(name)
            .value(value)
            .build());
        return this;
    }

    @Override
    Edge<T> addEdge(T from, T to) {
        var edge = super.addEdge(from, to);
        bus.post(new EdgeCreated.Builder<T>()
            .graphId(getId().orElse(null))
            .from(from)
            .to(to)
            .build());
        return edge;
    }

    @Override
    public void removeEdge(T from, T to) {
        var optional = findEdge(from, to);
        super.removeEdge(from, to);
        optional.ifPresent(edge -> bus.post(
            new EdgeRemoved.Builder<T>()
                .graphId(getId().orElse(null))
                .edgeId(edge.getId().orElse(null))
                .from(edge.getFrom())
                .to(edge.getTo())
                .build()));
    }

    @Override
    Vertex<T> addVertex(T id) {
        var vertex = super.addVertex(id);
        bus.post(new VertexCreated.Builder<T>()
            .graphId(getId().orElse(null))
            .vertexId(id)
            .build());
        return vertex;
    }

    @Override
    public void removeVertex(T id) {
        var optional = findVertex(id);
        super.removeVertex(id);
        optional.ifPresent(vertex -> bus.post(new VertexRemoved.Builder<T>()
            .graphId(getId().orElse(null))
            .vertexId(id)
            .build()));
    }
}
