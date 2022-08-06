package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;
import java.util.stream.*;

/**
 * A graph is composed of vertices and edges. A vertex is able to connect to other vertices through edges. Edges
 * connect two vertices. A graph can be directed or undirected.
 *
 * <p>Vertices have an id which uniquely identifies the vertex within the graph. Edges have a source and target
 * property which are the ids of the vertex endpoints. Edges are optionally identified.</p>
 *
 * <p>At all times the graph is valid event when removing connected vertices.</p>
 *
 * <p>Undirected graphs ignore source/target order in edges.</p>
 *
 * <p>Once a vertex or edge is removed it can no longer modify the graph or be modified.</p>
 *
 * @param <ID> type of all identifiers in graph
 */
public interface PropertyGraph<ID> {
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
     * Ensures this vertex connects to the vertex with an 'id' property equal to the target parameter. The
     * edge or vertex is created if needed. This vertex is returned.
     *
     * <p>If a vertex is created its 'id' property will be set to the target parameter. If an edge is created the
     * source property is set to the 'id' of this vertex and the target property is set to the 'id' of the target
     * vertex.</p>
     *
     * <p>If this graph is undirected and contains an edge connecting this vertex with target the edge will not be
     * created. Undirected graphs ignore source/target order in edges.</p>
     *
     * @param target vertex id
     * @return this vertex
     * @throws NullPointerException if target is null
     */
    Vertex<ID> connectsTo(ID target);

    /**
     * Ensures this vertex connects to the vertex with an id property equal to the source parameter. The
     * edge or vertex is created if needed. This vertex is returned.
     *
     * <p>If a vertex is created its id property will be set to the source parameter. If an edge is created the
     * target property is set to the id of the source vertex and the target property is set to the id of this
     * vertex.</p>
     *
     * <p>If this graph is undirected and contains an edge connecting this vertex with source the edge will not be
     * created. Undirected graphs ignore source/target order in edges.</p>
     *
     * @param source vertex id
     * @return this vertex
     * @throws NullPointerException if source is null
     */
    Vertex<ID> connectsFrom(ID source);

    /**
     * Returns the set of all edges connecting to this vertex.
     *
     * @return set of adjacent edges
     */
    Set<Edge<ID>> adjacentEdges();

    /**
     * Returns the set of edges connecting to this vertex where target is the id of this vertex
     *
     * @return incoming edges
     */
    Set<Edge<ID>> inEdges();

    /**
     * Returns the set of edges connecting to this vertex where source is the id of this vertex
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
     * <p>A property is resolved by first checking properties set on this vertex. If the local property does not exist
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
     * @return this vertex
     */
    Vertex<ID> remove();

    /**
     * Returns true if this vertex and id vertex is connected.
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
     * Returns vertex id of source endpoint.
     *
     * @return id of source endpoint
     */
    ID getSource();

    /**
     * Sets vertex id of source endpoint. If vertex does not exist it is created.
     *
     * @param source endpoint
     * @throws NullPointerException if source is null
     */
    void setSource(ID source);

    /**
     * Returns vertex id of source endpoint.
     *
     * @return id of source endpoint
     */
    ID source();

    /**
     * Sets vertex id of source endpoint. If vertex does not exist it is created.
     *
     * @param source endpoint
     * @return this edge
     * @throws NullPointerException if source is null
     */
    Edge<ID> source(ID source);

    /**
     * Returns vertex id of target endpoint.
     *
     * @return id of target endpoint
     */
    ID getTarget();

    /**
     * Sets vertex id of source endpoint. If vertex does not exist it is created.
     *
     * @param target endpoint
     * @throws NullPointerException if target is null
     */
    void setTarget(ID target);

    /**
     * Returns vertex id of target endpoint.
     *
     * @return id of target endpoint
     */
    ID target();

    /**
     * Sets vertex id of target endpoint. If vertex does not exist it is created.
     *
     * @param target endpoint
     * @return this edge
     */
    Edge<ID> target(ID target);

    /**
     * Returns ordered pair of endpoint ids. source endpoint id is always first followed by target endpoint id.
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
      ID source = getSource();
      ID target = getTarget();
      if(source.equals(id)) {
        return target;
      } else if(target.equals(id)) {
        return source;
      }
      throw new IllegalArgumentException("id \"" + id + "\" is not an endpoint of this edge.");
    }

    /**
     * Returns source vertex
     *
     * @return source vertex
     */
    Vertex<ID> sourceVertex();

    /**
     * Returns target vertex
     *
     * @return target vertex
     */
    Vertex<ID> targetVertex();

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
  PropertyGraph<ID> vertex(ID id);

  PropertyGraph<ID> vertex(ID id, String name, Object value);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

  PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

  PropertyGraph<ID> vertex(ID id, Map<String, Object> properties);

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
   * Finds edge connecting source and target vertex. If edge is not in this graph the optional is empty. The order of
   * source and target does not matter in undirected implementations. However, if the graph is directed source and
   * target must match source and target of the edge exactly.
   *
   * @param source of edge
   * @param target   of edge
   * @return optional of edge
   * @throws NullPointerException if source or target is null
   */
  Optional<Edge<ID>> findEdge(ID source, ID target);

  /**
   * finds edge with given id. If edge is not in this graph the optional is empty.
   *
   * @param id of edge
   * @return optional of edge
   * @throws NullPointerException if id is null
   */
  Optional<Edge<ID>> findEdge(ID id);

  /**
   * Ensures an edge exists between source and target vertices. If an edge does not exist it is created. If any of the
   * vertices do not exist they are created.
   *
   * <p>If this graph is undirected and contains an edge connecting source and target the edge will not be created.
   * Undirected graphs ignore source/target order in edges.</p>
   *
   * @param source of edge
   * @param target   of edge
   * @return created or existing edge
   */
  PropertyGraph<ID> edge(ID source, ID target);

  /*
  TODO future methods for setting id when it becomes treated as a property and events are easier to handle..
  Graph<ID> edge(ID from, ID to, ID id);

  Graph<ID> edge(ID from, ID to, ID id, String name, Object value);

  Graph<ID> edge(ID from, ID to, ID id, Map<String, Object> properties);
  */

  PropertyGraph<ID> edge(ID source, ID target, String name, Object value);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

  PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

  PropertyGraph<ID> edge(ID source, ID target, Map<String, Object> properties);

  Edge<ID> getEdge(ID source, ID target);

  Edge<ID> getEdge(ID source, ID target, String name, Object value);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9);

  Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10);

  Edge<ID> getEdge(ID source, ID target, Map<String, Object> properties);

  /**
   * Removes edge with given from and to.
   *
   * @param source of edge
   * @param target   of edge
   * @throws NullPointerException     if source or target is null
   * @throws IllegalArgumentException if edge does not exist
   */
  void removeEdge(ID source, ID target);

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
  PropertyGraph<ID> id(ID id);

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
  PropertyGraph<ID> property(String name, Object value);

  PropertyGraph<ID> property(Map<String, Object> properties);

  /**
   * Removes property on this graph returning this graph
   *
   * @param name of property
   * @return this graph
   * @throws NullPointerException if name is null
   */
  PropertyGraph<ID> removeProperty(String name);

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
  PropertyGraph<ID> edgeProperty(String name, Object value);

  PropertyGraph<ID> edgeProperty(Map<String, Object> properties);

  /**
   * Removes edge property returning this graph
   *
   * @param name of property
   * @return this graph
   * @throws NullPointerException if name is null
   */
  PropertyGraph<ID> removeEdgeProperty(String name);

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
  PropertyGraph<ID> vertexProperty(String name, Object value);

  PropertyGraph<ID> vertexProperty(Map<String, Object> properties);

  /**
   * Removes vertex property returning this graph
   *
   * @param name of property
   * @return this graph
   * @throws NullPointerException if name is null
   */
  PropertyGraph<ID> removeVertexProperty(String name);

  /**
   * Returns true if source and target vertices are connected.
   * @param source id of vertex
   * @param target id of vertex
   * @return true if the two vertices are connected
   */
  default boolean connected(@NonNull ID source, @NonNull ID target) {
    if(findVertex(source).isEmpty() || findVertex(target).isEmpty()) {
      return false;
    }
    if(findEdge(source, target).isPresent()) {
      return true;
    }

    Deque<ID> stack = new LinkedList<>();
    stack.push(source);
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
        if(target.equals(next)) {
          return true;
        }
        stack.push(next);
      }
    }

    return false;
  }

  /**
   * Returns true if this graph is empty.
   * @return
   */
  default boolean isEmpty() {
    return getVertices().isEmpty();
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
   * Returns a reverse-post-order {@link Iterator} of every {@link Vertex} in this graph. This is a reverse of a
   * postOrderIterator starting at the provided vertices.
   * @throws NullPointerException if start is null or any id in start
   * @throws IllegalArgumentException if start contains ids that are not in the graph
   * @param start ids of traversal
   * @return
   */
  default Iterator<Vertex<ID>> reversePostOrderIterator(ID... start) {
    var iterator = postOrderIterator(start);
    var list = new LinkedList<Vertex<ID>>();
    while(iterator.hasNext()) {
      list.addFirst(iterator.next());
    }
    return new Iterator<Vertex<ID>>() {
      @Override
      public boolean hasNext() {
        return list.size() != 0;
      }

      @Override
      public Vertex<ID> next() {
        if(list.size() == 0) {
          throw new NoSuchElementException("Could not find next element.");
        }
        return list.removeFirst();
      }
    };
  }

  /**
   * Returns a reverse-post-order {@link Stream} of every {@link Vertex} in this graph. This is a reverse of a
   * postOrderIterator starting at the provided vertices.
   * @throws NullPointerException if start is null or any id in start
   * @throws IllegalArgumentException if start contains ids that are not in the graph
   * @param start ids of traversal
   * @return
   */
  default Stream<Vertex<ID>> reversePostOrderStream(ID... start) {
    var iterator = reversePostOrderIterator(start);
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
  }


  default EdgeTypeIterator<ID> edgeTypeIterator(ID... start) {
    return new EdgeTypeIterator<>(this, start);
  }
}
