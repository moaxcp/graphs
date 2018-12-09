package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.events.Builders.*;
import static java.util.Objects.requireNonNull;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

public abstract class AbstractEventGraph extends AbstractSimpleGraph implements SimpleEventGraph {

    public abstract class EventEdge extends AbstractEdge {
        protected EventEdge(Object from, Object to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public void setProperty(String name, Object value) {
            var oldValue = getProperty(name);
            super.setProperty(name, value);
            if(oldValue.isPresent()) {
                bus.post(edgePropertyUpdated().graphId(AbstractEventGraph.this.getId().orElse(null)).edgeId(getId().orElse(null)).from(getFrom()).to(getTo()).name(name).value(value).oldValue(oldValue.orElse(null)).build());
            } else {
                bus.post(edgePropertyAdded().graphId(AbstractEventGraph.this.getId().orElse(null)).edgeId(getId().orElse(null)).from(getFrom()).to(getTo()).name(name).value(value).build());
            }
        }

        @Override
        public Edge removeProperty(String name) {
            var value = getProperty(name);
            var edge = super.removeProperty(name);
            bus.post(edgePropertyRemoved()
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
        protected EventVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }
    }

    private EventBus bus;

    public AbstractEventGraph(EventBus bus) {
        this.bus = requireNonNull(bus, "bus must not be null.");
    }

    public AbstractEventGraph(Object id, EventBus bus) {
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
            bus.post(graphPropertyUpdated().graphId(getId().orElse(null)).name(name).value(value).oldValue(oldValue.orElse(null)).build());
        } else {
            bus.post(graphPropertyAdded().graphId(getId().orElse(null)).name(name).value(value).build());
        }
    }

    @Override
    public SimpleGraph removeProperty(String name) {
        var value = getProperty(name);
        super.removeProperty(name);
        bus.post(graphPropertyRemoved().graphId(getId().orElse(null)).name(name).value(value).build());
        return this;
    }

    @Override
    Vertex addVertex(Object id) {
        var vertex = super.addVertex(id);
        bus.post(vertexCreated().graphId(getId().orElse(null)).vertexId(id).build());
        return vertex;
    }

    @Override
    Edge addEdge(Object from, Object to) {
        var edge = super.addEdge(from, to);
        bus.post(edgeCreated().graphId(getId().orElse(null)).from(from).to(to).build());
        return edge;
    }

    @Override
    public void removeEdge(Object from, Object to) {
        var optional = findEdge(from, to);
        super.removeEdge(from, to);
        optional.ifPresent(edge -> bus.post(
                edgeRemoved()
                        .graphId(getId().orElse(null))
                        .edgeId(edge.getId().orElse(null))
                        .from(edge.getFrom())
                        .to(edge.getTo()).build()));
    }
}
