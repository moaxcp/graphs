package com.github.moaxcp.graphs;

import java.util.*;

import static java.util.Collections.*;
import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

/**
 * This class provides a partial implementation of the {@link Graph} interface.
 *
 * <p>Vertices and edges are stored in insertion order.</p>
 *
 * @param <ID> type of all identifiers in graph
 */
public abstract class AbstractGraph<ID> implements Graph<ID> {
  private static final String ID_MUST_NOT_BE_NULL = "id must not be null.";
  private LocalProperties properties;
  private LocalProperties vertexProperties;
  private LocalProperties edgeProperties;
  private Map<ID, Vertex<ID>> vertices;
  private Map<EdgeKey<ID>, Edge<ID>> edges;
  private Map<ID, Edge<ID>> edgeIds;
  private Map<ID, Set<Edge<ID>>> adjacentEdges;
  private Map<ID, Set<Edge<ID>>> inEdges;
  private Map<ID, Set<Edge<ID>>> outEdges;

  protected AbstractGraph() {
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

  protected AbstractGraph(ID id) {
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
  public Optional<Vertex<ID>> findVertex(ID id) {
    return Optional.ofNullable(vertices.get(id));
  }

  @Override
  public Graph<ID> vertex(ID id) {
    getVertex(id);
    return this;
  }

  @Override
  public Graph<ID> vertex(ID id, String name, Object value) {
    return vertex(id, linkedHashMap(name, value));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
  }

  @Override
  public Graph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    return vertex(id, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
  }

  private Graph<ID> vertex(ID id, LinkedHashMap<String, Object> properties) {
    getVertex(id, properties);
    return this;
  }

  @Override
  public Graph<ID> vertex(ID id, Map<String, Object> properties) {
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
  public void removeVertex(ID id) {
    Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
    var optional = findVertex(id);
    if (optional.isEmpty()) {
      throw new IllegalArgumentException("vertex '" + id + "' not found.");
    }
    var adjacent = optional.get().adjacentEdges().stream()
      .map(e -> newEdgeKey(e.getFrom(), e.getTo()))
      .collect(toSet());
    for (var edge : adjacent) {
      removeEdge(edge.getFrom(), edge.getTo());
    }
    vertices.remove(id);
  }

  @Override
  public Optional<Edge<ID>> findEdge(ID from, ID to) {
    requireNonNull(from, "from must not be null.");
    requireNonNull(to, "to must not be null.");
    var key = newEdgeKey(from, to);
    var edge = edges.get(key);
    return Optional.ofNullable(edge);
  }

  @Override
  public Optional<Edge<ID>> findEdge(ID id) {
    requireNonNull(id, "id must not be null.");
    return Optional.ofNullable(edgeIds.get(id));
  }

  @Override
  public Graph<ID> edge(ID from, ID to) {
    getEdge(from, to);
    return this;
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name, Object value) {
    return edge(from, to, linkedHashMap(name, value));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
  }

  @Override
  public Graph<ID> edge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    return edge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
  }

  private Graph<ID> edge(ID from, ID to, LinkedHashMap<String, Object> properties) {
    getEdge(from, to, properties);
    return this;
  }

  @Override
  public Graph<ID> edge(ID from, ID to, Map<String, Object> properties) {
    getEdge(from, to, properties);
    return this;
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to) {
    return findEdge(from, to).orElseGet(() -> addEdge(from, to, Map.of()));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name, Object value) {
    return getEdge(from, to, linkedHashMap(name, value));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    return getEdge(from, to, linkedHashMap(name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10));
  }

  @Override
  public Edge<ID> getEdge(ID from, ID to, Map<String, Object> properties) {
    var optional = findEdge(from, to);
    optional.ifPresent(e -> e.property(properties));
    return optional.orElseGet(() -> addEdge(from, to, properties));
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
    adjacentEdges.compute(edgeKey.getFrom(), (k, v) -> mergeSet(edge, v));
    adjacentEdges.compute(edgeKey.getTo(), (k, v) -> mergeSet(edge, v));
    inEdges.compute(edgeKey.getTo(), (k, v) -> mergeSet(edge, v));
    outEdges.compute(edgeKey.getFrom(), (k, v) -> mergeSet(edge, v));
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
  public void removeEdge(ID from, ID to) {
    requireNonNull(from, "from must not be null.");
    requireNonNull(to, "to must not be null.");
    EdgeKey<ID> key = newEdgeKey(from, to);
    var edge = edges.remove(key);
    if (edge == null) {
      throw new IllegalArgumentException("edge from '" + from + "' to '" + to + "' not found.");
    }
    edge.getId().ifPresent(edgeIds::remove);
    var fromSet = adjacentEdges.get(from);
    fromSet.remove(edge);
    if (fromSet.isEmpty()) {
      adjacentEdges.remove(from);
    }
    var toSet = adjacentEdges.get(to);
    toSet.remove(edge);
    if (toSet.isEmpty()) {
      adjacentEdges.remove(to);
    }
    var outSet = outEdges.get(from);
    outSet.remove(edge);
    if (outSet.isEmpty()) {
      outEdges.remove(from);
    }
    var inSet = inEdges.get(to);
    inSet.remove(edge);
    if (inSet.isEmpty()) {
      inEdges.remove(to);
    }
  }

  @Override
  public void removeEdge(ID id) {
    requireNonNull(id, ID_MUST_NOT_BE_NULL);
    var optional = findEdge(id);
    optional.ifPresent(edge -> removeEdge(edge.getFrom(), edge.getTo()));
    if (optional.isEmpty()) {
      throw new IllegalArgumentException("edge with id '" + id + "' not found.");
    }
  }

  @Override
  public Optional<ID> getId() {
    return Optional.ofNullable((ID) properties.getProperty("id").orElse(null));
  }

  @Override
  public void setId(ID id) {
    properties.putProperties(linkedHashMap("id", id));
  }

  @Override
  public Graph<ID> id(ID id) {
    setId(id);
    return this;
  }

  @Override
  public Map<String, Object> getProperties() {
    return properties.local();
  }

  @Override
  public Optional<Object> getProperty(String name) {
    return properties.getProperty(name);
  }

  @Override
  public void setProperty(String name, Object value) {
    property(name, value);
  }

  @Override
  public Graph<ID> property(String name, Object value) {
    property(linkedHashMap(name, value));
    return this;
  }

  @Override
  public Graph<ID> property(Map<String, Object> properties) {
    if(properties.containsKey("id")) {
      throw new IllegalArgumentException("id cannot be set as a property.");
    }
    this.properties.putProperties(properties);
    return this;
  }

  @Override
  public Graph<ID> removeProperty(String name) {
    property(name, null);
    return this;
  }

  @Override
  public Map<String, Object> getEdgeProperties() {
    return edgeProperties.local();
  }

  @Override
  public Optional<Object> getEdgeProperty(String name) {
    return edgeProperties.getProperty(name);
  }

  @Override
  public void setEdgeProperty(String name, Object value) {
    edgeProperty(name, value);
  }

  @Override
  public Graph<ID> edgeProperty(String name, Object value) {
    edgeProperty(linkedHashMap(name, value));
    return this;
  }

  @Override
  public Graph<ID> edgeProperty(Map<String, Object> properties) {
    edgeProperties.putProperties(properties);
    return this;
  }

  @Override
  public Graph<ID> removeEdgeProperty(String name) {
    edgeProperty(linkedHashMap(name, null));
    return this;
  }

  @Override
  public Map<String, Object> getVertexProperties() {
    return vertexProperties.local();
  }

  @Override
  public Optional<Object> getVertexProperty(String name) {
    return vertexProperties.getProperty(name);
  }

  @Override
  public void setVertexProperty(String name, Object value) {
    vertexProperty(name, value);
  }

  @Override
  public Graph<ID> vertexProperty(String name, Object value) {
    vertexProperty(linkedHashMap(name, value));
    return this;
  }

  @Override
  public Graph<ID> vertexProperty(Map<String, Object> properties) {
    vertexProperties.putProperties(properties);
    return this;
  }

  @Override
  public Graph<ID> removeVertexProperty(String name) {
    vertexProperty(linkedHashMap(name, null));
    return this;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractGraph)) return false;
    AbstractGraph<?> that = (AbstractGraph<?>) o;
    return Objects.equals(properties, that.properties) && Objects.equals(vertexProperties, that.vertexProperties) && Objects.equals(edgeProperties, that.edgeProperties) && Objects.equals(vertices, that.vertices) && Objects.equals(edges, that.edges);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(properties, vertexProperties, edgeProperties, vertices, edges);
  }

  public class SimpleEdge implements Edge<ID> {
    private ID id;
    private ID from;
    private ID to;
    private InheritedProperties properties;

    protected SimpleEdge(ID from, ID to, Map<String, Object> local, Map<String, Object> inherited) {
      this.from = from;
      this.to = to;
      properties = new InheritedProperties(local, inherited);
    }

    private void check() {
      EdgeKey<ID> key = newEdgeKey(from, to);
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
      return Optional.ofNullable(id);
    }

    @Override
    public void setId(ID id) {
      check();
      edgeIds.remove(this.id);
      if (id != null) {
        edgeIds.put(id, this);
      }
      this.id = id;
    }

    @Override
    public final Edge<ID> id(ID id) {
      setId(id);
      return this;
    }

    @Override
    public final ID getFrom() {
      return from;
    }

    @Override
    public void setFrom(ID from) {
      check();
      requireNonNull(from, "from must not be null.");
      EdgeKey<ID> oldKey = newEdgeKey(this.from, this.to);
      edges.remove(oldKey);
      adjacentEdges.get(this.from).remove(this);
      inEdges.get(to).remove(this);
      outEdges.get(this.from).remove(this);
      vertex(from);
      adjacentEdges.compute(from, (k, v) -> mergeSet(this, v));
      inEdges.compute(to, (k, v) -> mergeSet(this, v));
      outEdges.compute(from, (k, v) -> mergeSet(this, v));
      this.from = from;
      EdgeKey<ID> key = newEdgeKey(this.from, this.to);
      edges.put(key, this);
    }

    @Override
    public Edge<ID> from(ID from) {
      setFrom(from);
      return this;
    }

    @Override
    public final ID from() {
      return getFrom();
    }

    @Override
    public final ID getTo() {
      return to;
    }

    @Override
    public void setTo(ID to) {
      check();
      Objects.requireNonNull(to, "to must not be null.");
      EdgeKey<ID> oldKey = newEdgeKey(this.from, this.to);
      edges.remove(oldKey);
      adjacentEdges.get(this.to).remove(this);
      inEdges.get(this.to).remove(this);
      outEdges.get(this.from).remove(this);
      vertex(to);
      adjacentEdges.compute(to, (k, v) -> mergeSet(this, v));
      inEdges.compute(to, (k, v) -> mergeSet(this, v));
      outEdges.compute(from, (k, v) -> mergeSet(this, v));
      this.to = to;
      EdgeKey<ID> key = newEdgeKey(this.from, this.to);
      edges.put(key, this);
    }

    @Override
    public final Edge<ID> to(ID to) {
      setTo(to);
      return this;
    }

    @Override
    public final ID to() {
      return getTo();
    }

    @Override
    public final boolean isDirected() {
      return AbstractGraph.this.isDirected();
    }

    @Override
    public final List<ID> endpoints() {
      return List.of(from, to);
    }

    @Override
    public final Vertex<ID> fromVertex() {
      check();
      return getVertex(getFrom());
    }

    @Override
    public final Vertex<ID> toVertex() {
      check();
      return getVertex(getTo());
    }

    @Override
    public Optional<Object> getProperty(String name) {
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
      this.properties.putProperties(properties);
      return this;
    }

    @Override
    public Edge<ID> removeProperty(String name) {
      check();
      properties.removeProperty(name);
      return this;
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

  public class SimpleVertex implements Vertex<ID> {
    InheritedProperties properties;

    protected SimpleVertex(ID id, Map<String, Object> local, Map<String, Object> inherited) {
      Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
      Objects.requireNonNull(local, "local must not be null.");
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
      return (ID) properties.getProperty("id").get();
    }

    @Override
    public void setId(ID id) {
      check();
      Objects.requireNonNull(id, ID_MUST_NOT_BE_NULL);
      if (findVertex(id).isPresent()) {
        throw new IllegalArgumentException("vertex with id a already exists.");
      }
      Set<? extends Edge<ID>> adjacent = new LinkedHashSet<>(adjacentEdges());
      Object oldId = getId();
      vertices.remove(oldId);
      properties.putProperties(linkedHashMap("id", id));
      vertices.put(id, this);
      for (Edge<ID> edge : adjacent) {
        if (edge.getFrom().equals(oldId)) {
          edge.setFrom(id);
        }
        if (edge.getTo().equals(oldId)) {
          edge.setTo(id);
        }
      }
    }

    @Override
    public Vertex<ID> id(ID id) {
      setId(id);
      return this;
    }

    @Override
    public Optional<Object> getProperty(String name) {
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
    public Vertex<ID> connectsTo(ID to) {
      check();
      edge(getId(), to);
      return this;
    }

    @Override
    public Vertex<ID> connectsFrom(ID s) {
      check();
      edge(s, getId());
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

    @Override
    public final boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof AbstractGraph<?>.SimpleVertex)) return false;
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
