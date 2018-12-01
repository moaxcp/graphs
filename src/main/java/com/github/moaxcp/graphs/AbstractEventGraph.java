package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.events.Builders.*;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

public abstract class AbstractEventGraph extends AbstractSimpleGraph {

    public abstract class EventEdge extends AbstractEdge {
        protected EventEdge(Object from, Object to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }
    }

    public abstract class EventVertex extends AbstractVertex {
        protected EventVertex(Object id, Map<String, Object> inherited) {
            super(id, inherited);
        }
    }

    private EventBus bus;

    public AbstractEventGraph() {
        this(EventBus.getDefault());
    }

    public AbstractEventGraph(EventBus bus) {
        this.bus = bus;
    }

    public AbstractEventGraph(Object id) {
        this(id, EventBus.getDefault());
    }

    public AbstractEventGraph(Object id, EventBus bus) {
        super(id);
        this.bus = bus;
    }

    protected EventBus getBus() {
        return bus;
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
