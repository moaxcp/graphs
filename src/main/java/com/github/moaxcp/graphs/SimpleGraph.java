package com.github.moaxcp.graphs;

import java.util.*;

/**
 * Interface for all simple graphs. This includes undirected, directed and specialized graphs such as directed acyclic
 * graph (DAG).
 */
public interface SimpleGraph {
    public interface Vertex {
        Object getId();
        void setId(Object id);
        Vertex id(Object id);
        Vertex connectsTo(Object to);
        Vertex connectsFrom(Object from);
        Edge edgeTo(Object to);
        Edge edgeFrom(Object from);
        Vertex toVertex(Object to);
        Vertex fromVertex(Object from);
        Set<Edge> traverseEdges();
        Set<Edge> adjacentEdges();
        Set<Edge> inEdges();
        Set<Edge> outEdges();
        Optional<Object> getProperty(String name);
        void setProperty(String name, Object value);
        Vertex property(String name, Object value);
        Vertex removeProperty(String name);
        Map<String, Object> inherited();
        Map<String, Object> local();
    }

    public interface Edge {
        Optional<Object> getId();
        void setId(Object id);
        Edge id(Object id);
        Object getFrom();
        void setFrom(Object from);
        Object from();
        Edge from(Object from);
        Object getTo();
        void setTo(Object to);
        Object to();
        Edge to(Object to);
        List<Object> endpoints();
        Vertex fromVertex();
        Vertex toVertex();
        boolean isDirected();
        Optional<Object> getProperty(String name);
        void setProperty(String name, Object value);
        Edge property(String name, Object value);
        Edge removeProperty(String name);
        Map<String, Object> inherited();
        Map<String, Object> local();
        boolean equals(Object from, Object to);
    }

    Map<Object, Vertex> getVertices();
    Set<Edge> getEdges();
    Map<Object, Edge> getEdgeIds();
    Optional<Vertex> findVertex(Object id);
    Vertex vertex(Object id);
    void removeVertex(Object id);
    Optional<Edge> findEdge(Object from, Object to);
    Optional<Edge> findEdge(Object id);
    Edge edge(Object from, Object to);
    void removeEdge(Object from, Object to);
    void removeEdge(Object id);
    Optional<Object> getId();
    void setId(Object id);
    SimpleGraph id(Object id);
    boolean isDirected();
    Optional<Object> getProperty(String name);
    void setProperty(String name, Object value);
    SimpleGraph property(String name, Object value);
    SimpleGraph removeProperty(String name);
    Optional<Object> getEdgeProperty(String name);
    void setEdgeProperty(String name, Object value);
    SimpleGraph edgeProperty(String name, Object value);
    SimpleGraph removeEdgeProperty(String name);
    Optional<Object> getVertexProperty(String name);
    void setVertexProperty(String name, Object value);
    SimpleGraph vertexProperty(String name, Object value);
    SimpleGraph removeVertexProperty(String name);
}
