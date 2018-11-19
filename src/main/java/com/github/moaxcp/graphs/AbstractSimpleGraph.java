package com.github.moaxcp.graphs;

import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import java.util.*;
import java.util.stream.Collectors;

abstract class AbstractSimpleGraph implements SimpleGraph {
    private abstract class InheritingElement<T> {
        private Map<String, Object> inherited;
        private Map<String, Object> local = new LinkedHashMap<>();

        public InheritingElement(Map<String, Object> inherited) {
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
        public Map<String, Object> getLocal() {
            return unmodifiableMap(local);
        }

        /**
         * Returns the value mapped to name.
         * @param name  of property to return
         * @return value mapped to name
         * @throws NullPointerException if name is null
         */
        public Optional<Object> getProperty(String name) {
            Object value = local.get(requireNonNull(name, "name must not be null."));
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
            requireNonNull(name, "name must not be null.");
            requireNonNull(value, "value must not be null.");
            if(name.isEmpty()) {
                throw new IllegalArgumentException("name must not be empty.");
            }
            local.put(name, value);
        }

        public void addProperties(Map<String, Object> properties) {
            local.putAll(properties);
        }

        public T property(String name, Object value) {
            setProperty(name, value);
            return self();
        }

        public T removeProperty(String name) {
            requireNonNull(name, "name must not be null.");
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
        protected AbstractEdge(Object from, Object to, Map<String, Object> inherited) {
            super(inherited);
            super.setProperty("from", from);
            super.setProperty("to", to);
        }

        private void check() {
            if(!edges.contains(this)) {
                throw new IllegalStateException("Edge is not in graph.");
            }
        }

        public Optional<Object> getId() {
            return getProperty("id");
        }

        @Override
        public void setId(Object id) {
            check();
            getId().ifPresent(edgeIds::remove);
            if(id != null) {
                edgeIds.put(id, this);
            }
            if (id == null && !getId().isPresent()) {
                return;
            }
            if (id == null && getId().isPresent()) {
                removeProperty("id");
                return;
            }
            super.setProperty("id", id);
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
            if ("id".equals(name)) {
                setId(value);
                return;
            }
            super.setProperty(name, value);
        }

        @Override
        public Edge removeProperty(String name) {
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
            if (!(obj instanceof UndirectedEventGraph.UndirectedEdge)) {
                return false;
            }
            UndirectedEventGraph.UndirectedEdge edge = (UndirectedEventGraph.UndirectedEdge) obj;
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
            Objects.requireNonNull(id, "id must not be null.");
            super.setProperty("id", id);
        }

        private void check() {
            if(!vertices.containsKey(getId())) {
                throw new IllegalStateException("Vertex is not in graph.");
            }
        }

        public Object getId() {
            return getProperty("id").get();
        }

        @Override
        public void setId(Object id) {
            check();
            Objects.requireNonNull(id, "id must not be null.");
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

        @Override
        public Vertex removeProperty(String name) {
            if ("id".equals(name)) {
                throw new IllegalArgumentException("id can not be removed.");
            }
            super.removeProperty(name);
            return self();
        }

        public final String toString() {
            return "Vertex '" + getId() + "' " + getLocal().toString();
        }

        @Override
        public final boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof UndirectedEventGraph.UndirectedVertex)) {
                return false;
            }
            UndirectedEventGraph.UndirectedVertex vertex = (UndirectedEventGraph.UndirectedVertex) obj;
            return Objects.equals(getId(), vertex.getId());
        }

        @Override
        public final int hashCode() {
            return Objects.hash(getId());
        }
    }


    private Map<Object, Vertex> vertices;
    private Set<Edge> edges;
    private Map<Object, Edge> edgeIds;
    private Map<String, Object> vertexProperties;
    private Map<String, Object> edgeProperties;
    private Object id;
    private Map<String, Object> properties;

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
        var vertex = vertices.getOrDefault(id, newVertex(id, vertexProperties));
        if (!vertices.containsKey(id)) {
            vertices.put(id, vertex);
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
        return findEdge(from, to).orElseGet(() -> addEdge(newEdge(from, to, edgeProperties)));
    }

    abstract Edge newEdge(Object from, Object to, Map<String, Object> inherited);

    abstract Vertex newVertex(Object id, Map<String, Object> inherited);

    private Edge addEdge(Edge edge) {
        vertex(edge.getFrom());
        vertex(edge.getTo());
        edges.add(edge);
        return edge;
    }

    public void removeEdge(Object from, Object to) {
        findEdge(from, to).ifPresent(edges::remove);
    }

    @Override
    public void removeEdge(Object id) {

    }

    @Override
    public boolean isDirected() {
        return false;
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
        requireNonNull(name, "name must not be null.");
        requireNonNull(value, "value must not be null.");
        if(name.isEmpty()) {
            throw new IllegalArgumentException("name must not be empty.");
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
        requireNonNull(name, "name must not be null.");
        if(!properties.containsKey(name)) {
            throw new IllegalArgumentException("graph does not contain property named '" + name + "'.");
        }
        properties.remove(name);
        return this;
    }

    @Override
    public void setVertexProperty(String name, Object value) {
        vertexProperties.put(name, value);
    }

    @Override
    public Optional<Object> getEdgeProperty(String name) {
        return Optional.ofNullable(edgeProperties.get(name));
    }

    @Override
    public void setEdgeProperty(String name, Object value) {
        requireNonNull(name, "name must not be null.");
        requireNonNull(value, "value must not be null.");
        edgeProperties.put(name, value);
    }

    @Override
    public SimpleGraph edgeProperty(String name, Object value) {
        setEdgeProperty(name, value);
        return this;
    }

    @Override
    public SimpleGraph removeEdgeProperty(String name) {
        requireNonNull(name, "name must not be null.");
        if(!edgeProperties.containsKey(name)) {
            throw new IllegalArgumentException("graph does not contain edge property named '" + name + "'.");
        }
        edgeProperties.remove(name);
        return this;
    }

    @Override
    public Optional<Object> getVertexProperty(String name) {
        return null;
    }

    @Override
    public SimpleGraph vertexProperty(String name, Object value) {
        return null;
    }

    @Override
    public SimpleGraph removeVertexProperty(String name) {
        return null;
    }
}
