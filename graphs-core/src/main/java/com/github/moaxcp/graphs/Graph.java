package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;
import java.util.stream.*;

/**
 * A graph is composed of vertices and edges. A vertex is able to connect to other vertices through edges. Edges
 * connect two vertices. A graph can be directed or undirected.
 *
 * <p>Vertices have an 'id' which uniquely identifies the vertex within the graph. Edges have a 'to' and 'from'
 * property which are the ids of the vertex endpoints. Edges are optionally identified.</p>
 *
 * <p>At all times the graph is valid event when removing connected vertices.</p>
 *
 * <p>Undirected graphs ignore 'from'/'to' order in edges.</p>
 *
 * <p>Once a vertex or edge is removed it can no longer modify the graph or be modified.</p>
 *
 * @param <ID> type of all identifiers in graph
 */
public interface Graph<ID> {
  /**
   * Vertex of graph. A vertex must have an identifier unique to all vertices in the graph.
   *
   * @param <ID> type of identifier
   */
  interface Vertex<ID> {
    /**
     * returns identifier of vertex.
     *
     * @return identifier of vertex
     */
    ID getId();

    /**
     * sets identifier of vertex.
     *
     * @param id identifier of vertex
     * @throws NullPointerException     if id is null
     * @throws IllegalArgumentException if a vertex with id already exists in the graph
     */
    void setId(ID id);

    /**
     * sets identifier of this vertex. This vertex is returned.
     *
     * @param id identifier of vertex.
     * @return this vertex
     * @throws NullPointerException     if id is null
     * @throws IllegalArgumentException if a vertex with id already exists in the graph
     */
    Vertex<ID> id(ID id);

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
    Vertex<ID> connectsTo(ID to);

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
    Vertex<ID> connectsFrom(ID from);

    /**
     * Returns the set of all edges connecting to this vertex.
     *
     * @return set of adjacent edges
     */
    Set<Edge<ID>> adjacentEdges();

    /**
     * Returns the set of edges connecting to this vertex where 'to' is the 'id' of this vertex
     *
     * @return incoming edges
     */
    Set<Edge<ID>> inEdges();

    /**
     * Returns the set of edges connecting to this vertex where 'from' is the 'id' of this vertex
     *
     * @return outgoing edges
     */
    Set<Edge<ID>> outEdges();

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
    <T> Optional<T> findProperty(String name);

    /**
     * Returns a property. If the property does not exist null is returned.
     *
     * <p>A property is resolved by first checking properties set on this vertex. If the local proeprty does not exist
     * vertexProperties in the graph are checked.</p>
     * @param <T> return type of property
     * @param name of property
     * @return value of named property
     * @throws NullPointerException if name is null
     */
    <T> T getProperty(String name);

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
    Vertex<ID> property(String name, Object value);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

    Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

    Vertex<ID> property(Map<String, Object> properties);

    /**
     * Removes property from local properties returning this vertex.
     *
     * @param name of property
     * @return this vertex
     * @throws NullPointerException if name is null
     */
    Vertex<ID> removeProperty(String name);

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

    /**
     * Removes this vertex from the graph.
     * @return tis vertex
     */
    Vertex<ID> remove();

    /**
     * Returns true if this vertex and 'id' vertex is connected.
     * @param id of vertex
     * @return true if the two vertices are connected
     */
    boolean connected(ID id);
  }

  /**
   * An edge between two vertices in a graph. Edge direction depends on graph type. If the graph is directed
   * {@link #isDirected()} will return true.
   *
   * @param <ID> type of identifier
   */
  interface Edge<ID> {
    /**
     * Returns optional identifier of this edge.
     *
     * @return optional identifier
     */
    Optional<ID> getId();

    /**
     * Sets identifier of this edge.
     *
     * @param id of edge
     * @throws NullPointerException if id is null
     */
    void setId(ID id);

    /**
     * Sets identifier of this edge.
     *
     * @param id of edge
     * @return this edge
     * @throws NullPointerException if id is null
     */
    Edge<ID> id(ID id);

    /**
     * Returns vertex id of 'from' endpoint.
     *
     * @return id of 'from' endpoint
     */
    ID getFrom();

    /**
     * Sets vertex id of 'from' endpoint. If vertex does not exist it is created.
     *
     * @param from endpoint
     * @throws NullPointerException if from is null
     */
    void setFrom(ID from);

    /**
     * Returns vertex id of 'from' endpoint.
     *
     * @return id of 'from' endpoint
     */
    ID from();

    /**
     * Sets vertex id of 'from' endpoint. If vertex does not exist it is created.
     *
     * @param from endpoint
     * @return this edge
     * @throws NullPointerException if from is null
     */
    Edge<ID> from(ID from);

    /**
     * Returns vertex id of 'to' endpoint.
     *
     * @return id of 'to' endpoint
     */
    ID getTo();

    /**
     * Sets vertex id of 'to' endpoint. If vertex does not exist it is created.
     *
     * @param to endpoint
     * @throws NullPointerException if to is null
     */
    void setTo(ID to);

    /**
     * Returns vertex id of 'to' endpoint.
     *
     * @return id of 'to' endpoint
     */
    ID to();

    /**
     * Sets vertex id of 'to' endpoint. If vertex does not exist it is created.
     *
     * @param to endpoint
     * @return this edge
     */
    Edge<ID> to(ID to);

    /**
     * Returns ordered pair of endpoint ids. 'from' endpoint id is always first followed by 'to' endpoint id.
     *
     * @return order pair of endpoints
     */
    List<ID> endpoints();

    /**
     * Returns the opposite endpoint of this edge
     * @param id of one endpoint
     * @return
     * @throws NullPointerException if id is null
     * @throws IllegalArgumentException if id is not one of the endpoints of this edge
     */
    default ID getOppositeEndpoint(@NonNull ID id) {
      ID from = getFrom();
      ID to = getTo();
      if(from.equals(id)) {
        return to;
      } else if(to.equals(id)) {
        return from;
      }
      throw new IllegalArgumentException("id \"" + id + "\" is not an endpoint of this edge.");
    }

    /**
     * Returns 'from' vertex
     *
     * @return 'from' vertex
     */
    Vertex<ID> fromVertex();

    /**
     * Returns 'to' vertex
     *
     * @return 'to' vertex
     */
    Vertex<ID> toVertex();

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
    <T> Optional<T> findProperty(String name);

    /**
     * Returns a property. If the property does not exist null is returned.
     *
     * <p>A property is resolved by first checking properties set on this edge. If the local proeprty does not exist
     * edgeProperties in the graph are checked.</p>
     * @param <T> return type of property
     * @param name of property
     * @return value of named property
     * @throws NullPointerException if name is null
     */
    <T> T getProperty(String name);

    /**
     * Sets the value of a local property.
     *
     * @param name  of property
     * @param value of property
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
    Edge<ID> property(String name, Object value);

    /**
     * Sets the value of 2 local properties returning this edge. Properties are added in order.
     *
     * @param name1  of first property
     * @param value1 of first property
     * @param name2  of second property
     * @param value2 of second property
     * @return this edge
     * @throws NullPointerException if name or value is null for any of the properties
     */
    Edge<ID> property(String name1, Object value1, String name2, Object value2);

    Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3);

    Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

    Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

    Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

    Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

    Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

    Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

    Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

    Edge<ID> property(Map<String, Object> properties);

    /**
     * Removes property from local properties returning this edge.
     *
     * @param name of property
     * @return this edge
     * @throws NullPointerException if name is null
     */
    Edge<ID> removeProperty(String name);

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

    /**
     * Removes this edge from the graph.
     * @return this edge
     */
    Edge<ID> remove();
  }

  /**
   * Returns an ordered map of all vertices in this graph. The key is the vertex id. Ordering is determined by
   * implementing class.
   *
   * @return ordered map of all vertices in this graph
   */
  Map<ID, Vertex<ID>> getVertices();

  /**
   * Returns an ordered collection of all edges in this graph.
   *
   * @return ordered collection of all edges
   */
  Collection<Edge<ID>> getEdges();

  /**
   * Returns a map of edge ids to edges.
   *
   * @return map of edge ids to edges
   */
  Map<ID, Edge<ID>> getEdgeIds();

  /**
   * Finds vertex with given id. If vertex is not in this graph the optional is empty.
   *
   * @param id of vertex
   * @return optional of vertex
   * @throws NullPointerException if id is null
   */
  Optional<Vertex<ID>> findVertex(ID id);

  /**
   * Ensure a vertex exists with id and returns the vertex. If the vertex does not exist it is created.
   *
   * @param id of vertex
   * @return created or existing vertex
   * @throws NullPointerException if id is null
   */
  Graph<ID> vertex(ID id);

  Graph<ID> vertex(ID id, String name, Object value);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

  Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

  Graph<ID> vertex(ID id, Map<String, Object> properties);

  Vertex<ID> getVertex(ID id);

  Vertex<ID> getVertex(ID id, String name, Object value);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

  Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

  Vertex<ID> getVertex(ID id, Map<String, Object> properties);

  /**
   * Removes vertex with given id. If edges connect to the vertex they are removed first.
   *
   * @param id of vertex
   * @throws NullPointerException if id is null
   */
  void removeVertex(ID id);

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
  Optional<Edge<ID>> findEdge(ID from, ID to);

  /**
   * finds edge with given id. If edge is not in this graph the optional is empty.
   *
   * @param id of edge
   * @return optional of edge
   * @throws NullPointerException if id is null
   */
  Optional<Edge<ID>> findEdge(ID id);

  /**
   * Ensures an edge exists between 'from' and 'to' vertices. If an edge does not exist it is created. If any of the
   * vertices do not exist they are created.
   *
   * <p>If this graph is undirected and contains an edge connecting 'to' and 'from' the edge will not be
   * created. Undirected graphs ignore 'from'/'to' order in edges.</p>
   *
   * @param from of edge
   * @param to   of edge
   * @return created or existing edge
   */
  Graph<ID> edge(ID from, ID to);

  /*
  TODO future methods for setting id when it becomes treated as a property and events are easier to handle..
  Graph<ID> edge(ID from, ID to, ID id);

  Graph<ID> edge(ID from, ID to, ID id, String name, Object value);

  Graph<ID> edge(ID from, ID to, ID id, Map<String, Object> properties);
  */

  Graph<ID> edge(ID from, ID to, String name, Object value);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

  Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

  Graph<ID> edge(ID from, ID to, Map<String, Object> properties);

  Edge<ID> getEdge(ID from, ID to);

  Edge<ID> getEdge(ID from, ID to, String name, Object value);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

  Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

  Edge<ID> getEdge(ID from, ID to, Map<String, Object> properties);

  /**
   * Removes edge with given from and to.
   *
   * @param from of edge
   * @param to   of edge
   * @throws NullPointerException     if from or to is null
   * @throws IllegalArgumentException if edge does not exist
   */
  void removeEdge(ID from, ID to);

  /**
   * Removes edge with given id
   *
   * @param id of edge
   * @throws NullPointerException     if id is null
   * @throws IllegalArgumentException if edge with id does not exist
   */
  void removeEdge(ID id);

  /**
   * Returns the id of this graph. If the id is not set and empty optional is returned.
   *
   * @return optional of id
   */
  Optional<ID> getId();

  /**
   * Sets the id of this graph.
   *
   * @param id of graph
   * @throws NullPointerException if id is null
   */
  void setId(ID id);

  /**
   * Sets the id of this graph
   *
   * @param id of graph
   * @return this graph
   * @throws NullPointerException if id is null
   */
  Graph<ID> id(ID id);

  /**
   * Returns true if this graph is directed.
   *
   * @return if this graph is directed
   */
  boolean isDirected();

  /**
   * Returns an unmodifiable map of properties for this graph.
   *
   * @return an unmodifiable map of properties for this graph
   */
  Map<String, Object> getProperties();

  /**
   * Returns {@link Optional} of a property. If the property does not exist the returned {@link Optional} is
   * empty.
   *
   * @param name of property
   * @return optional of named proeprty
   * @throws NullPointerException if name is null
   */
  <T> Optional<T> findProperty(String name);

  /**
   * Returns a property. If the property does not exist null is returned.
   *
   * @param <T> return type of property
   * @param name of property
   * @return value of named property
   * @throws NullPointerException if name is null
   */
  <T> T getProperty(String name);

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
  Graph<ID> property(String name, Object value);

  Graph<ID> property(Map<String, Object> properties);

  /**
   * Removes property on this graph returning this graph
   *
   * @param name of property
   * @return this graph
   * @throws NullPointerException if name is null
   */
  Graph<ID> removeProperty(String name);

  /**
   * Returns an unmodifiable map of edge properties.
   *
   * @return an unmodifiable map of edge properties
   */
  Map<String, Object> getEdgeProperties();

  /**
   * Returns {@link Optional} of an edge property. If the property does not exist the returned {@link Optional} is
   * empty.
   *
   * @param name of property
   * @return optional of property
   * @throws NullPointerException if name is null
   */
  <T> Optional<T> findEdgeProperty(String name);

  /**
   * Returns an edge property. If the property does not exist null is returned.
   *
   * @param name of property
   * @return property value
   * @throws NullPointerException if name is null
   */
  <T> T getEdgeProperty(String name);

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
  Graph<ID> edgeProperty(String name, Object value);

  Graph<ID> edgeProperty(Map<String, Object> properties);

  /**
   * Removes edge property returning this graph
   *
   * @param name of property
   * @return this graph
   * @throws NullPointerException if name is null
   */
  Graph<ID> removeEdgeProperty(String name);

  /**
   * Retruns an unmodifiable map of vertex properties.
   *
   * @return an unmodifiable map of vertex properties
   */
  Map<String, Object> getVertexProperties();

  /**
   * Returns {@link Optional} of a vertex property. If the property does not exist the returned {@link Optional} is
   * empty.
   *
   * @param name of property
   * @return optional of property
   * @throws NullPointerException if name is null
   */
  <T> Optional<T> findVertexProperty(String name);

  /**
   * Returns a vertex property. If the property does not exist null is returned.
   *
   * @param name of property
   * @return property value
   * @throws NullPointerException if name is null
   */
  <T> T getVertexProperty(String name);

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
   * @return created or existing edge
   * @throws NullPointerException if name or value is null
   */
  Graph<ID> vertexProperty(String name, Object value);

  Graph<ID> vertexProperty(Map<String, Object> properties);

  /**
   * Removes vertex property returning this graph
   *
   * @param name of property
   * @return this graph
   * @throws NullPointerException if name is null
   */
  Graph<ID> removeVertexProperty(String name);

  /**
   * Returns true if 'from' and 'to' vertices are connected.
   * @param from id of vertex
   * @param to id of vertex
   * @return true if the two vertices are connected
   */
  default boolean connected(@NonNull ID from, @NonNull ID to) {
    if(findVertex(from).isEmpty() || findVertex(to).isEmpty()) {
      return false;
    }
    if(findEdge(from, to).isPresent()) {
      return true;
    }

    Deque<ID> stack = new LinkedList<>();
    stack.push(from);
    Set<ID> visited = new HashSet<>();

    while(!stack.isEmpty()) {
      ID id = stack.pop();
      if(visited.contains(id)) {
        continue;
      }
      Vertex<ID> vertex = getVertex(id);
      visited.add(id);
      Set<Edge<ID>> edges;
      if(isDirected()) {
        edges = vertex.outEdges();
      } else {
        edges = vertex.adjacentEdges();
      }
      for(var edge : edges) {
        ID next = edge.getOppositeEndpoint(id);
        if(to.equals(next)) {
          return true;
        }
        stack.push(next);
      }
    }

    return false;
  }

  /**
   * Returns a post-order depth-first {@link Iterator} which returns every {@link Vertex} in this graph starting at
   * the provided vertices.
   * @throws NullPointerException if start is null or any id in start
   * @throws IllegalArgumentException if start contains ids that are not in the graph
   * @param start ids of traversal
   * @return
   */
  default Iterator<Vertex<ID>> postOrderIterator(ID... start) {
    return new PostOrderDepthFirstIterator<>(this, start);
  }

  /**
   * Returns a post-order depth-first {@link Stream} of every {@link Vertex} in this graph starting at the provided
   * vertices.
   * @throws NullPointerException if start is null or any id in start
   * @throws IllegalArgumentException if start contains ids that are not in the graph
   * @param start ids of traversal
   * @return
   */
  default Stream<Vertex<ID>> postOrderStream(ID... start) {
    var iterator = postOrderIterator(start);
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
  }

  /**
   * Returns a pre-order depth-first {@link Iterator} of every {@link Vertex} in this graph starting at the provided
   * vertices.
   * @throws NullPointerException if start is null or any id in start
   * @throws IllegalArgumentException if start contains ids that are not in the graph
   * @param start ids of traversal
   * @return
   */
  default Iterator<Vertex<ID>> preOrderIterator(ID... start) {
    return new PreOrderDepthFirstIterator<>(this, start);
  }

  /**
   * Returns a pre-order depth-first {@link Stream} of every {@link Vertex} in this graph starting at the provided
   * vertices.
   * @throws NullPointerException if start is null or any id in start
   * @throws IllegalArgumentException if start contains ids that are not in the graph
   * @param start ids of traversal
   * @return
   */
  default Stream<Vertex<ID>> preOrderStream(ID... start) {
    var iterator = preOrderIterator(start);
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
  }

  /**
   * Returns a breadth-first {@link Iterator} of every {@link Vertex} in this graph starting at the provided
   * vertices.
   * @throws NullPointerException if start is null or any id in start
   * @throws IllegalArgumentException if start contains ids that are not in the graph
   * @param start ids of traversal
   * @return
   */
  default Iterator<Vertex<ID>> breadthFirstIterator(ID... start) {
    return new BreadthFirstIterator<>(this, start);
  }

  /**
   * Returns a breadth-first {@link Stream} of every {@link Vertex} in this graph starting at the provided
   * vertices.
   * @throws NullPointerException if start is null or any id in start
   * @throws IllegalArgumentException if start contains ids that are not in the graph
   * @param start ids of traversal
   * @return
   */
  default Stream<Vertex<ID>> breadthFirstStream(ID... start) {
    var iterator = breadthFirstIterator(start);
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
  }

  /**
   * Returns true if this graph is empty.
   * @return
   */
  default boolean isEmpty() {
    return getVertices().isEmpty();
  }
}
