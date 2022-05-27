package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;

/**
 * This class provides a partial implementation of the {@link PropertyGraph} interface.
 *
 * <p>Vertices and edges are stored in insertion order.</p>
 *
 * @param <ID> type of all identifiers in graph
 */
public abstract class AbstractPropertyGraph<ID> implements PropertyGraph<ID> {
  private LocalProperties properties;
  private LocalProperties vertexProperties;
  private LocalProperties edgeProperties;
  private Map<ID, Vertex<ID>> vertices;
  private Map<EdgeKey<ID>, Edge<ID>> edges;
  private Map<ID, Edge<ID>> edgeIds;
  private Map<ID, Set<Edge<ID>>> adjacentEdges;
  private Map<ID, Set<Edge<ID>>> inEdges;
  private Map<ID, Set<Edge<ID>>> outEdges;

  protected AbstractPropertyGraph() {
    vertices = new LinkedHashMap<>();
    edges = new LinkedHashMap<>();
    edgeIds = new LinkedHashMap<>();
    adjacentEdges = new LinkedHashMap<>();
    inEdges = new LinkedHashMap<>();
    outEdges = new LinkedHashMap<>();
    vertexProperties = new LocalProperties(new LinkedHashMap<>());
    edgeProperties = new LocalProperties(new LinkedHashMap<>());
    properties = new LocalProperties(new LinkedHashMap<>());
  }

  protected AbstractPropertyGraph(ID id) {
    this();
    properties.putProperties(linkedHashMap("id", id));
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name, Object value) {
    var map = new LinkedHashMap<String, Object>();
    map.put(name, value);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2) {
    var map = linkedHashMap(name1, value1);
    map.put(name2, value2);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    var map = linkedHashMap(name1, value1, name2, value2);
    map.put(name3, value3);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    var map = linkedHashMap(name1, value1, name2, value2, name3, value3);
    map.put(name4, value4);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    var map = linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4);
    map.put(name5, value5);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    var map = linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5);
    map.put(name6, value6);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    var map = linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6);
    map.put(name7, value7);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    var map = linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7);
    map.put(name8, value8);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    var map = linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8);
    map.put(name9, value9);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    var map = linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9);
    map.put(name10, value10);
    return map;
  }

  private static LinkedHashMap<String, Object> linkedHashMap(Map<String, Object> map) {
    return new LinkedHashMap<>(map);
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
  public Map<ID, Edge<ID>> getEdgeIds() {
    return unmodifiableMap(edgeIds);
  }

  @Override
  public Optional<Vertex<ID>> findVertex(@NonNull ID id) {
    return Optional.ofNullable(vertices.get(id));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id) {
    getVertex(id);
    return this;
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name, Object value) {
    return vertex(id, linkedHashMap(name, value));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
  }

  private PropertyGraph<ID> vertex(ID id, LinkedHashMap<String, Object> properties) {
    getVertex(id, properties);
    return this;
  }

  @Override
  public PropertyGraph<ID> vertex(ID id, Map<String, Object> properties) {
    getVertex(id, properties);
    return this;
  }

  @Override
  public Vertex<ID> getVertex(ID id) {
    var optional = findVertex(id);
    return optional.orElseGet(() -> addVertex(id, Map.of()));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name, Object value) {
    return getVertex(id, linkedHashMap(name, value));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
  }

  @Override
  public Vertex<ID> getVertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    return getVertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
  }

  @Override
  public Vertex<ID> getVertex(ID id, Map<String, Object> properties) {
    var optional = findVertex(id);
    optional.ifPresent(v -> v.property(properties));
    return optional.orElseGet(() -> addVertex(id, properties));
  }

  protected Vertex<ID> addVertex(ID id, Map<String, Object> properties) {
    var vertex = newVertex(id, properties, vertexProperties.local());
    vertices.put(vertex.getId(), vertex);
    return vertex;
  }

  @Override
  public void removeVertex(@NonNull ID id) {
    var optional = findVertex(id);
    if (optional.isEmpty()) {
      throw new IllegalArgumentException("vertex '" + id + "' not found.");
    }
    var adjacent = optional.get().adjacentEdges().stream()
      .map(e -> newEdgeKey(e.getSource(), e.getTarget()))
      .collect(toSet());
    for (var edge : adjacent) {
      removeEdge(edge.getSource(), edge.getTarget());
    }
    vertices.remove(id);
  }

  @Override
  public Optional<Edge<ID>> findEdge(@NonNull ID source, @NonNull ID target) {
    var key = newEdgeKey(source, target);
    var edge = edges.get(key);
    return Optional.ofNullable(edge);
  }

  @Override
  public Optional<Edge<ID>> findEdge(@NonNull ID id) {
    return Optional.ofNullable(edgeIds.get(id));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target) {
    getEdge(source, target);
    return this;
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name, Object value) {
    return edge(source, target, linkedHashMap(name, value));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    return edge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
  }

  private PropertyGraph<ID> edge(ID from, ID to, LinkedHashMap<String, Object> properties) {
    getEdge(from, to, properties);
    return this;
  }

  @Override
  public PropertyGraph<ID> edge(ID source, ID target, Map<String, Object> properties) {
    getEdge(source, target, properties);
    return this;
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target) {
    return findEdge(source, target).orElseGet(() -> addEdge(source, target, Map.of()));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name, Object value) {
    return getEdge(source, target, linkedHashMap(name, value));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    return getEdge(source, target, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
  }

  @Override
  public Edge<ID> getEdge(ID source, ID target, Map<String, Object> properties) {
    var optional = findEdge(source, target);
    optional.ifPresent(e -> e.property(properties));
    return optional.orElseGet(() -> addEdge(source, target, properties));
  }

  protected Edge<ID> newEdge(ID from, ID to, Map<String, Object> local, Map<String, Object> inherited) {
    return new SimpleEdge(from, to, local, inherited);
  }

  protected abstract EdgeKey<ID> newEdgeKey(ID from, ID to);

  protected Vertex<ID> newVertex(ID id, Map<String, Object> local, Map<String, Object> inherited) {
    return new SimpleVertex(id, local, inherited);
  }

  protected Edge<ID> addEdge(ID from, ID to, Map<String, Object> local) {
    vertex(from);
    vertex(to);
    var edge = newEdge(from, to, local, edgeProperties.local());
    var edgeKey = newEdgeKey(from, to);
    edges.put(edgeKey, edge);
    adjacentEdges.compute(edgeKey.getSource(), (k, v) -> mergeSet(edge, v));
    adjacentEdges.compute(edgeKey.getTarget(), (k, v) -> mergeSet(edge, v));
    inEdges.compute(edgeKey.getTarget(), (k, v) -> mergeSet(edge, v));
    outEdges.compute(edgeKey.getSource(), (k, v) -> mergeSet(edge, v));
    return edge;
  }

  private Set<Edge<ID>> mergeSet(Edge<ID> edge, Set<Edge<ID>> set) {
    if (set == null) {
      set = new LinkedHashSet<>();
      set.add(edge);
    } else {
      set.add(edge);
    }
    return set;
  }

  @Override
  public void removeEdge(@NonNull ID source, @NonNull ID target) {
    EdgeKey<ID> key = newEdgeKey(source, target);
    var edge = edges.remove(key);
    if (edge == null) {
      throw new IllegalArgumentException("edge from '" + source + "' to '" + target + "' not found.");
    }
    edge.getId().ifPresent(edgeIds::remove);
    var fromSet = adjacentEdges.get(source);
    fromSet.remove(edge);
    if (fromSet.isEmpty()) {
      adjacentEdges.remove(source);
    }
    var toSet = adjacentEdges.get(target);
    toSet.remove(edge);
    if (toSet.isEmpty()) {
      adjacentEdges.remove(target);
    }
    var outSet = outEdges.get(source);
    outSet.remove(edge);
    if (outSet.isEmpty()) {
      outEdges.remove(source);
    }
    var inSet = inEdges.get(target);
    inSet.remove(edge);
    if (inSet.isEmpty()) {
      inEdges.remove(target);
    }
  }

  @Override
  public void removeEdge(@NonNull ID id) {
    var optional = findEdge(id);
    optional.ifPresent(edge -> removeEdge(edge.getSource(), edge.getTarget()));
    if (optional.isEmpty()) {
      throw new IllegalArgumentException("edge with id '" + id + "' not found.");
    }
  }

  @Override
  public Optional<ID> getId() {
    return Optional.ofNullable((ID) properties.findProperty("id").orElse(null));
  }

  @Override
  public void setId(ID id) {
    properties.putProperties(linkedHashMap("id", id));
  }

  @Override
  public PropertyGraph<ID> id(ID id) {
    setId(id);
    return this;
  }

  @Override
  public Map<String, Object> getProperties() {
    return properties.local();
  }

  @Override
  public <T> Optional<T> findProperty(String name) {
    return properties.findProperty(name);
  }

  @Override
  public <T> T getProperty(String name) {
    return properties.getProperty(name);
  }

  @Override
  public void setProperty(String name, Object value) {
    property(name, value);
  }

  @Override
  public PropertyGraph<ID> property(String name, Object value) {
    property(linkedHashMap(name, value));
    return this;
  }

  @Override
  public PropertyGraph<ID> property(Map<String, Object> properties) {
    if(properties.containsKey("id")) {
      throw new IllegalArgumentException("id cannot be set as a property.");
    }
    this.properties.putProperties(properties);
    return this;
  }

  @Override
  public PropertyGraph<ID> removeProperty(String name) {
    property(name, null);
    return this;
  }

  @Override
  public Map<String, Object> getEdgeProperties() {
    return edgeProperties.local();
  }

  @Override
  public <T> Optional<T> findEdgeProperty(String name) {
    return edgeProperties.findProperty(name);
  }

  @Override
  public <T> T getEdgeProperty(String name) {
    return edgeProperties.getProperty(name);
  }

  @Override
  public void setEdgeProperty(String name, Object value) {
    edgeProperty(name, value);
  }

  @Override
  public PropertyGraph<ID> edgeProperty(String name, Object value) {
    edgeProperty(linkedHashMap(name, value));
    return this;
  }

  @Override
  public PropertyGraph<ID> edgeProperty(Map<String, Object> properties) {
    edgeProperties.putProperties(properties);
    return this;
  }

  @Override
  public PropertyGraph<ID> removeEdgeProperty(String name) {
    edgeProperty(linkedHashMap(name, null));
    return this;
  }

  @Override
  public Map<String, Object> getVertexProperties() {
    return vertexProperties.local();
  }

  @Override
  public <T> Optional<T> findVertexProperty(String name) {
    return vertexProperties.findProperty(name);
  }

  @Override
  public <T> T getVertexProperty(String name) {
    return vertexProperties.getProperty(name);
  }

  @Override
  public void setVertexProperty(String name, Object value) {
    vertexProperty(name, value);
  }

  @Override
  public PropertyGraph<ID> vertexProperty(String name, Object value) {
    vertexProperty(linkedHashMap(name, value));
    return this;
  }

  @Override
  public PropertyGraph<ID> vertexProperty(Map<String, Object> properties) {
    vertexProperties.putProperties(properties);
    return this;
  }

  @Override
  public PropertyGraph<ID> removeVertexProperty(String name) {
    vertexProperty(linkedHashMap(name, null));
    return this;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractPropertyGraph)) return false;
    AbstractPropertyGraph<?> that = (AbstractPropertyGraph<?>) o;
    return Objects.equals(properties, that.properties) && Objects.equals(vertexProperties, that.vertexProperties) && Objects.equals(edgeProperties, that.edgeProperties) && Objects.equals(vertices, that.vertices) && Objects.equals(edges, that.edges);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(properties, vertexProperties, edgeProperties, vertices, edges);
  }

  @ToString
  public class SimpleEdge implements Edge<ID> {
    private InheritedProperties properties;

    protected SimpleEdge(ID source, ID target, Map<String, Object> local, Map<String, Object> inherited) {
      properties = new InheritedProperties(local, inherited);
      properties.putProperty("source", source);
      properties.putProperty("target", target);
    }

    private void check() {
      EdgeKey<ID> key = newEdgeKey(getSource(), getTarget());
      if (!edges.containsKey(key)) {
        throw new IllegalStateException("Edge is not in graph.");
      }
    }

    @Override
    public Map<String, Object> inherited() {
      return properties.inherited();
    }

    @Override
    public Map<String, Object> local() {
      return properties.local();
    }

    @Override
    public final Optional<ID> getId() {
      return (Optional<ID>) properties.findProperty("id");
    }

    @Override
    public void setId(ID id) {
      check();
      getId().ifPresent(a -> edgeIds.remove(a));
      if (id != null) {
        edgeIds.put(id, this);
      }
      if(id != null) {
        properties.putProperty("id", id);
      } else if(getId().isPresent()) {
        properties.removeProperty("id");
      }
    }

    @Override
    public final Edge<ID> id(ID id) {
      setId(id);
      return this;
    }

    @Override
    public final ID getSource() {
      return (ID) properties.findProperty("source").orElseThrow(() -> new IllegalStateException("source was not set as a property."));
    }

    @Override
    public void setSource(@NonNull ID source) {
      check();
      EdgeKey<ID> oldKey = newEdgeKey(getSource(), getTarget());
      edges.remove(oldKey);
      adjacentEdges.get(getSource()).remove(this);
      inEdges.get(getTarget()).remove(this);
      outEdges.get(getSource()).remove(this);
      vertex(source);
      adjacentEdges.compute(source, (k, v) -> mergeSet(this, v));
      inEdges.compute(getTarget(), (k, v) -> mergeSet(this, v));
      outEdges.compute(source, (k, v) -> mergeSet(this, v));
      properties.putProperty("source", source);
      EdgeKey<ID> key = newEdgeKey(getSource(), getTarget());
      edges.put(key, this);
    }

    @Override
    public Edge<ID> source(ID source) {
      setSource(source);
      return this;
    }

    @Override
    public final ID source() {
      return getSource();
    }

    @Override
    public final ID getTarget() {
      return (ID) properties.findProperty("target").orElseThrow(() -> new IllegalStateException("target was not set as a property."));
    }

    @Override
    public void setTarget(@NonNull ID target) {
      check();
      EdgeKey<ID> oldKey = newEdgeKey(getSource(), getTarget());
      edges.remove(oldKey);
      adjacentEdges.get(getTarget()).remove(this);
      inEdges.get(getTarget()).remove(this);
      outEdges.get(getSource()).remove(this);
      vertex(target);
      adjacentEdges.compute(target, (k, v) -> mergeSet(this, v));
      inEdges.compute(target, (k, v) -> mergeSet(this, v));
      outEdges.compute(getSource(), (k, v) -> mergeSet(this, v));
      properties.putProperty("target", target);
      EdgeKey<ID> key = newEdgeKey(getSource(), getTarget());
      edges.put(key, this);
    }

    @Override
    public final Edge<ID> target(ID target) {
      setTarget(target);
      return this;
    }

    @Override
    public final ID target() {
      return getTarget();
    }

    @Override
    public final boolean isDirected() {
      return AbstractPropertyGraph.this.isDirected();
    }

    @Override
    public final List<ID> endpoints() {
      return List.of(getSource(), getTarget());
    }

    @Override
    public final Vertex<ID> sourceVertex() {
      check();
      return getVertex(getSource());
    }

    @Override
    public final Vertex<ID> targetVertex() {
      check();
      return getVertex(getTarget());
    }

    @Override
    public <T> Optional<T> findProperty(String name) {
      return properties.findProperty(name);
    }

    @Override
    public <T> T getProperty(String name) {
      return properties.getProperty(name);
    }

    @Override
    public void setProperty(String name, Object value) {
      property(name, value);
    }

    @Override
    public Edge<ID> property(String name, Object value) {
      return property(linkedHashMap(name, value));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2) {
      return property(linkedHashMap(name1, value1, name2, value2));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
    }

    @Override
    public Edge<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
    }

    @Override
    public Edge<ID> property(Map<String, Object> properties) {
      check();
      if(properties.containsKey("id")) {
        throw new IllegalArgumentException("id cannot be set as a property.");
      } else if(properties.containsKey("source")) {
        throw new IllegalArgumentException("source cannot be set as a property.");
      } else if(properties.containsKey("target")) {
        throw new IllegalArgumentException("target cannot be set as a property.");
      }
      this.properties.putProperties(properties);
      return this;
    }

    @Override
    public Edge<ID> removeProperty(String name) {
      check();
      property(name, null);
      return this;
    }

    @Override
    public Edge<ID> remove() {
      AbstractPropertyGraph.this.removeEdge(getSource(), getTarget());
      return this;
    }

    @Override
    public final boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof AbstractPropertyGraph<?>.SimpleEdge)) return false;
      SimpleEdge that = (SimpleEdge) o;
      return Objects.equals(local(), that.local()) &&
        Objects.equals(inherited(), that.inherited());
    }

    @Override
    public final int hashCode() {
      return Objects.hash(local(), inherited());
    }
  }

  @ToString
  public class SimpleVertex implements Vertex<ID> {
    InheritedProperties properties;

    protected SimpleVertex(@NonNull ID id, Map<String, Object> local, Map<String, Object> inherited) {
      if(local.containsKey("id")) {
        throw new IllegalArgumentException("id cannot be set as a property.");
      }
      properties = new InheritedProperties(local, inherited);
      properties.putProperties(linkedHashMap("id", id));
    }

    private void check() {
      if (!vertices.containsKey(getId())) {
        throw new IllegalStateException("Vertex is not in graph.");
      }
    }

    @Override
    public Map<String, Object> inherited() {
      return properties.inherited();
    }

    @Override
    public Map<String, Object> local() {
      return properties.local();
    }

    public ID getId() {
      return (ID) properties.findProperty("id").orElseThrow(() -> new IllegalStateException("id was not set as a property."));
    }

    @Override
    public void setId(@NonNull ID id) {
      check();
      if (findVertex(id).isPresent()) {
        throw new IllegalArgumentException("vertex with id a already exists.");
      }
      Set<? extends Edge<ID>> adjacent = new LinkedHashSet<>(adjacentEdges());
      Object oldId = getId();
      vertices.remove(oldId);
      properties.putProperties(linkedHashMap("id", id));
      vertices.put(id, this);
      for (Edge<ID> edge : adjacent) {
        if (edge.getSource().equals(oldId)) {
          edge.setSource(id);
        }
        if (edge.getTarget().equals(oldId)) {
          edge.setTarget(id);
        }
      }
    }

    @Override
    public Vertex<ID> id(ID id) {
      setId(id);
      return this;
    }

    @Override
    public <T> Optional<T> findProperty(String name) {
      return properties.findProperty(name);
    }

    @Override
    public <T> T getProperty(String name) {
      return properties.getProperty(name);
    }

    @Override
    public void setProperty(String name, Object value) {
      property(name, value);
    }

    @Override
    public Vertex<ID> property(String name, Object value) {
      return property(linkedHashMap(name, value));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2) {
      return property(linkedHashMap(name1, value1, name2, value2));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
    }

    @Override
    public Vertex<ID> property(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
      return property(linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
    }

    @Override
    public Vertex<ID> property(Map<String, Object> properties) {
      check();
      if(properties.containsKey("id")) {
        throw new IllegalArgumentException("id cannot be set as a property.");
      }
      this.properties.putProperties(properties);
      return this;
    }

    @Override
    public Vertex<ID> removeProperty(String name) {
      property(name, null);
      return this;
    }

    @Override
    public Vertex<ID> connectsTo(ID target) {
      check();
      edge(getId(), target);
      return this;
    }

    @Override
    public Vertex<ID> connectsFrom(ID source) {
      check();
      edge(source, getId());
      return this;
    }

    @Override
    public Set<Edge<ID>> adjacentEdges() {
      check();
      Set<Edge<ID>> edges = adjacentEdges.get(getId());
      if (edges == null) {
        return emptySet();
      }
      return unmodifiableSet(edges);
    }

    @Override
    public Set<Edge<ID>> inEdges() {
      check();
      Set<Edge<ID>> edges = inEdges.get(getId());
      if (edges == null) {
        return emptySet();
      }
      return unmodifiableSet(edges);
    }

    @Override
    public Set<Edge<ID>> outEdges() {
      check();
      Set<Edge<ID>> edges = outEdges.get(getId());
      if (edges == null) {
        return emptySet();
      }
      return unmodifiableSet(edges);
    }

    public Vertex<ID> remove() {
      AbstractPropertyGraph.this.removeVertex(getId());
      return this;
    }

    @Override
    public boolean connected(@NonNull ID id) {
      return AbstractPropertyGraph.this.connected(getId(), id);
    }

    @Override
    public final boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof AbstractPropertyGraph<?>.SimpleVertex)) return false;
      SimpleVertex that = (SimpleVertex) o;
      return Objects.equals(local(), that.local()) &&
        Objects.equals(inherited(), that.inherited());
    }

    @Override
    public final int hashCode() {
      return Objects.hash(local(), inherited());
    }
  }
}
