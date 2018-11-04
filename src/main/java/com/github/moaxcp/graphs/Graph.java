package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.element.IdentifiedInheritingElement;
import com.github.moaxcp.graphs.element.OptionallyIdentifiedElement;
import com.github.moaxcp.graphs.element.OptionallyIdentifiedInheritingElement;
import com.github.moaxcp.graphs.event.*;
import org.greenrobot.eventbus.EventBus;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.moaxcp.graphs.newevents.Builders.graphCreated;

/**
 * Graph represents an undrected graph backed by an adjacency list. Methods in Graph provide the following guarentees:
 * <p>
 *     <ul>
 *         <li>The graph is always valid. All edges point to vertices within the graph.</li>
 *         <li>Never return null.</li>
 *         <li>Referencing missing elements will create them for most methods.</li>
 *         <li>Changes to the graph, vertices, or edges results in an event</li>
 *     </ul>
 * </p>
 */
public class Graph extends OptionallyIdentifiedElement<Graph> {

    /**
     * Edge represents an undirected edge in this graph.
     */
    public class Edge extends OptionallyIdentifiedInheritingElement<Edge> {
        protected Edge(Object from, Object to, Map<String, Object> inherited, EventBus bus) {
            super(inherited, bus);
            local.put("from", from);
            local.put("to", to);
        }

        @Override
        protected Edge self() {
            return this;
        }

        private void check() {
            if(!edges.contains(this)) {
                throw new IllegalStateException("Edge is not in graph.");
            }
        }

        @Override
        public void setId(Object id) {
            check();
            getId().ifPresent(edgeIds::remove);
            if(id != null) {
                edgeIds.put(id, this);
            }
            super.setId(id);
        }

        /**
         * Returns id of "from" {@link Vertex}.
         * @return id of "from" {@link Vertex}
         */
        public Object getFrom() {
            return getProperty("from").get();
        }

        /**
         * Sets "from" {@link Vertex} to vertex with id. If vertex does not exist it is created.
         * @param from
         */
        public void setFrom(Object from) {
            check();
            Objects.requireNonNull(from);
            edges.remove(this);
            vertex(from);
            super.setProperty("from", from);
            edges.add(this);
        }

        /**
         * Sets "from" {@link Vertex} to vertex with id returning this {@link Edge}. If vertex does not exist it is
         * created.
         * @param from {@link Vertex} id for "from" vertex
         * @return this edge
         */
        public Edge from(Object from) {
            setFrom(from);
            return this;
        }

        /**
         * Returns id of "to" {@link Vertex}.
         * @return id of "to" {@link Vertex}
         */
        public Object getTo() {
            return getProperty("to").get();
        }

        /**
         * Sets "to" {@link Vertex} to vertex with id. If vertex does not exist it is created.
         * @param to {@link Vertex} id for "to" vertex
         */
        public void setTo(Object to) {
            check();
            Objects.requireNonNull(to);
            edges.remove(this);
            vertex(to);
            super.setProperty("to", to);
            edges.add(this);
        }

        /**
         * Sets "to" {@link Vertex} to vertex with id returning this {@link Edge}. If vertex does not exist it is
         * created.
         * @param to {@link Vertex} id for "to" vertex
         * @return this edge
         */
        public Edge to(Object to) {
            setTo(to);
            return this;
        }

        public Vertex from() {
            check();
            return vertex(getFrom());
        }

        public Vertex to() {
            check();
            return vertex(getTo());
        }

        @Override
        public void setProperty(String name, Object value) {
            check();
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
            check();
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

    /**
     * Vertex represents a vertex in this graph.
     */
    public class Vertex extends IdentifiedInheritingElement<Vertex> {
        private Vertex(Object id, Map<String, Object> inherited, EventBus bus) {
            super(id, inherited, bus);
        }

        @Override
        protected Vertex self() {
            return this;
        }

        private void check() {
            if(!vertices.containsKey(getId())) {
                throw new IllegalStateException("Vertex is not in graph.");
            }
        }

        @Override
        public void setId(Object id) {
            check();
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

        @Override
        public void setProperty(String name, Object value) {
            check();
            super.setProperty(name, value);
        }

        public Vertex connectsTo(String to) {
            check();
            edge(getId(), to);
            return this;
        }

        public Vertex connectsFrom(String s) {
            check();
            edge(s, getId());
            return this;
        }

        public Edge edgeTo(String id) {
            check();
            return edge(getId(), id);
        }

        public Edge edgeFrom(String id) {
            check();
            return edge(id, getId());
        }

        public Vertex toVertex(String id) {
            check();
            return edgeTo(id).to();
        }

        public Vertex fromVertex(String id) {
            check();
            return edgeFrom(id).from();
        }

        public Set<Edge> adjacentEdges() {
            check();
            return edges.stream()
                    .filter(edge -> getId().equals(edge.getFrom()) || getId().equals(edge.getTo()))
                    .collect(Collectors.toSet());
        }

        public Set<Edge> inEdges() {
            check();
            return edges.stream()
                    .filter(edge -> getId().equals(edge.getTo()))
                    .collect(Collectors.toSet());
        }

        public Set<Edge> outEdges() {
            check();
            return edges.stream()
                    .filter(edge -> getId().equals(edge.getFrom()))
                    .collect(Collectors.toSet());
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
    protected Map<String, Object> edgeProperties;

    public Graph() {
        this(EventBus.getDefault());
    }

    public Graph(EventBus bus) {
        super(bus);
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
        edgeIds = new LinkedHashMap<>();
        nodeProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
        bus.post(graphCreated().build());
    }

    public Graph(Object id) {
        super(EventBus.getDefault());
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
        edgeIds = new LinkedHashMap<>();
        nodeProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
        local.put("id", Objects.requireNonNull(id));
        bus.post(graphCreated().id(id).build());
    }

    public Graph(Object id, EventBus bus) {
        this(bus);
        local.put("id", Objects.requireNonNull(id));
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
        var optional = findVertex(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("vertex '" + id + "' not found.");
        }
        var adjacent = optional.get().adjacentEdges();
        for (var edge : adjacent) {
            removeEdge(edge.getFrom(), edge.getTo());
        }
        Vertex removed = vertices.remove(id);
        publish(new VertexRemovedGraphEvent().withGraph(this).withVertex(removed));
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
        var search = newEdge(from, to);
        return findEdge(search);
    }

    public Optional<Edge> findEdge(Object id) {
        return Optional.ofNullable(edgeIds.get(id));
    }

    public Edge edge(Object from, Object to) {
        return findEdge(from, to).orElseGet(() -> addEdge(newEdge(from, to)));
    }

    Edge newEdge(Object from, Object to) {
        return new Edge(from, to, edgeProperties, getBus());
    }

    private Edge addEdge(Edge edge) {
        vertex(edge.getFrom());
        vertex(edge.getTo());
        edges.add(edge);
        publish(new EdgeAddedGraphEvent().withGraph(this).withEdge(edge));
        return edge;
    }

    public void removeEdge(Object from, Object to) {
        var search = newEdge(from, to);
        findEdge(search).ifPresent(this::removeAndNotify);
    }

    private void removeAndNotify(Edge edge) {
        edges.remove(edge);
        publish(new EdgeRemovedGraphEvent().withGraph(this).withEdge(edge));
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
