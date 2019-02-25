package com.github.moaxcp.graphs.greenrobot;

import static java.util.Objects.requireNonNull;
import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

public abstract class AbstractEventGraph<ID> extends AbstractGraph<ID> implements EventGraph<ID, EventBus> {

    public class EventEdge extends SimpleEdge {
        protected EventEdge(ID from, ID to, Map<String, Object> inherited) {
            super(from, to, inherited);
        }

        @Override
        public void setId(ID id) {
            var oldId = getId();
            if(id == null && !oldId.isPresent()) {
                return;
            }
            super.setId(id);
            if(id == null) {
                bus.post(new EdgeIdRemoved.Builder<ID>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .edgeId(oldId.orElse(null))
                    .from(getFrom())
                    .to(getTo())
                    .build());
                return;
            }
            if(oldId.isPresent()) {
                bus.post(new EdgeIdUpdated.Builder<ID>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .edgeId(id)
                    .oldEdgeId(oldId.orElse(null))
                    .from(getFrom())
                    .to(getTo())
                    .build());
            } else {
                bus.post(new EdgeIdAdded.Builder<ID>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .edgeId(id)
                    .from(getFrom())
                    .to(getTo())
                    .build());
            }
        }

        @Override
        public void setProperty(String name, Object value) {
            var oldValue = getProperty(name);
            super.setProperty(name, value);
            if(oldValue.isPresent()) {
                bus.post(new EdgePropertyUpdated.Builder<ID>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .edgeId(getId().orElse(null))
                    .from(getFrom())
                    .to(getTo())
                    .name(name)
                    .value(value)
                    .oldValue(oldValue.orElse(null))
                    .build());
            } else {
                bus.post(new EdgePropertyAdded.Builder<ID>()
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
        public Edge<ID> removeProperty(String name) {
            var value = getProperty(name);
            var edge = super.removeProperty(name);
            bus.post(new EdgePropertyRemoved.Builder<ID>()
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

    public class EventVertex extends SimpleVertex {
        protected EventVertex(ID id, Map<String, Object> inherited) {
            super(id, inherited);
        }

        @Override
        public void setId(ID id) {
            ID oldId = getId();
            super.setId(id);
            bus.post(new VertexIdUpdated.Builder<ID>()
                .graphId(AbstractEventGraph.this.getId().orElse(null))
                .oldVertexId(oldId)
                .vertexId(id)
                .build());
        }

        @Override
        public void setProperty(String name, Object value) {
            var oldValue = getProperty(name);
            super.setProperty(name, value);
            if(oldValue.isPresent()) {
                bus.post(new VertexPropertyUpdated.Builder<ID>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .vertexId(getId())
                    .name(name)
                    .value(value)
                    .oldValue(oldValue.orElse(null))
                    .build());
            } else {
                bus.post(new VertexPropertyAdded.Builder<ID>()
                    .graphId(AbstractEventGraph.this.getId().orElse(null))
                    .vertexId(getId())
                    .name(name)
                    .value(value)
                    .build());
            }
        }

        @Override
        public Vertex<ID> removeProperty(String name) {
            var value = getProperty(name);
            var vertex = super.removeProperty(name);
            bus.post(new VertexPropertyRemoved.Builder<ID>()
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

    public AbstractEventGraph(ID id, EventBus bus) {
        super(id);
        this.bus = requireNonNull(bus, "bus must not be null.");
    }

    public EventBus getBus() {
        return bus;
    }

    @Override
    public void setId(ID id) {
        var oldId = getId();
        super.setId(id);
        if(id == null && !oldId.isPresent()) {
            return;
        }
        if(id == null) {
            bus.post(new GraphIdRemoved.Builder<ID>()
                .graphId(oldId.orElse(null))
                .build());
            return;
        }
        if(oldId.isPresent()) {
            bus.post(new GraphIdUpdated.Builder<ID>()
                .graphId(id)
                .oldGraphId(oldId.orElse(null))
                .build());
        } else {
            bus.post(new GraphIdAdded.Builder<ID>()
                .graphId(id)
                .build());
        }
    }

    @Override
    public void setProperty(String name, Object value) {
        var oldValue = getProperty(name);
        super.setProperty(name, value);
        if(oldValue.isPresent()) {
            bus.post(new GraphPropertyUpdated.Builder<ID>()
                .graphId(getId().orElse(null))
                .name(name).value(value)
                .oldValue(oldValue.orElse(null))
                .build());
        } else {
            bus.post(new GraphPropertyAdded.Builder<ID>()
                .graphId(getId().orElse(null))
                .name(name)
                .value(value)
                .build());
        }
    }

    @Override
    public Graph<ID> removeProperty(String name) {
        var value = getProperty(name);
        super.removeProperty(name);
        bus.post(new GraphPropertyRemoved.Builder<ID>()
            .graphId(getId().orElse(null))
            .name(name)
            .value(value)
            .build());
        return this;
    }

    @Override
    protected Vertex<ID> newVertex(ID id, Map<String, Object> inherited) {
        return new EventVertex(id, inherited);
    }

    @Override
    protected Edge<ID> newEdge(ID from, ID to, Map<String, Object> inherited) {
        return new EventEdge(from, to, inherited);
    }

    @Override
    protected Edge<ID> addEdge(ID from, ID to) {
        var edge = super.addEdge(from, to);
        bus.post(new EdgeCreated.Builder<ID>()
            .graphId(getId().orElse(null))
            .from(from)
            .to(to)
            .build());
        return edge;
    }

    @Override
    public void removeEdge(ID from, ID to) {
        var optional = findEdge(from, to);
        super.removeEdge(from, to);
        optional.ifPresent(edge -> bus.post(
            new EdgeRemoved.Builder<ID>()
                .graphId(getId().orElse(null))
                .edgeId(edge.getId().orElse(null))
                .from(edge.getFrom())
                .to(edge.getTo())
                .build()));
    }

    @Override
    protected Vertex<ID> addVertex(ID id) {
        var vertex = super.addVertex(id);
        bus.post(new VertexCreated.Builder<ID>()
            .graphId(getId().orElse(null))
            .vertexId(id)
            .build());
        return vertex;
    }

    @Override
    public void removeVertex(ID id) {
        var optional = findVertex(id);
        super.removeVertex(id);
        optional.ifPresent(vertex -> bus.post(new VertexRemoved.Builder<ID>()
            .graphId(getId().orElse(null))
            .vertexId(id)
            .build()));
    }
}