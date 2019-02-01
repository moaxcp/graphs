package com.github.moaxcp.graphs;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import java.util.*;

abstract class AbstractGraph<T> implements Graph<T> {

    private static final String NAME_MUST_NOT_BE_NULL = "name must not be null.";
    private static final String VALUE_MUST_NOT_BE_NULL = "value must not be null.";
    private static final String NAME_MUST_NOT_BE_EMPTY = "name must not be empty.";
    private static final String ID_MUST_NOT_BE_NULL = "id must not be null.";

    /**
     * Edge represents an undirected edge in this graph.
     */
    public class SimpleEdge extends InheritingElement<Edge<T>> implements Edge<T> {
        private T id;
        private T from;
        private T to;

        protected SimpleEdge(T from, T to, Map<String, Object> inherited) {
            super(inherited);
            this.from = from;
            this.to = to;
        }

        private void check() {
            EdgeKey<T> key = newEdgeKey(from, to);
            if(!edges.keySet().contains(key)) {
                throw new IllegalStateException("Edge is not in graph.");
            }
        }

        @Override
        public final Optional<T> getId() {
            return Optional.ofNullable(id);
        }

        @Override
        public void setId(T id) {
            check();
            edgeIds.remove(this.id);
            if(id != null) {
                edgeIds.put(id, this);
            }
            this.id = id;
        }

        @Override
        public final Edge<T> id(T id) {
            setId(id);
            return self();
        }

        /**
         * Returns id of "from" {@link Vertex}.
         * @return id of "from" {@link Vertex}
         */
        @Override
        public final T getFrom() {
            return from;
        }

        /**
         * Sets "from" {@link Vertex} to vertex with id. If vertex does not exist it is created.
         * @param from
         */
        @Override
        public void setFrom(T from) {
            check();
            requireNonNull(from, "from must not be null.");
            EdgeKey<T> oldKey = newEdgeKey(this.from, this.to);
            edges.remove(oldKey);
            vertex(from);
            this.from = from;
            EdgeKey<T> key = newEdgeKey(this.from, this.to);
            edges.put(key, this);
        }

        /**
         * Sets "from" {@link Vertex} to vertex with id returning this {@link Edge}. If vertex does not exist it is
         * created.
         * @param from {@link Vertex} id for "from" vertex
         * @return this edge
         */
        @Override
        public Edge<T> from(T from) {
            setFrom(from);
            return this;
        }

        @Override
        public final T from() {
            return getFrom();
        }

        /**
         * Returns id of "to" {@link Vertex}.
         * @return id of "to" {@link Vertex}
         */
        @Override
        public final T getTo() {
            return to;
        }

        /**
         * Sets "to" {@link Vertex} to vertex with id. If vertex does not exist it is created.
         * @param to {@link Vertex} id for "to" vertex
         */
        @Override
        public void setTo(T to) {
            check();
            Objects.requireNonNull(to, "to must not be null.");
            EdgeKey<T> oldKey = newEdgeKey(this.from, this.to);
            edges.remove(oldKey);
            vertex(to);
            this.to = to;
            EdgeKey<T> key = newEdgeKey(this.from, this.to);
            edges.put(key, this);
        }

        /**
         * Sets "to" {@link Vertex} to vertex with id returning this {@link Edge}. If vertex does not exist it is
         * created.
         * @param to {@link Vertex} id for "to" vertex
         * @return this edge
         */
        @Override
        public final Edge<T> to(T to) {
            setTo(to);
            return this;
        }

        @Override
        public final T to() {
            return getTo();
        }

        @Override
        public final boolean isDirected() {
            return AbstractGraph.this.isDirected();
        }

        @Override
        public final List<T> endpoints() {
            return List.of(from, to);
        }

        public final Vertex<T> fromVertex() {
            check();
            return vertex(getFrom());
        }

        public final Vertex<T> toVertex() {
            check();
            return vertex(getTo());
        }

        @Override
        public void setProperty(String name, Object value) {
            check();
            super.setProperty(name, value);
        }

        @Override
        public Edge<T> removeProperty(String name) {
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

    /**
     * Vertex represents a vertex in this graph.
     */
    public class SimpleVertex extends InheritingElement<Vertex<T>> implements Vertex<T> {
        private T id;

        protected SimpleVertex(T id, Map<String, Object> inherited) {
            super(inherited);
            Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
            this.id = id;
        }

        private void check() {
            if(!vertices.containsKey(getId())) {
                throw new IllegalStateException("Vertex is not in graph.");
            }
        }

        public T getId() {
            return id;
        }

        @Override
        public void setId(T id) {
            check();
            Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
            Set<? extends Edge<T>> adjacent = adjacentEdges();
            Object oldId = getId();
            vertices.remove(this.getId());
            this.id = id;
            vertices.put(id, this);
            for (Edge<T> edge : adjacent) {
                if (edge.getFrom().equals(oldId)) {
                    edge.setFrom(id);
                }
                if (edge.getTo().equals(oldId)) {
                    edge.setTo(id);
                }
            }
        }

        public Vertex<T> id(T id) {
            setId(id);
            return self();
        }

        @Override
        public void setProperty(String name, Object value) {
            check();
            super.setProperty(name, value);
        }

        @Override
        public Vertex<T> removeProperty(String name) {
            check();
            super.removeProperty(name);
            return self();
        }

        public Vertex<T> connectsTo(T to) {
            check();
            edge(getId(), to);
            return this;
        }

        public Vertex<T> connectsFrom(T s) {
            check();
            edge(s, getId());
            return this;
        }

        public Edge<T> edgeTo(T to) {
            check();
            return edge(getId(), to);
        }

        public Edge<T> edgeFrom(T from) {
            check();
            return edge(from, getId());
        }

        public Vertex<T> toVertex(T id) {
            check();
            return edgeTo(id).toVertex();
        }

        public Vertex<T> fromVertex(T id) {
            check();
            return edgeFrom(id).fromVertex();
        }

        @Override
        public Set<Edge<T>> adjacentEdges() {
            check();
            Set<Edge<T>> edges = adjacentEdges.get(id);
            if(edges == null) {
                return Collections.emptySet();
            }
            return unmodifiableSet(edges);
        }
        @Override
        public Set<Edge<T>> inEdges() {
            check();
            Set<Edge<T>> edges = inEdges.get(id);
            if(edges == null) {
                return Collections.emptySet();
            }
            return unmodifiableSet(edges);
        }
        @Override
        public Set<Edge<T>> outEdges() {
            check();
            Set<Edge<T>> edges = outEdges.get(id);
            if(edges == null) {
                return Collections.emptySet();
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


    private T id;
    private Map<String, Object> properties;
    private Map<String, Object> vertexProperties;
    private Map<String, Object> edgeProperties;
    private Map<T, Vertex<T>> vertices;
    private Map<EdgeKey<T>, Edge<T>> edges;
    private Map<T, Edge<T>> edgeIds;
    private Map<T, Set<Edge<T>>> adjacentEdges;
    private Map<T, Set<Edge<T>>> inEdges;
    private Map<T, Set<Edge<T>>> outEdges;

    AbstractGraph() {
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

    AbstractGraph(T id) {
        this();
        this.id = id;
    }

    @Override
    public Map<T, Vertex<T>> getVertices() {
        return Collections.unmodifiableMap(vertices);
    }

    @Override
    public Collection<Edge<T>> getEdges() {
        return Collections.unmodifiableCollection(edges.values());
    }

    @Override
    public Map<T, Edge<T>> getEdgeIds() { return Collections.unmodifiableMap(edgeIds); }

    public Optional<Vertex<T>> findVertex(T id) {
        return Optional.ofNullable(vertices.get(id));
    }

    public Vertex<T> vertex(T id) {
        return findVertex(id).orElseGet(() -> addVertex(id));
    }

    Vertex<T> addVertex(T id) {
        var vertex = newVertex(id, vertexProperties);
        vertices.put(id, vertex);
        return vertex;
    }

    public void removeVertex(T id) {
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

    public Optional<Edge<T>> findEdge(T from, T to) {
        requireNonNull(from, "from must not be null.");
        requireNonNull(to, "to must not be null.");
        var key = newEdgeKey(from, to);
        var edge = edges.get(key);
        return Optional.ofNullable(edge);
    }

    public Optional<Edge<T>> findEdge(T id) {
        requireNonNull(id, "id must not be null.");
        return Optional.ofNullable(edgeIds.get(id));
    }

    public Edge<T> edge(T from, T to) {
        return findEdge(from, to).orElseGet(() -> addEdge(from, to));
    }

    Edge<T> newEdge(T from, T to, Map<String, Object> inherited) {
        return new SimpleEdge(from, to, inherited);
    }

    protected abstract EdgeKey<T> newEdgeKey(T from, T to);

    Vertex<T> newVertex(T id, Map<String, Object> inherited) {
        return new SimpleVertex(id, inherited);
    }

    Edge<T> addEdge(T from, T to) {
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

    private Set<Edge<T>> mergeSet(Edge<T> edge, Set<Edge<T>> set) {
        if(set == null) {
            set = new LinkedHashSet<>();
            set.add(edge);
        }
        else {
            set.add(edge);
        }
        return set;
    }

    public void removeEdge(T from, T to) {
        requireNonNull(from, "from must not be null.");
        requireNonNull(to, "to must not be null.");
        EdgeKey<T> key = newEdgeKey(from, to);
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
    public void removeEdge(T id) {
        requireNonNull(id, ID_MUST_NOT_BE_NULL);
        var optional = findEdge(id);
        optional.ifPresent(edge -> removeEdge(edge.getFrom(), edge.getTo()));
        if(!optional.isPresent()) {
            throw new IllegalArgumentException("edge with id '" + id + "' not found.");
        }
    }

    @Override
    public Optional<T> getId() {
        return Optional.ofNullable(id);
    }

    @Override
    public void setId(T id) {
        this.id = id;
    }

    @Override
    public Graph<T> id(T id) {
        setId(id);
        return this;
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
    public Graph<T> property(String name, Object value) {
        setProperty(name, value);
        return this;
    }

    @Override
    public Graph<T> removeProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!properties.containsKey(name)) {
            throw new IllegalArgumentException("graph does not contain property named '" + name + "'.");
        }
        properties.remove(name);
        return this;
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
    public Graph<T> edgeProperty(String name, Object value) {
        setEdgeProperty(name, value);
        return this;
    }

    @Override
    public Graph<T> removeEdgeProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!edgeProperties.containsKey(name)) {
            throw new IllegalArgumentException("graph does not contain edge property named '" + name + "'.");
        }
        edgeProperties.remove(name);
        return this;
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
    public Graph<T> vertexProperty(String name, Object value) {
        setVertexProperty(name, value);
        return this;
    }

    @Override
    public Graph<T> removeVertexProperty(String name) {
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