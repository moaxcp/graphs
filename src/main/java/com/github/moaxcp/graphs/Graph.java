package com.github.moaxcp.graphs;

import java.util.*;

/**
 * A graph is composed of vertices and edges. A vertex is able to connect to other vertices through edges. Edges
 * connect two vertices. A graph can be directed or undirected. If a graph is directed its edges are treated as
 * directed otherwise if a graph is undirected edges are treated as undirected.
 * <p>Vertices have an 'id' which uniquely identifies the vertex within the graph. Edges have a 'to' and 'from' property which are the
 * ids of the vertex endpoints. Edges are optionally identified.</p>
 *
 * @param <T> type of all identifiers in graph
 */
public interface Graph<T> {
    /**
     * Vertex of graph. A vertex must have an identifier unique to all vertices in the graph.
     *
     * @param <T> type of identifier
     */
    public interface Vertex<T> {
        /**
         * returns identifier of vertex.
         *
         * @return identifier of vertex
         */
        T getId();

        /**
         * sets identifier of vertex.
         *
         * @param id identifier of vertex
         * @throws NullPointerException if id is null
         */
        void setId(T id) throws NullPointerException;

        /**
         * sets identifier of this vertex. This vertex is returned.
         *
         * @param id identifier of vertex.
         * @return this vertex
         * @throws NullPointerException if id is null
         */
        Vertex<T> id(T id) throws NullPointerException;

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'to' parameter. The
         * edge or vertex is created if needed. This vertex is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'to' parameter. If an edge is created the
         * 'from' property is set to the 'id' of this vertex and the 'to' property is set to the 'id' of the 'to'
         * vertex.</p>
         *
         * @param to vertex id
         * @return this vertex
         * @throws NullPointerException if to is null
         */
        Vertex<T> connectsTo(T to) throws NullPointerException;

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'from' parameter. The
         * edge or vertex is created if needed. This vertex is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'from' parameter. If an edge is created the
         * 'from' property is set to the 'id' of the 'from' vertex and the 'to' property is set to the 'id' of this
         * vertex.</p>
         *
         * @param from vertex id
         * @return this vertex
         * @throws NullPointerException if from is null
         */
        Vertex<T> connectsFrom(T from) throws NullPointerException;

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'to' parameter. The
         * edge or vertex is created if needed. The edge is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'to' parameter. If an edge is created the
         * 'from' property is set to the 'id' of this vertex and the 'to' property is set to the 'id' of the 'to'
         * vertex.</p>
         *
         * @param to vertex id
         * @return edge between this vertex and the 'to' vertex
         * @throws NullPointerException if to is null
         */
        Edge<T> edgeTo(T to) throws NullPointerException;

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'from' parameter. The
         * edge or vertex is created if needed. The edge is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'from' parameter. If an edge is created the
         * 'from' property is set to the 'id' of the 'from' vertex and the 'to' property is set to the 'id' of this
         * vertex.</p>
         *
         * @param from vertex id
         * @return edge from 'from' vertex to this vertex
         * @throws NullPointerException if from is null
         */
        Edge<T> edgeFrom(T from) throws NullPointerException;

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'to' parameter. The
         * edge or vertex is created if needed. The 'to' vertex is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'to' parameter. If an edge is created the
         * 'from' property is set to the 'id' of this vertex and the 'to' property is set to the 'id' of the 'to'
         * vertex.</p>
         *
         * @param to vertex id
         * @return 'to' vertex
         * @throws NullPointerException if to is null
         */
        Vertex<T> toVertex(T to) throws NullPointerException;

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'from' parameter. The
         * edge or vertex is created if needed. The vertex is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'from' parameter. If an edge is created the
         * 'from' property is set to the 'id' of the 'from' vertex and the 'to' property is set to the 'id' of this
         * vertex.</p>
         *
         * @param from vertex id
         * @return 'from' vertex
         * @throws NullPointerException if from is null
         */
        Vertex<T> fromVertex(T from) throws NullPointerException;

        Set<Edge<T>> adjacentEdges();

        Set<Edge<T>> inEdges();

        Set<Edge<T>> outEdges();

        Optional<Object> getProperty(String name);

        void setProperty(String name, Object value);

        Vertex<T> property(String name, Object value);

        Vertex<T> removeProperty(String name);

        Map<String, Object> inherited();

        Map<String, Object> local();
    }

    public interface Edge<T> {
        Optional<T> getId();

        void setId(T id);

        Edge<T> id(T id);

        T getFrom();

        void setFrom(T from);

        T from();

        Edge<T> from(T from);

        T getTo();

        void setTo(T to);

        T to();

        Edge<T> to(T to);

        List<T> endpoints();

        Vertex<T> fromVertex();

        Vertex<T> toVertex();

        boolean isDirected();

        Optional<Object> getProperty(String name);

        void setProperty(String name, Object value);

        Edge<T> property(String name, Object value);

        Edge<T> removeProperty(String name);

        Map<String, Object> inherited();

        Map<String, Object> local();
    }

    Map<T, Vertex<T>> getVertices();

    Collection<Edge<T>> getEdges();

    Map<T, Edge<T>> getEdgeIds();

    Optional<Vertex<T>> findVertex(T id);

    Vertex<T> vertex(T id);

    void removeVertex(T id);

    Optional<Edge<T>> findEdge(T from, T to);

    Optional<Edge<T>> findEdge(T id);

    Edge<T> edge(T from, T to);

    void removeEdge(T from, T to);

    void removeEdge(T id);

    Optional<T> getId();

    void setId(T id);

    Graph<T> id(T id);

    boolean isDirected();

    Optional<Object> getProperty(String name);

    void setProperty(String name, Object value);

    Graph<T> property(String name, Object value);

    Graph<T> removeProperty(String name);

    Optional<Object> getEdgeProperty(String name);

    void setEdgeProperty(String name, Object value);

    Graph<T> edgeProperty(String name, Object value);

    Graph<T> removeEdgeProperty(String name);

    Optional<Object> getVertexProperty(String name);

    void setVertexProperty(String name, Object value);

    Graph<T> vertexProperty(String name, Object value);

    Graph<T> removeVertexProperty(String name);
}
