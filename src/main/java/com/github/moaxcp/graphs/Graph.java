package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.*;
import org.greenrobot.eventbus.EventBus;

import java.util.*;
import java.util.stream.Collectors;

public class Graph extends OptionallyIdentifiedElement<Graph> {

    public class Edge extends OptionallyIdentifiedInheritingElement<Edge> {
        private Edge(Object from, Object to, Map<String, Object> inherited, EventBus bus) {
            super(inherited, bus);
            local.put("from", from);
            local.put("to", to);
        }

        @Override
        protected Edge self() {
            return this;
        }

        @Override
        public void setId(Object id) {
            getId().ifPresent(edgeIds::remove);
            if(id != null) {
                edgeIds.put(id, this);
            }
            super.setId(id);
        }

        public Object getFrom() {
            return getProperty("from").get();
        }

        public void setFrom(Object from) {
            Objects.requireNonNull(from);
            edges.remove(this);
            vertex(from);
            super.setProperty("from", from);
            edges.add(this);
        }

        public void setTo(Object to) {
            Objects.requireNonNull(to);
            edges.remove(this);
            vertex(to);
            super.setProperty("to", to);
            edges.add(this);
        }

        public Object getTo() {
            return getProperty("to").get();
        }

        public Vertex from() {
            return vertex(getFrom());
        }

        public Vertex to() {
            return vertex(getTo());
        }

        @Override
        public void setProperty(String name, Object value) {
            if ("from".equals(name)) {
                setFrom((value));
                return;
            }
            if ("to".equals(name)) {
                setTo((value));
                return;
            }
            super.setProperty(name, value);
        }

        @Override
        public void removeProperty(String name) {
            if("id".equals(name)) {
                getId().ifPresent(edgeIds::remove);
            }
            if ("from".equals(name)) {
                throw new IllegalArgumentException("'from' can not be removed.");
            }
            if ("to".equals(name)) {
                throw new IllegalArgumentException("'to' can not be removed.");
            }
            super.removeProperty(name);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Graph.Edge)) {
                return false;
            }
            Edge edge = (Edge) obj;
            return (Objects.equals(getFrom(), edge.getFrom()) || Objects.equals(getFrom(), edge.getTo())) && (Objects.equals(getTo(), edge.getTo()) || Objects.equals(getTo(), edge.getFrom()));
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFrom(), getTo());
        }

        @Override
        protected EdgePropertyAddedGraphEvent propertyAddedEvent(String name, Object value) {
            EdgePropertyAddedGraphEvent event = new EdgePropertyAddedGraphEvent().withGraph(Graph.this).withEdge(this).withName(name).withValue(value);
            event.check();
            return event;
        }

        @Override
        protected EdgePropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value) {
            EdgePropertyRemovedGraphEvent event = new EdgePropertyRemovedGraphEvent().withGraph(Graph.this).withEdge(this).withName(name).withValue(value);
            event.check();
            return event;
        }

        @Override
        protected EdgePropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue) {
            EdgePropertyUpdatedGraphEvent event = new EdgePropertyUpdatedGraphEvent().withGraph(Graph.this).withEdge(this).withName(name).withValue(value).withOldValue(oldValue);
            event.check();
            return event;
        }
    }

    public class Vertex extends IdentifiedInheritingElement<Vertex> {
        private Vertex(Object id, Map<String, Object> inherited, EventBus bus) {
            super(id, inherited, bus);
        }

        @Override
        protected Vertex self() {
            return this;
        }

        @Override
        public void setId(Object id) {
            Objects.requireNonNull(id, "id must not be null.");
            Set<? extends Edge> adjacent = adjacentEdges();
            Object oldId = getId();
            vertices.remove(this.getId());
            super.setId(id);
            vertices.put(id, this);
            for (Edge edge : adjacent) {
                if (edge.getFrom().equals(oldId)) {
                    edge.setFrom(id);
                }
                if (edge.getTo().equals(oldId)) {
                    edge.setTo(id);
                }
            }

        }

        public Set<? extends Edge> adjacentEdges() {
            return Graph.this.adjacentEdges(getId());
        }

        public Vertex connectsTo(String to) {
            edge(getId(), to);
            return this;
        }

        public Vertex connectsFrom(String s) {
            edge(s, getId());
            return this;
        }

        public Edge edgeTo(String id) {
            return edge(getId(), id);
        }

        public Edge edgeFrom(String id) {
            return edge(id, getId());
        }

        public Vertex toVertex(String id) {
            return edgeTo(id).to();
        }

        public Vertex fromVertex(String id) {
            return edgeFrom(id).from();
        }

        public final String toString() {
            return "Vertex '" + getId() + "' " + local.toString();
        }

        @Override
        public final boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof Graph.Vertex)) {
                return false;
            }
            Vertex vertex = (Vertex) obj;
            return Objects.equals(getId(), vertex.getId());
        }

        @Override
        public final int hashCode() {
            return Objects.hash(getId());
        }

        @Override
        protected VertexPropertyAddedGraphEvent propertyAddedEvent(String name, Object value) {
            VertexPropertyAddedGraphEvent event = new VertexPropertyAddedGraphEvent().withGraph(Graph.this).withVertex(this).withName(name).withValue(value);
            event.check();
            return event;
        }

        @Override
        protected VertexPropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value) {
            VertexPropertyRemovedGraphEvent event = new VertexPropertyRemovedGraphEvent().withGraph(Graph.this).withVertex(this).withName(name).withValue(value);
            event.check();
            return event;
        }

        @Override
        protected VertexPropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue) {
            VertexPropertyUpdatedGraphEvent event = new VertexPropertyUpdatedGraphEvent().withGraph(Graph.this).withVertex(this).withName(name).withValue(value).withOldValue(oldValue);
            event.check();
            return event;
        }
    }

    private Map<Object, Vertex> vertices;
    private LinkedHashSet<Edge> edges;
    private LinkedHashMap<Object, Edge> edgeIds;
    private Map<String, Object> nodeProperties;
    private Map<String, Object> edgeProperties;

    public Graph() {
        super(EventBus.getDefault());
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
        edgeIds = new LinkedHashMap<>();
        nodeProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
    }

    public Graph(String id) {
        this();
        Objects.requireNonNull(id);
        local.put("id", id);
    }

    protected Graph self() {
        return this;
    }

    public Map<Object, Vertex> getVertices() {
        return Collections.unmodifiableMap(vertices);
    }

    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    public Map<Object, Edge> getEdgeIds() { return Collections.unmodifiableMap(edgeIds); }

    public void setNodeProperty(String key, Object value) {
        nodeProperties.put(key, value);
    }

    public void setEdgeProperty(String key, Object value) {
        edgeProperties.put(key, value);
    }

    void publish(GraphEvent event) {
        event.check();
        getBus().post(event);
    }

    public Optional<Vertex> findVertex(Object id) {
        return Optional.ofNullable(vertices.get(id));
    }

    public Vertex vertex(Object id) {
        var vertex = vertices.getOrDefault(id, new Vertex(id, nodeProperties, getBus()));
        if (!vertices.containsKey(id)) {
            vertices.put(id, vertex);
            publish(new VertexAddedGraphEvent().withGraph(this).withVertex(vertex));
        }
        return vertex;
    }

    public void removeVertex(String id) {
        Objects.requireNonNull(id);
        if (!vertices.containsKey(id)) {
            throw new IllegalArgumentException("vertex '" + id + "' not found.");
        }
        var adjacent = adjacentEdges(id);
        for (var edge : adjacent) {
            removeEdge(edge.getFrom(), edge.getTo());
        }
        Vertex removed = vertices.remove(id);
        publish(new VertexRemovedGraphEvent().withGraph(this).withVertex(removed));
    }

    public void removeEdge(Object from, Object to) {
        var search = new Edge(from, to, edgeProperties, getBus());
        findEdge(search).ifPresent(this::removeAndNotify);
    }

    private void removeAndNotify(Edge edge) {
        edges.remove(edge);
        publish(new EdgeRemovedGraphEvent().withGraph(this).withEdge(edge));
    }

    private Optional<Edge> findEdge(Edge search) {
        if (!edges.contains(search)) {
            return Optional.empty();
        }
        return edges.stream()
                .filter(search::equals)
                .findFirst();
    }

    public Optional<Edge> findEdge(Object from, Object to) {
        var search = new Edge(from, to, edgeProperties, getBus());
        return findEdge(search);
    }

    public Edge edge(Object from, Object to) {
        return findEdge(from, to).orElseGet(() -> addEdge(new Edge(from, to, edgeProperties, getBus())));
    }

    public Optional<Edge> edge(Object id) {
        return Optional.ofNullable(edgeIds.get(id));
    }

    private Edge addEdge(Edge edge) {
        vertex(edge.getFrom());
        vertex(edge.getTo());
        edges.add(edge);
        publish(new EdgeAddedGraphEvent().withGraph(this).withEdge(edge));
        return edge;
    }

    public Set<? extends Edge> adjacentEdges(Object id) {
        return edges.stream()
                .filter(edge -> id.equals(edge.getFrom()) || id.equals(edge.getTo()))
                .collect(Collectors.toSet());
    }

    @Override
    protected GraphPropertyAddedGraphEvent propertyAddedEvent(String name, Object value) {
        GraphPropertyAddedGraphEvent event = new GraphPropertyAddedGraphEvent().withGraph(this).withName(name).withValue(value);
        event.check();
        return event;
    }

    @Override
    protected GraphPropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value) {
        GraphPropertyRemovedGraphEvent event = new GraphPropertyRemovedGraphEvent().withGraph(this).withName(name).withValue(value);
        event.check();
        return event;
    }

    @Override
    protected GraphPropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue) {
        GraphPropertyUpdatedGraphEvent event = new GraphPropertyUpdatedGraphEvent().withGraph(this).withName(name).withValue(value).withOldValue(oldValue);
        event.check();
        return event;
    }
}
