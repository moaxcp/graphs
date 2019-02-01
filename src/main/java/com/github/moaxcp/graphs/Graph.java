package com.github.moaxcp.graphs;

import java.util.*;

/**
 * A graph is composed of vertices and edges. A vertex is able to connect to other vertices through edges. Edges
 * connect two vertices. A graph can be directed or undirected. If a graph is directed its edges are treated as
 * directed otherwise if a graph is undirected edges are treated as undirected.
 *
 * <p>Vertices have an 'id' which uniquely identifies the vertex within the graph. Edges have a 'to' and 'from'
 * property which are the ids of the vertex endpoints. Edges are optionally identified.</p>
 *
 * <p>At all times the graph is valid event when removing connected vertices.</p>
 *
 * <p>Undirected graphs ignore 'from'/'to' order in edges.</p>
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
        void setId(T id);

        /**
         * sets identifier of this vertex. This vertex is returned.
         *
         * @param id identifier of vertex.
         * @return this vertex
         * @throws NullPointerException if id is null
         */
        Vertex<T> id(T id);

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'to' parameter. The
         * edge or vertex is created if needed. This vertex is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'to' parameter. If an edge is created the
         * 'from' property is set to the 'id' of this vertex and the 'to' property is set to the 'id' of the 'to'
         * vertex.</p>
         *
         * <p>If this graph is undirected and contains an edge connecting this vertex with 'to' the edge will not be
         * created. Undirected graphs ignore 'from'/'to' order in edges.</p>
         *
         * @param to vertex id
         * @return this vertex
         * @throws NullPointerException if to is null
         */
        Vertex<T> connectsTo(T to);

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'from' parameter. The
         * edge or vertex is created if needed. This vertex is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'from' parameter. If an edge is created the
         * 'from' property is set to the 'id' of the 'from' vertex and the 'to' property is set to the 'id' of this
         * vertex.</p>
         *
         * <p>If this graph is undirected and contains an edge connecting this vertex with 'from' the edge will not be
         * created. Undirected graphs ignore 'from'/'to' order in edges.</p>
         *
         * @param from vertex id
         * @return this vertex
         * @throws NullPointerException if from is null
         */
        Vertex<T> connectsFrom(T from);

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'to' parameter. The
         * edge or vertex is created if needed. The edge is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'to' parameter. If an edge is created the
         * 'from' property is set to the 'id' of this vertex and the 'to' property is set to the 'id' of the 'to'
         * vertex.</p>
         *
         * <p>If this graph is undirected and contains an edge connecting this vertex with 'to' the edge will be
         * returned. Undirected graphs ignore 'from'/'to' order in edges.</p>
         *
         * @param to vertex id
         * @return edge between this vertex and the 'to' vertex
         * @throws NullPointerException if to is null
         */
        Edge<T> edgeTo(T to);

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'from' parameter. The
         * edge or vertex is created if needed. The edge is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'from' parameter. If an edge is created the
         * 'from' property is set to the 'id' of the 'from' vertex and the 'to' property is set to the 'id' of this
         * vertex.</p>
         *
         * <p>If this graph is undirected and contains an edge connecting this vertex with 'from' the edge will be
         * returned. Undirected graphs ignore 'from'/'to' order in edges.</p>
         *
         * @param from vertex id
         * @return edge from 'from' vertex to this vertex
         * @throws NullPointerException if from is null
         */
        Edge<T> edgeFrom(T from);

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'to' parameter. The
         * edge or vertex is created if needed. The 'to' vertex is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'to' parameter. If an edge is created the
         * 'from' property is set to the 'id' of this vertex and the 'to' property is set to the 'id' of the 'to'
         * vertex.</p>
         *
         * <p>If this graph is undirected and contains an edge connecting this vertex with 'to' the edge will not be
         * created and the vertex will be returned. Undirected graphs ignore 'from'/'to' order in edges.</p>
         *
         * @param to vertex id
         * @return 'to' vertex
         * @throws NullPointerException if to is null
         */
        Vertex<T> toVertex(T to);

        /**
         * Ensures this vertex connects to the vertex with an 'id' property equal to the 'from' parameter. The
         * edge or vertex is created if needed. The vertex is returned.
         *
         * <p>If a vertex is created its 'id' property will be set to the 'from' parameter. If an edge is created the
         * 'from' property is set to the 'id' of the 'from' vertex and the 'to' property is set to the 'id' of this
         * vertex.</p>
         *
         * <p>If this graph is undirected and contains an edge connecting this vertex with 'to' the edge will not be
         * created and the vertex will be returned. Undirected graphs ignore 'from'/'to' order in edges.</p>
         *
         * @param from vertex id
         * @return 'from' vertex
         * @throws NullPointerException if from is null
         */
        Vertex<T> fromVertex(T from);

        /**
         * Returns the set of all edges connecting to this vertex.
         *
         * @return set of adjacent edges
         */
        Set<Edge<T>> adjacentEdges();

        /**
         * Returns the set of edges connecting to this vertex where 'to' is the 'id' of this vertex
         *
         * @return incoming edges
         */
        Set<Edge<T>> inEdges();

        /**
         * Returns the set of edges connecting to this vertex where 'from' is the 'id' of this vertex
         *
         * @return outgoing edges
         */
        Set<Edge<T>> outEdges();

        /**
         * Returns {@link Optional} of a property. If the property does not exist the returned {@link Optional} is
         * empty.
         *
         * <p>A property is resolved by first checking properties set on this vertex. If the local property does
         * not exist vertexProperties in the graph are checked.</p>
         *
         * @param name of property
         * @return optional of named property
         * @throws NullPointerException if name is null
         */
        Optional<Object> getProperty(String name);

        /**
         * Sets the value of a local property.
         *
         * @param name  of property
         * @param value of property
         * @throws NullPointerException if name or value is null
         */
        void setProperty(String name, Object value);

        /**
         * Sets the value of a local property returning this vertex.
         *
         * @param name  of property
         * @param value of property
         * @return this vertex
         * @throws NullPointerException if name or value is null
         */
        Vertex<T> property(String name, Object value);

        /**
         * Removes property from local properties returning this vertex.
         *
         * @param name of property
         * @return this vertex
         * @throws NullPointerException if name is null
         */
        Vertex<T> removeProperty(String name);

        /**
         * Returns an unmodifiable map of inherited properties
         *
         * @return map of inherited properties
         */
        Map<String, Object> inherited();

        /**
         * Returns an unmodifiable map of local properties
         *
         * @return map of local properties
         */
        Map<String, Object> local();
    }

    /**
     * An edge between two vertices in a graph. Edge direction depends on graph type. If the graph is directed
     * {@link #isDirected()} will return true.
     *
     * @param <T> type of identifier
     */
    public interface Edge<T> {
        /**
         * Returns optional identifier of this edge.
         *
         * @return optional identifier
         */
        Optional<T> getId();

        /**
         * Sets identifier of this edge.
         *
         * @param id of edge
         * @throws NullPointerException if id is null
         */
        void setId(T id);

        /**
         * Sets identifier of this edge.
         *
         * @param id of edge
         * @return this edge
         * @throws NullPointerException if id is null
         */
        Edge<T> id(T id);

        /**
         * Returns vertex id of 'from' endpoint.
         *
         * @return id of 'from' endpoint
         */
        T getFrom();

        /**
         * Sets vertex id of 'from' endpoint. If vertex does not exist it is created.
         *
         * @param from endpoint
         * @throws NullPointerException if from is null
         */
        void setFrom(T from);

        /**
         * Returns vertex id of 'from' endpoint.
         *
         * @return id of 'from' endpoint
         */
        T from();

        /**
         * Sets vertex id of 'from' endpoint. If vertex does not exist it is created.
         *
         * @param from endpoint
         * @return this edge
         * @throws NullPointerException if from is null
         */
        Edge<T> from(T from);

        /**
         * Returns vertex id of 'to' endpoint.
         *
         * @return id of 'to' endpoint
         */
        T getTo();

        /**
         * Sets vertex id of 'to' endpoint. If vertex does not exist it is created.
         *
         * @param to endpoint
         * @throws NullPointerException if to is null
         */
        void setTo(T to);

        /**
         * Returns vertex id of 'to' endpoint.
         *
         * @return id of 'to' endpoint
         */
        T to();

        /**
         * Sets vertex id of 'to' endpoint. If vertex does not exist it is created.
         *
         * @param to endpoint
         * @return this edge
         */
        Edge<T> to(T to);

        /**
         * Returns ordered pair of endpoint ids. 'from' endpoint id is always first followed by 'to' endpoint id.
         *
         * @return order pair of endpoints
         */
        List<T> endpoints();

        /**
         * Returns 'from' vertex
         *
         * @return 'from' vertex
         */
        Vertex<T> fromVertex();

        /**
         * Returns 'to' vertex
         *
         * @return 'to' vertex
         */
        Vertex<T> toVertex();

        /**
         * Returns true if this edge is directed. This edge is always directed if the parent graph is directed.
         *
         * @return true if this edge is directed
         */
        boolean isDirected();

        /**
         * Returns {@link Optional} of a property. If the property does not exist the returned {@link Optional} is
         * empty.
         *
         * <p>A property is resolved by first checking properties set on this edge. If the local property does
         * not exist edgeProperties in the graph are checked.</p>
         *
         * @param name of property
         * @return optional of named property
         * @throws NullPointerException if name is null
         */
        Optional<Object> getProperty(String name);

        /**
         * Sets the value of a local property.
         *
         * @param name
         * @param value
         * @throws NullPointerException if name or value is null
         */
        void setProperty(String name, Object value);

        /**
         * Sets the value of a local property returning this edge.
         *
         * @param name  of property
         * @param value of property
         * @return this edge
         * @throws NullPointerException if name or value is null
         */
        Edge<T> property(String name, Object value);

        /**
         * Removes property from local properties returning this edge.
         *
         * @param name of property
         * @return this edge
         * @throws NullPointerException if name is null
         */
        Edge<T> removeProperty(String name);

        /**
         * Returns an unmodifiable map of inherited properties
         *
         * @return map of inherited properties
         */
        Map<String, Object> inherited();

        /**
         * Returns an unmodifiable map of local properties
         *
         * @return map of local properties
         */
        Map<String, Object> local();
    }

    /**
     * Returns an ordered map of all vertices in this graph. The key is the vertex id. Ordering is determined by
     * implementing class.
     *
     * @return ordered map of all vertices in this graph
     */
    Map<T, Vertex<T>> getVertices();

    /**
     * Returns an ordered collection of all edges in this graph.
     *
     * @return ordered collection of all edges
     */
    Collection<Edge<T>> getEdges();

    /**
     * Returns a map of edge ids to edges.
     *
     * @return map of edge ids to edges
     */
    Map<T, Edge<T>> getEdgeIds();

    /**
     * Finds vertex with given id. If vertex is not in this graph the optional is empty.
     *
     * @param id of vertex
     * @return optional of vertex
     * @throws NullPointerException if id is null
     */
    Optional<Vertex<T>> findVertex(T id);

    /**
     * Ensure a vertex exists with id and returns the vertex. If the vertex does not exist it is created.
     *
     * @param id of vertex
     * @return created or existing vertex
     * @throws NullPointerException if id is null
     */
    Vertex<T> vertex(T id);

    /**
     * Removes vertex with given id. If edges connect to the vertex they are removed first.
     *
     * @param id of vertex
     * @throws NullPointerException if id is null
     */
    void removeVertex(T id);

    /**
     * Finds edge connecting 'from' and 'to' vertex. If edge is not in this graph the optional is empty. The order of
     * 'from' and 'to' does not matter in undirected implementations. However, if the graph is directed 'from' and 'to'
     * must match 'from' and 'to' of the edge exactly.
     *
     * @param from of edge
     * @param to   of edge
     * @return optional of edge
     * @throws NullPointerException if from or to is null
     */
    Optional<Edge<T>> findEdge(T from, T to);

    /**
     * finds edge with given id. If edge is not in this graph the optional is empty.
     *
     * @param id of edge
     * @return optional of edge
     * @throws NullPointerException if id is null
     */
    Optional<Edge<T>> findEdge(T id);

    /**
     * Ensures an edge exists between 'from' and 'to' vertices. If an edge does not exist it is created. If any of the
     * vertices do not exist they are created.
     *
     * <p>If this graph is undirected and contains an edge connecting 'to' and 'from' the edge will not be
     * created. Undirected graphs ignore 'from'/'to' order in edges.</p>
     *
     * @param from of edge
     * @param to   of edge
     * @return
     */
    Edge<T> edge(T from, T to);

    /**
     * Removes edge with given from and to.
     * @param from of edge
     * @param to of edge
     * @throws NullPointerException if from or to is null
     * @throws IllegalArgumentException if edge does not exist
     */
    void removeEdge(T from, T to);

    /**
     * Removes edge with given id
     * @param id of edge
     * @throws NullPointerException if id is null
     * @throws IllegalArgumentException if edge with id does not exist
     */
    void removeEdge(T id);

    /**
     * Returns the id of this graph. If the id is not set and empty optional is returned.
     * @return optional of id
     */
    Optional<T> getId();

    /**
     * Sets the id of this graph.
     * @param id of graph
     * @throws NullPointerException if id is null
     */
    void setId(T id);

    /**
     * Sets the id of this graph
     * @param id of graph
     * @return this graph
     * @throws NullPointerException if id is null
     */
    Graph<T> id(T id);

    /**
     * Returns true if this graph is directed.
     * @return if this graph is directed
     */
    boolean isDirected();

    /**
     * Returns {@link Optional} of a property. If the property does not exist the returned {@link Optional} is
     * empty.
     *
     * @param name of property
     * @return optional of named proeprty
     * @throws NullPointerException if name is null
     */
    Optional<Object> getProperty(String name);

    /**
     * Sets property on this graph
     *
     * @param name  of property
     * @param value of property
     * @throws NullPointerException if name or value is null
     */
    void setProperty(String name, Object value);

    /**
     * Sets property on this graph returning this graph
     *
     * @param name  of property
     * @param value of property
     * @return this graph
     * @throws NullPointerException if name or value is null
     */
    Graph<T> property(String name, Object value);

    /**
     * Removes property on this graph returning this graph
     *
     * @param name of property
     * @return this graph
     * @throws NullPointerException if name is null
     */
    Graph<T> removeProperty(String name);

    /**
     * Returns {@link Optional} of an edge property. If the property does not exist the returned {@link Optional} is
     * empty.
     *
     * @param name of property
     * @return optional of property
     * @throws NullPointerException if name is null
     */
    Optional<Object> getEdgeProperty(String name);

    /**
     * Sets edge property.
     *
     * @param name  of property
     * @param value of property
     * @throws NullPointerException if name or value is null
     */
    void setEdgeProperty(String name, Object value);

    /**
     * Sets edge property returning this graph
     *
     * @param name  of edge property
     * @param value of edge property
     * @return this graph
     * @throws NullPointerException if name or value is null
     */
    Graph<T> edgeProperty(String name, Object value);

    /**
     * Removes edge property returning this graph
     *
     * @param name of property
     * @return this graph
     * @throws NullPointerException if name is null
     */
    Graph<T> removeEdgeProperty(String name);

    /**
     * Returns {@link Optional} of a vertex property. If the property does not exist the returned {@link Optional} is
     * empty.
     *
     * @param name of property
     * @return optional of property
     * @throws NullPointerException if name is null
     */
    Optional<Object> getVertexProperty(String name);

    /**
     * Sets vertex property.
     *
     * @param name  of property
     * @param value of property
     * @throws NullPointerException if name or value is null
     */
    void setVertexProperty(String name, Object value);

    /**
     * Sets vertex property returning this graph
     *
     * @param name  of property
     * @param value of property
     * @return
     * @throws NullPointerException if name or value is null
     */
    Graph<T> vertexProperty(String name, Object value);

    /**
     * Removes vertex property returning this graph
     *
     * @param name of property
     * @return this graph
     * @throws NullPointerException if name is null
     */
    Graph<T> removeVertexProperty(String name);
}
