package com.github.moaxcp.graphs.greenrobot;

import static com.github.moaxcp.graphs.newevents.Builders.*;
import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.element.*;
import com.github.moaxcp.graphs.event.*;
import java.util.*;
import java.util.stream.*;
import org.greenrobot.eventbus.*;

/**
 * Graph represents an undrected graph backed by an adjacency list. Methods in Graph provide the following guarentees:
 * <p>
 *     <ul>
 *         <li>The graph is always valid. All edges point to vertices within the graph.</li>
 *         <li>Never return null.</li>
 *         <li>Referencing missing elements will create them for most methods.</li>
 *         <li>Changes to the graph, vertices, or edges results in an event</li>
 *     </ul>
 */
public class UndirectedEventGraph extends OptionallyIdentifiedElement<UndirectedEventGraph> implements SimpleGraph {

    /**
     * Edge represents an undirected edge in this graph.
     */
    public class UndirectedEdge extends OptionallyIdentifiedInheritingElement<UndirectedEdge> implements Edge {
        protected UndirectedEdge(Object from, Object to, Map<String, Object> inherited, EventBus bus) {
            super(inherited, bus);
            super.setProperty("from", from);
            super.setProperty("to", to);
        }

        @Override
        protected UndirectedEdge self() {
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

        public Object from() {
            return getFrom();
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

        public Object to() {
            return getTo();
        }

        public Vertex fromVertex() {
            check();
            return vertex(getFrom());
        }

        public Vertex toVertex() {
            check();
            return vertex(getTo());
        }

        @Override
        public boolean isDirected() {
            return false;
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
        public UndirectedEdge removeProperty(String name) {
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
            return super.removeProperty(name);
        }

        public boolean equals(Object from, Object to) {
            return (Objects.equals(getFrom(), from) || Objects.equals(getFrom(), to)) && (Objects.equals(getTo(), to) || Objects.equals(getTo(), from));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UndirectedEdge)) {
                return false;
            }
            UndirectedEdge edge = (UndirectedEdge) obj;
            return equals(edge.getFrom(), edge.getTo());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFrom(), getTo());
        }

        @Override
        protected EdgePropertyAddedGraphEvent propertyAddedEvent(String name, Object value) {
            EdgePropertyAddedGraphEvent event = new EdgePropertyAddedGraphEvent().withGraph(UndirectedEventGraph.this).withEdge(this).withName(name).withValue(value);
            event.check();
            return event;
        }

        @Override
        protected EdgePropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value) {
            EdgePropertyRemovedGraphEvent event = new EdgePropertyRemovedGraphEvent().withGraph(UndirectedEventGraph.this).withEdge(this).withName(name).withValue(value);
            event.check();
            return event;
        }

        @Override
        protected EdgePropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue) {
            EdgePropertyUpdatedGraphEvent event = new EdgePropertyUpdatedGraphEvent().withGraph(UndirectedEventGraph.this).withEdge(this).withName(name).withValue(value).withOldValue(oldValue);
            event.check();
            return event;
        }
    }

    /**
     * Vertex represents a vertex in this graph.
     */
    public class UndirectedVertex extends IdentifiedInheritingElement<UndirectedVertex> implements Vertex {
        private UndirectedVertex(Object id, Map<String, Object> inherited, EventBus bus) {
            super(id, inherited, bus);
        }

        @Override
        protected UndirectedVertex self() {
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

        public Vertex connectsTo(Object to) {
            check();
            edge(getId(), to);
            return this;
        }

        public Vertex connectsFrom(Object s) {
            check();
            edge(s, getId());
            return this;
        }

        public Edge edgeTo(Object to) {
            check();
            return edge(getId(), to);
        }

        public Edge edgeFrom(Object from) {
            check();
            return edge(from, getId());
        }

        public Vertex toVertex(Object id) {
            check();
            return edgeTo(id).toVertex();
        }

        public Vertex fromVertex(Object id) {
            check();
            return edgeFrom(id).fromVertex();
        }

        @Override
        public Set<Edge> traverseEdges() {
            return adjacentEdges();
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
            return "Vertex '" + getId() + "' " + local().toString();
        }

        @Override
        public final boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof UndirectedVertex)) {
                return false;
            }
            UndirectedVertex vertex = (UndirectedVertex) obj;
            return Objects.equals(getId(), vertex.getId());
        }

        @Override
        public final int hashCode() {
            return Objects.hash(getId());
        }

        @Override
        protected VertexPropertyAddedGraphEvent propertyAddedEvent(String name, Object value) {
            VertexPropertyAddedGraphEvent event = new VertexPropertyAddedGraphEvent().withGraph(UndirectedEventGraph.this).withVertex(this).withName(name).withValue(value);
            event.check();
            return event;
        }

        @Override
        protected VertexPropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value) {
            VertexPropertyRemovedGraphEvent event = new VertexPropertyRemovedGraphEvent().withGraph(UndirectedEventGraph.this).withVertex(this).withName(name).withValue(value);
            event.check();
            return event;
        }

        @Override
        protected VertexPropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue) {
            VertexPropertyUpdatedGraphEvent event = new VertexPropertyUpdatedGraphEvent().withGraph(UndirectedEventGraph.this).withVertex(this).withName(name).withValue(value).withOldValue(oldValue);
            event.check();
            return event;
        }
    }

    private Map<Object, Vertex> vertices;
    private LinkedHashSet<Edge> edges;
    private LinkedHashMap<Object, Edge> edgeIds;
    private Map<String, Object> nodeProperties;
    protected Map<String, Object> edgeProperties;

    public UndirectedEventGraph() {
        this(EventBus.getDefault());
    }

    public UndirectedEventGraph(EventBus bus) {
        super(bus);
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
        edgeIds = new LinkedHashMap<>();
        nodeProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
        postCreatedEvent();
    }

    public UndirectedEventGraph(Object id) {
        super(EventBus.getDefault());
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
        edgeIds = new LinkedHashMap<>();
        nodeProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
        super.setProperty("id", Objects.requireNonNull(id));
        postCreatedEvent();
    }

    public UndirectedEventGraph(Object id, EventBus bus) {
        super(bus);
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
        edgeIds = new LinkedHashMap<>();
        nodeProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
        super.setProperty("id", Objects.requireNonNull(id));
        postCreatedEvent();
    }

    protected void postCreatedEvent() {
        getBus().post(graphCreated().graphId(getId().orElse(null)).build());
    }

    protected UndirectedEventGraph self() {
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

    @Override
    public SimpleGraph edgeProperty(String name, Object value) {
        return null;
    }

    @Override
    public SimpleGraph removeEdgeProperty(String name) {
        return null;
    }

    @Override
    public Optional<Object> getVertexProperty(String name) {
        return null;
    }

    @Override
    public void setVertexProperty(String name, Object value) {

    }

    @Override
    public SimpleGraph vertexProperty(String name, Object value) {
        return null;
    }

    @Override
    public SimpleGraph removeVertexProperty(String name) {
        return null;
    }

    void publish(GraphEvent event) {
        event.check();
        getBus().post(event);
    }

    public Optional<Vertex> findVertex(Object id) {
        return Optional.ofNullable(vertices.get(id));
    }

    public Vertex vertex(Object id) {
        var vertex = vertices.getOrDefault(id, new UndirectedVertex(id, nodeProperties, getBus()));
        if (!vertices.containsKey(id)) {
            vertices.put(id, vertex);
            publish(new VertexAddedGraphEvent().withGraph(this).withVertex(vertex));
        }
        return vertex;
    }

    public void removeVertex(Object id) {
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

    public Optional<Edge> findEdge(Object from, Object to) {
        return edges.stream()
                .filter(edge -> edge.equals(from, to))
                .findFirst();
    }

    public Optional<Edge> findEdge(Object id) {
        return Optional.ofNullable(edgeIds.get(id));
    }

    public Edge edge(Object from, Object to) {
        return findEdge(from, to).orElseGet(() -> addEdge(newEdge(from, to)));
    }

    Edge newEdge(Object from, Object to) {
        return new UndirectedEdge(from, to, edgeProperties, getBus());
    }

    private Edge addEdge(Edge edge) {
        vertex(edge.getFrom());
        vertex(edge.getTo());
        edges.add(edge);
        publish(new EdgeAddedGraphEvent().withGraph(this).withEdge(edge));
        return edge;
    }

    public void removeEdge(Object from, Object to) {
        findEdge(from, to).ifPresent(this::removeAndNotify);
    }

    @Override
    public void removeEdge(Object id) {

    }

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public Optional<Object> getEdgeProperty(String name) {
        return null;
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
