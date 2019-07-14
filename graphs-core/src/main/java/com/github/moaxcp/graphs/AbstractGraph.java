package com.github.moaxcp.graphs;

import java.util.*;

import static java.util.Collections.*;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;

/**
 * This class provides a partial implementation of the {@link Graph} interface.
 *
 * <p>Vertices and edges are stored in insertion order.</p>
 * @param <ID> type of all identifiers in graph
 */
public abstract class AbstractGraph<ID> implements Graph<ID> {

    private static final String NAME_MUST_NOT_BE_NULL = "name must not be null.";
    private static final String VALUE_MUST_NOT_BE_NULL = "value must not be null.";
    private static final String NAME_MUST_NOT_BE_EMPTY = "name must not be empty.";
    private static final String ID_MUST_NOT_BE_NULL = "id must not be null.";

    public class SimpleEdge extends InheritingElement<Edge<ID>> implements Edge<ID> {
        private ID id;
        private ID from;
        private ID to;

        protected SimpleEdge(ID from, ID to, Map<String, Object> inherited) {
            super(inherited);
            this.from = from;
            this.to = to;
        }

        private void check() {
            EdgeKey<ID> key = newEdgeKey(from, to);
            if(!edges.keySet().contains(key)) {
                throw new IllegalStateException("Edge is not in graph.");
            }
        }

        @Override
        public final Optional<ID> getId() {
            return Optional.ofNullable(id);
        }

        @Override
        public void setId(ID id) {
            check();
            edgeIds.remove(this.id);
            if(id != null) {
                edgeIds.put(id, this);
            }
            this.id = id;
        }

        @Override
        public final Edge<ID> id(ID id) {
            setId(id);
            return self();
        }

        @Override
        public final ID getFrom() {
            return from;
        }

        @Override
        public void setFrom(ID from) {
            check();
            requireNonNull(from, "from must not be null.");
            EdgeKey<ID> oldKey = newEdgeKey(this.from, this.to);
            edges.remove(oldKey);
            adjacentEdges.get(this.from).remove(this);
            inEdges.get(to).remove(this);
            outEdges.get(this.from).remove(this);
            vertex(from);
            adjacentEdges.compute(from, (k, v) -> mergeSet(this, v));
            inEdges.compute(to, (k, v) -> mergeSet(this, v));
            outEdges.compute(from, (k, v) -> mergeSet(this, v));
            this.from = from;
            EdgeKey<ID> key = newEdgeKey(this.from, this.to);
            edges.put(key, this);
        }

        @Override
        public Edge<ID> from(ID from) {
            setFrom(from);
            return this;
        }

        @Override
        public final ID from() {
            return getFrom();
        }

        @Override
        public final ID getTo() {
            return to;
        }

        @Override
        public void setTo(ID to) {
            check();
            Objects.requireNonNull(to, "to must not be null.");
            EdgeKey<ID> oldKey = newEdgeKey(this.from, this.to);
            edges.remove(oldKey);
            adjacentEdges.get(this.to).remove(this);
            inEdges.get(this.to).remove(this);
            outEdges.get(this.from).remove(this);
            vertex(to);
            adjacentEdges.compute(to, (k, v) -> mergeSet(this, v));
            inEdges.compute(to, (k, v) -> mergeSet(this, v));
            outEdges.compute(from, (k, v) -> mergeSet(this, v));
            this.to = to;
            EdgeKey<ID> key = newEdgeKey(this.from, this.to);
            edges.put(key, this);
        }

        @Override
        public final Edge<ID> to(ID to) {
            setTo(to);
            return this;
        }

        @Override
        public final ID to() {
            return getTo();
        }

        @Override
        public final boolean isDirected() {
            return AbstractGraph.this.isDirected();
        }

        @Override
        public final List<ID> endpoints() {
            return List.of(from, to);
        }

        @Override
        public final Vertex<ID> fromVertex() {
            check();
            return vertex(getFrom());
        }

        @Override
        public final Vertex<ID> toVertex() {
            check();
            return vertex(getTo());
        }

        @Override
        public void setProperty(String name, Object value) {
            check();
            super.setProperty(name, value);
        }

        @Override
        public Edge<ID> removeProperty(String name) {
            check();
            return super.removeProperty(name);
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AbstractGraph<?>.SimpleEdge)) return false;
            SimpleEdge that = (SimpleEdge) o;
            return Objects.equals(id, that.id) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(local(), that.local()) &&
                Objects.equals(inherited(), that.inherited());
        }

        @Override
        public final int hashCode() {
            return Objects.hash(id, from, to, local(), inherited());
        }
    }

    public class SimpleVertex extends InheritingElement<Vertex<ID>> implements Vertex<ID> {
        private ID id;

        protected SimpleVertex(ID id, Map<String, Object> inherited) {
            super(inherited);
            Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
            this.id = id;
        }

        private void check() {
            if(!vertices.containsKey(getId())) {
                throw new IllegalStateException("Vertex is not in graph.");
            }
        }

        public ID getId() {
            return id;
        }

        @Override
        public void setId(ID id) {
            check();
            Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
            if(findVertex(id).isPresent()) {
                throw new IllegalArgumentException("vertex with id a already exists.");
            }
            Set<? extends Edge<ID>> adjacent = new LinkedHashSet<>(adjacentEdges());
            Object oldId = getId();
            vertices.remove(this.getId());
            this.id = id;
            vertices.put(id, this);
            for (Edge<ID> edge : adjacent) {
                if (edge.getFrom().equals(oldId)) {
                    edge.setFrom(id);
                }
                if (edge.getTo().equals(oldId)) {
                    edge.setTo(id);
                }
            }
        }

        @Override
        public Vertex<ID> id(ID id) {
            setId(id);
            return self();
        }

        @Override
        public void setProperty(String name, Object value) {
            check();
            super.setProperty(name, value);
        }

        @Override
        public Vertex<ID> removeProperty(String name) {
            check();
            super.removeProperty(name);
            return self();
        }

        @Override
        public Vertex<ID> connectsTo(ID to) {
            check();
            edge(getId(), to);
            return this;
        }

        @Override
        public Vertex<ID> connectsFrom(ID s) {
            check();
            edge(s, getId());
            return this;
        }

        @Override
        public Set<Edge<ID>> adjacentEdges() {
            check();
            Set<Edge<ID>> edges = adjacentEdges.get(id);
            if(edges == null) {
                return emptySet();
            }
            return unmodifiableSet(edges);
        }

        @Override
        public Set<Edge<ID>> inEdges() {
            check();
            Set<Edge<ID>> edges = inEdges.get(id);
            if(edges == null) {
                return emptySet();
            }
            return unmodifiableSet(edges);
        }

        @Override
        public Set<Edge<ID>> outEdges() {
            check();
            Set<Edge<ID>> edges = outEdges.get(id);
            if(edges == null) {
                return emptySet();
            }
            return unmodifiableSet(edges);
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AbstractGraph<?>.SimpleVertex)) return false;
            SimpleVertex that = (SimpleVertex) o;
            return Objects.equals(id, that.id) &&
                Objects.equals(local(), that.local()) &&
                Objects.equals(inherited(), that.inherited());
        }

        @Override
        public final int hashCode() {
            return Objects.hash(id, local(), inherited());
        }
    }


    private ID id;
    private Map<String, Object> properties;
    private Map<String, Object> vertexProperties;
    private Map<String, Object> edgeProperties;
    private Map<ID, Vertex<ID>> vertices;
    private Map<EdgeKey<ID>, Edge<ID>> edges;
    private Map<ID, Edge<ID>> edgeIds;
    private Map<ID, Set<Edge<ID>>> adjacentEdges;
    private Map<ID, Set<Edge<ID>>> inEdges;
    private Map<ID, Set<Edge<ID>>> outEdges;

    protected AbstractGraph() {
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashMap<>();
        edgeIds = new LinkedHashMap<>();
        adjacentEdges = new LinkedHashMap<>();
        inEdges = new LinkedHashMap<>();
        outEdges = new LinkedHashMap<>();
        vertexProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
        properties = new LinkedHashMap<>();
    }

    protected AbstractGraph(ID id) {
        this();
        this.id = id;
    }

    @Override
    public Map<ID, Vertex<ID>> getVertices() {
        return unmodifiableMap(vertices);
    }

    @Override
    public Collection<Edge<ID>> getEdges() {
        return Collections.unmodifiableCollection(edges.values());
    }

    @Override
    public Map<ID, Edge<ID>> getEdgeIds() { return unmodifiableMap(edgeIds); }

    @Override
    public Optional<Vertex<ID>> findVertex(ID id) {
        return Optional.ofNullable(vertices.get(id));
    }

    @Override
    public Vertex<ID> vertex(ID id) {
        return findVertex(id).orElseGet(() -> addVertex(id));
    }

    protected Vertex<ID> addVertex(ID id) {
        var vertex = newVertex(id, vertexProperties);
        vertices.put(id, vertex);
        return vertex;
    }

    @Override
    public void removeVertex(ID id) {
        Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
        var optional = findVertex(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("vertex '" + id + "' not found.");
        }
        var adjacent = optional.get().adjacentEdges().stream()
            .map(e-> newEdgeKey(e.getFrom(), e.getTo()))
            .collect(toSet());
        for (var edge : adjacent) {
            removeEdge(edge.getFrom(), edge.getTo());
        }
        vertices.remove(id);
    }

    @Override
    public Optional<Edge<ID>> findEdge(ID from, ID to) {
        requireNonNull(from, "from must not be null.");
        requireNonNull(to, "to must not be null.");
        var key = newEdgeKey(from, to);
        var edge = edges.get(key);
        return Optional.ofNullable(edge);
    }

    @Override
    public Optional<Edge<ID>> findEdge(ID id) {
        requireNonNull(id, "id must not be null.");
        return Optional.ofNullable(edgeIds.get(id));
    }

    @Override
    public Edge<ID> edge(ID from, ID to) {
        return findEdge(from, to).orElseGet(() -> addEdge(from, to));
    }

    protected Edge<ID> newEdge(ID from, ID to, Map<String, Object> inherited) {
        return new SimpleEdge(from, to, inherited);
    }

    protected abstract EdgeKey<ID> newEdgeKey(ID from, ID to);

    protected Vertex<ID> newVertex(ID id, Map<String, Object> inherited) {
        return new SimpleVertex(id, inherited);
    }

    protected Edge<ID> addEdge(ID from, ID to) {
        vertex(from);
        vertex(to);
        var edge = newEdge(from, to, edgeProperties);
        var edgeKey = newEdgeKey(from, to);
        edges.put(edgeKey, edge);
        adjacentEdges.compute(edgeKey.getFrom(), (k, v) -> mergeSet(edge, v));
        adjacentEdges.compute(edgeKey.getTo(), (k, v) -> mergeSet(edge, v));
        inEdges.compute(edgeKey.getTo(), (k, v) -> mergeSet(edge, v));
        outEdges.compute(edgeKey.getFrom(), (k, v) -> mergeSet(edge, v));
        return edge;
    }

    private Set<Edge<ID>> mergeSet(Edge<ID> edge, Set<Edge<ID>> set) {
        if(set == null) {
            set = new LinkedHashSet<>();
            set.add(edge);
        }
        else {
            set.add(edge);
        }
        return set;
    }

    @Override
    public void removeEdge(ID from, ID to) {
        requireNonNull(from, "from must not be null.");
        requireNonNull(to, "to must not be null.");
        EdgeKey<ID> key = newEdgeKey(from, to);
        var edge = edges.remove(key);
        if(edge == null) {
            throw new IllegalArgumentException("edge from '" + from + "' to '" + to + "' not found.");
        }
        edge.getId().ifPresent(edgeIds::remove);
        var fromSet = adjacentEdges.get(from);
        fromSet.remove(edge);
        if(fromSet.isEmpty()) {
            adjacentEdges.remove(from);
        }
        var toSet = adjacentEdges.get(to);
        toSet.remove(edge);
        if(toSet.isEmpty()) {
            adjacentEdges.remove(to);
        }
        var outSet = outEdges.get(from);
        outSet.remove(edge);
        if(outSet.isEmpty()) {
            outEdges.remove(from);
        }
        var inSet = inEdges.get(to);
        inSet.remove(edge);
        if(inSet.isEmpty()) {
            inEdges.remove(to);
        }
    }

    @Override
    public void removeEdge(ID id) {
        requireNonNull(id, ID_MUST_NOT_BE_NULL);
        var optional = findEdge(id);
        optional.ifPresent(edge -> removeEdge(edge.getFrom(), edge.getTo()));
        if(!optional.isPresent()) {
            throw new IllegalArgumentException("edge with id '" + id + "' not found.");
        }
    }

    @Override
    public Optional<ID> getId() {
        return Optional.ofNullable(id);
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public Graph<ID> id(ID id) {
        setId(id);
        return this;
    }

    @Override
    public Map<String, Object> getProperties() {
        return unmodifiableMap(properties);
    }

    @Override
    public Optional<Object> getProperty(String name) {
        return Optional.ofNullable(properties.get(name));
    }

    @Override
    public void setProperty(String name, Object value) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requireNonNull(value, VALUE_MUST_NOT_BE_NULL);
        if(name.isEmpty()) {
            throw new IllegalArgumentException(NAME_MUST_NOT_BE_EMPTY);
        }
        properties.put(name, value);
    }

    @Override
    public Graph<ID> property(String name, Object value) {
        setProperty(name, value);
        return this;
    }

    @Override
    public Graph<ID> removeProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!properties.containsKey(name)) {
            throw new IllegalArgumentException("graph does not contain property named '" + name + "'.");
        }
        properties.remove(name);
        return this;
    }

    @Override
    public Map<String, Object> getEdgeProperties() {
        return unmodifiableMap(edgeProperties);
    }

    @Override
    public Optional<Object> getEdgeProperty(String name) {
        return Optional.ofNullable(edgeProperties.get(name));
    }

    @Override
    public void setEdgeProperty(String name, Object value) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requireNonNull(value, VALUE_MUST_NOT_BE_NULL);
        if(name.isEmpty()) {
            throw new IllegalArgumentException(NAME_MUST_NOT_BE_EMPTY);
        }
        edgeProperties.put(name, value);
    }

    @Override
    public Graph<ID> edgeProperty(String name, Object value) {
        setEdgeProperty(name, value);
        return this;
    }

    @Override
    public Graph<ID> removeEdgeProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!edgeProperties.containsKey(name)) {
            throw new IllegalArgumentException("graph does not contain edge property named '" + name + "'.");
        }
        edgeProperties.remove(name);
        return this;
    }

    @Override
    public Map<String, Object> getVertexProperties() {
        return unmodifiableMap(vertexProperties);
    }

    @Override
    public Optional<Object> getVertexProperty(String name) {
        return Optional.ofNullable(vertexProperties.get(name));
    }

    @Override
    public void setVertexProperty(String name, Object value) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        requireNonNull(value, VALUE_MUST_NOT_BE_NULL);
        if(name.isEmpty()) {
            throw new IllegalArgumentException(NAME_MUST_NOT_BE_EMPTY);
        }
        vertexProperties.put(name, value);
    }

    @Override
    public Graph<ID> vertexProperty(String name, Object value) {
        setVertexProperty(name, value);
        return this;
    }

    @Override
    public Graph<ID> removeVertexProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!vertexProperties.containsKey(name)) {
            throw new IllegalArgumentException("graph does not contain edge property named '" + name + "'.");
        }
        vertexProperties.remove(name);
        return this;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractGraph)) return false;
        AbstractGraph<?> that = (AbstractGraph<?>) o;
        return Objects.equals(id, that.id) && Objects.equals(properties, that.properties) && Objects.equals(vertexProperties, that.vertexProperties) && Objects.equals(edgeProperties, that.edgeProperties) && Objects.equals(vertices, that.vertices) && Objects.equals(edges, that.edges);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, properties, vertexProperties, edgeProperties, vertices, edges);
    }
}