package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.*;
import org.greenrobot.eventbus.EventBus;

import java.util.*;
import java.util.stream.Collectors;

public class Graph extends OptionallyIdentifiedElement {

    public class Edge extends InheritingElement {
        private Edge(String from, String to, Map<String, Object> inherited, EventBus bus) {
            super(inherited, bus);
            local.put("from", from);
            local.put("to", to);
        }

        public String getFrom() {
            return (String) getProperty("from");
        }

        public void setFrom(String from) {
            Objects.requireNonNull(from);
            edges.remove(this);
            vertex(from);
            super.setProperty("from", from);
            edges.add(this);
        }

        public void setTo(String to) {
            Objects.requireNonNull(to);
            edges.remove(this);
            vertex(to);
            super.setProperty("to", to);
            edges.add(this);
        }

        public String getTo() {
            return (String) getProperty("to");
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
                setFrom((String) value);
                return;
            }
            if ("to".equals(name)) {
                setTo((String) value);
                return;
            }
            super.setProperty(name, value);
        }

        @Override
        public Edge withProperty(String name, Object value) {
            setProperty(name, value);
            return this;
        }

        @Override
        public void removeProperty(String name) {
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

    public class Vertex extends IdentifiedInheritingElement {
        private Vertex(String id, Map<String, Object> inherited, EventBus bus) {
            super(id, inherited, bus);
        }

        @Override
        public void setId(String id) {
            Objects.requireNonNull(id, "id must not be null.");
            Set<? extends Edge> adjacent = adjacentEdges();
            String oldId = getId();
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
        public Vertex withProperty(String name, Object value) {
            setProperty(name, value);
            return this;
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

    private Map<String, Vertex> vertices;
    private LinkedHashSet<Edge> edges;
    private Map<String, Object> nodeProperties;
    private Map<String, Object> edgeProperties;

    public Graph() {
        super(EventBus.getDefault());
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
        nodeProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
    }

    public Graph(String id) {
        this();
        Objects.requireNonNull(id);
        local.put("id", id);
    }

    public Map<String, Vertex> getVertices() {
        return Collections.unmodifiableMap(vertices);
    }

    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

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

    public Vertex vertex(String id) {
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

    public void removeEdge(String from, String to) {
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

    public Edge edge(String from, String to) {
        var search = new Edge(from, to, edgeProperties, getBus());
        return findEdge(search).orElseGet(() -> addEdge(search));
    }

    private Edge addEdge(Edge edge) {
        vertex(edge.getFrom());
        vertex(edge.getTo());
        edges.add(edge);
        publish(new EdgeAddedGraphEvent().withGraph(this).withEdge(edge));
        return edge;
    }

    public Set<? extends Edge> adjacentEdges(String id) {
        return edges.stream()
                .filter(edge -> id.equals(edge.getFrom()) || id.equals(edge.getTo()))
                .collect(Collectors.toSet());
    }

    @Override
    public Graph withProperty(String name, Object value) {
        setProperty(name, value);
        return this;
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
