package com.github.moaxcp.graphs;

import java.util.*;

/**
 * Interface for all simple graphs. This includes undirected, directed and specialized graphs such as directed acyclic
 * graph (DAG).
 */
public interface SimpleGraph<T> {
    public interface Vertex<T> {
        T getId();
        void setId(T id);
        Vertex<T> id(T id);
        Vertex<T> connectsTo(T to);
        Vertex<T> connectsFrom(T from);
        Edge<T> edgeTo(T to);
        Edge<T> edgeFrom(T from);
        Vertex<T> toVertex(T to);
        Vertex<T> fromVertex(T from);
        Set<Edge<T>> traverseEdges();
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
        boolean equals(T from, T to);
    }

    Map<T, Vertex<T>> getVertices();
    Set<Edge<T>> getEdges();
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
    SimpleGraph<T> id(T id);
    boolean isDirected();
    Optional<Object> getProperty(String name);
    void setProperty(String name, Object value);
    SimpleGraph<T> property(String name, Object value);
    SimpleGraph<T> removeProperty(String name);
    Optional<Object> getEdgeProperty(String name);
    void setEdgeProperty(String name, Object value);
    SimpleGraph<T> edgeProperty(String name, Object value);
    SimpleGraph<T> removeEdgeProperty(String name);
    Optional<Object> getVertexProperty(String name);
    void setVertexProperty(String name, Object value);
    SimpleGraph<T> vertexProperty(String name, Object value);
    SimpleGraph<T> removeVertexProperty(String name);
}
