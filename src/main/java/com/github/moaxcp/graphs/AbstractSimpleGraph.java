package com.github.moaxcp.graphs;

import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

abstract class AbstractSimpleGraph implements SimpleGraph {

    private static final String NAME_MUST_NOT_BE_NULL = "name must not be null.";
    private static final String VALUE_MUST_NOT_BE_NULL = "value must not be null.";
    private static final String NAME_MUST_NOT_BE_EMPTY = "name must not be empty.";
    private static final String ID_MUST_NOT_BE_NULL = "id must not be null.";

    private abstract class InheritingElement<T> {
        private Map<String, Object> inherited;
        private Map<String, Object> local = new LinkedHashMap<>();

        private InheritingElement(Map<String, Object> inherited) {
            this.inherited = unmodifiableMap(requireNonNull(inherited, "inherited must not be null."));
        }

        public Map<String, Object> inherited() {
            return inherited;
        }

        @SuppressWarnings("unchecked")
        T self() {
            return (T) this;
        }

        /**
         * Returns an unmodifiable {@link Map} of all properties set on this Element.
         * @return all properties set on this element
         */
        public Map<String, Object> local() {
            return unmodifiableMap(local);
        }

        /**
         * Returns the value mapped to name.
         * @param name  of property to return
         * @return value mapped to name
         * @throws NullPointerException if name is null
         */
        public Optional<Object> getProperty(String name) {
            requireNonNull(name, NAME_MUST_NOT_BE_NULL);
            Object value = local.get(name);
            if (value != null) {
                return Optional.of(value);
            }
            return ofNullable(inherited.get(name));
        }

        /**
         * Maps name to value
         * @param name
         * @param value
         */
        public void setProperty(String name, Object value) {
            requireNonNull(name, NAME_MUST_NOT_BE_NULL);
            requireNonNull(value, VALUE_MUST_NOT_BE_NULL);
            if(name.isEmpty()) {
                throw new IllegalArgumentException(NAME_MUST_NOT_BE_EMPTY);
            }
            local.put(name, value);
        }

        public T property(String name, Object value) {
            setProperty(name, value);
            return self();
        }

        public T removeProperty(String name) {
            requireNonNull(name, NAME_MUST_NOT_BE_NULL);
            if(!local.containsKey(name)) {
                throw new IllegalArgumentException("element does not contain property named '" + name + "'.");
            }
            local.remove(name);
            return self();
        }
    }

    /**
     * Edge represents an undirected edge in this graph.
     */
    public abstract class AbstractEdge extends InheritingElement<Edge> implements Edge {
        private Object id;
        private Object from;
        private Object to;

        protected AbstractEdge(Object from, Object to, Map<String, Object> inherited) {
            super(inherited);
            this.from = from;
            this.to = to;
        }

        private void check() {
            if(!edges.contains(this)) {
                throw new IllegalStateException("Edge is not in graph.");
            }
        }

        public Optional<Object> getId() {
            return Optional.ofNullable(id);
        }

        @Override
        public void setId(Object id) {
            check();
            edgeIds.remove(this.id);
            if(id != null) {
                edgeIds.put(id, this);
            }
            this.id = id;
        }

        public Edge id(Object id) {
            setId(id);
            return self();
        }

        /**
         * Returns id of "from" {@link Vertex}.
         * @return id of "from" {@link Vertex}
         */
        public Object getFrom() {
            return from;
        }

        /**
         * Sets "from" {@link Vertex} to vertex with id. If vertex does not exist it is created.
         * @param from
         */
        public void setFrom(Object from) {
            check();
            requireNonNull(from, "from must not be null.");
            edges.remove(this);
            vertex(from);
            this.from = from;
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
            return to;
        }

        /**
         * Sets "to" {@link Vertex} to vertex with id. If vertex does not exist it is created.
         * @param to {@link Vertex} id for "to" vertex
         */
        public void setTo(Object to) {
            check();
            Objects.requireNonNull(to, "to must not be null.");
            edges.remove(this);
            vertex(to);
            this.to = to;
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

        public List<Object> endpoints() {
            return List.of(from, to);
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
        public void setProperty(String name, Object value) {
            check();
            super.setProperty(name, value);
        }

        @Override
        public Edge removeProperty(String name) {
            check();
            return super.removeProperty(name);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Edge)) {
                return false;
            }
            Edge edge = (Edge) obj;
            return equals(edge.getFrom(), edge.getTo());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFrom(), getTo());
        }
    }

    /**
     * Vertex represents a vertex in this graph.
     */
    public abstract class AbstractVertex extends InheritingElement<Vertex> implements Vertex {
        protected AbstractVertex(Object id, Map<String, Object> inherited) {
            super(inherited);
            Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
            super.setProperty("id", id);
        }

        private void check() {
            if(!vertices.containsKey(getId())) {
                throw new IllegalStateException("Vertex is not in graph.");
            }
        }

        public Object getId() {
            return super.getProperty("id").orElseThrow(() -> new IllegalStateException("'id' should never be set to null."));
        }

        @Override
        public void setId(Object id) {
            check();
            Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
            Set<? extends Edge> adjacent = adjacentEdges();
            Object oldId = getId();
            vertices.remove(this.getId());
            super.setProperty("id", id);
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

        public Vertex id(Object id) {
            setId(id);
            return self();
        }

        @Override
        public void setProperty(String name, Object value) {
            check();
            if ("id".equals(name)) {
                setId(value);
                return;
            }
            super.setProperty(name, value);
        }

        @Override
        public Vertex removeProperty(String name) {
            check();
            if ("id".equals(name)) {
                throw new IllegalArgumentException("id cannot be removed.");
            }
            super.removeProperty(name);
            return self();
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
        public Set<Edge> adjacentEdges() {
            check();
            return edges.stream()
                    .filter(edge -> getId().equals(edge.getFrom()) || getId().equals(edge.getTo()))
                    .collect(Collectors.toSet());
        }
        @Override
        public Set<Edge> inEdges() {
            check();
            return edges.stream()
                    .filter(edge -> getId().equals(edge.getTo()))
                    .collect(Collectors.toSet());
        }
        @Override
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
            if (!(obj instanceof Vertex)) {
                return false;
            }
            Vertex vertex = (Vertex) obj;
            return Objects.equals(getId(), vertex.getId());
        }

        @Override
        public final int hashCode() {
            return Objects.hash(getId());
        }
    }


    private Object id;
    private Map<String, Object> properties;
    private Map<String, Object> vertexProperties;
    private Map<String, Object> edgeProperties;
    private Map<Object, Vertex> vertices;
    private Set<Edge> edges;
    private Map<Object, Edge> edgeIds;

    AbstractSimpleGraph() {
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
        edgeIds = new LinkedHashMap<>();
        vertexProperties = new LinkedHashMap<>();
        edgeProperties = new LinkedHashMap<>();
        properties = new LinkedHashMap<>();
    }

    AbstractSimpleGraph(Object id) {
        this();
        this.id = id;
    }

    @Override
    public Map<Object, Vertex> getVertices() {
        return Collections.unmodifiableMap(vertices);
    }

    @Override
    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    @Override
    public Map<Object, Edge> getEdgeIds() { return Collections.unmodifiableMap(edgeIds); }

    public Optional<Vertex> findVertex(Object id) {
        return Optional.ofNullable(vertices.get(id));
    }

    public Vertex vertex(Object id) {
        return findVertex(id).orElseGet(() -> addVertex(id));
    }

    Vertex addVertex(Object id) {
        var vertex = newVertex(id, vertexProperties);
        vertices.put(id, vertex);
        return vertex;
    }

    public void removeVertex(Object id) {
        Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
        var optional = findVertex(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("vertex '" + id + "' not found.");
        }
        var adjacent = optional.get().adjacentEdges();
        for (var edge : adjacent) {
            removeEdge(edge.getFrom(), edge.getTo());
        }
        vertices.remove(id);
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
        return findEdge(from, to).orElseGet(() -> addEdge(from, to));
    }

    abstract Edge newEdge(Object from, Object to, Map<String, Object> inherited);

    abstract Vertex newVertex(Object id, Map<String, Object> inherited);

    Edge addEdge(Object from, Object to) {
        vertex(from);
        vertex(to);
        var edge = newEdge(from, to, edgeProperties);
        edges.add(edge);
        return edge;
    }

    public void removeEdge(Object from, Object to) {
        Consumer<Edge> remove = ((Consumer<Edge>) edges::remove)
                .andThen(edge -> edge.getId().ifPresent(edgeIds::remove));
        var optional = findEdge(from, to);
        optional.ifPresent(remove);
        if(!optional.isPresent()) {
            throw new IllegalArgumentException("edge from '" + from + "' to '" + to + "' not found.");
        }
    }

    @Override
    public void removeEdge(Object id) {
        requireNonNull(id, ID_MUST_NOT_BE_NULL);
        var optional = findEdge(id);
        optional.ifPresent(edge -> removeEdge(edge.getFrom(), edge.getTo()));
        if(!optional.isPresent()) {
            throw new IllegalArgumentException("edge with id '" + id + "' not found.");
        }
    }

    @Override
    public Optional<Object> getId() {
        return Optional.ofNullable(id);
    }

    @Override
    public void setId(Object id) {
        this.id = id;
    }

    @Override
    public SimpleGraph id(Object id) {
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
    public SimpleGraph property(String name, Object value) {
        setProperty(name, value);
        return this;
    }

    @Override
    public SimpleGraph removeProperty(String name) {
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
    public SimpleGraph edgeProperty(String name, Object value) {
        setEdgeProperty(name, value);
        return this;
    }

    @Override
    public SimpleGraph removeEdgeProperty(String name) {
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
    public SimpleGraph vertexProperty(String name, Object value) {
        setVertexProperty(name, value);
        return this;
    }

    @Override
    public SimpleGraph removeVertexProperty(String name) {
        requireNonNull(name, NAME_MUST_NOT_BE_NULL);
        if(!vertexProperties.containsKey(name)) {
            throw new IllegalArgumentException("graph does not contain edge property named '" + name + "'.");
        }
        vertexProperties.remove(name);
        return this;
    }
}
