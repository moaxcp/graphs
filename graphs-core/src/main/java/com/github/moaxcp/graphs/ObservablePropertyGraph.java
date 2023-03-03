package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.events.*;
import java.util.*;
import lombok.NonNull;

public abstract class ObservablePropertyGraph<ID> extends AbstractPropertyGraph<ID> {
  private final List<PropertyGraphObserver<ID>> observers = new ArrayList<>();

  public ObservablePropertyGraph() {
    super();
  }

  public ObservablePropertyGraph(ID id) {
    super(id);
  }

  public ObservablePropertyGraph(@NonNull PropertyGraphObserver<ID>... observers) {
    addObserver(observers);
  }

  public ObservablePropertyGraph(ID id, PropertyGraphObserver<ID>... observers) {
    super(id);
    addObserver(observers);
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id) {
    super.vertex(id);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name, Object value) {
    super.vertex(id, name, value);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2) {
    super.vertex(id, name1, value1, name2, value2);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    super.vertex(id, name1, value1, name2, value2, name3, value3);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    super.vertex(id, name1, value1, name2, value2, name3, value3, name4, value4);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    super.vertex(id, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    super.vertex(id, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    super.vertex(id, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    super.vertex(id, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    super.vertex(id, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    super.vertex(id, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertex(ID id, Map<String, Object> properties) {
    super.vertex(id, properties);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target) {
    super.edge(source, target);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name, Object value) {
    super.edge(source, target, name, value);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2) {
    super.edge(source, target, name1, value1, name2, value2);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3) {
    super.edge(source, target, name1, value1, name2, value2, name3, value3);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4) {
    super.edge(source, target, name1, value1, name2, value2, name3, value3, name4, value4);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5) {
    super.edge(source, target, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6) {
    super.edge(source, target, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7) {
    super.edge(source, target, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8) {
    super.edge(source, target, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9) {
    super.edge(source, target, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7, String name8, Object value8, String name9, Object value9, String name10, Object value10) {
    super.edge(source, target, name1, value1, name2, value2, name3, value3, name4, value4, name5, value5, name6, value6, name7, value7, name8, value8, name9, value9, name10, value10);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edge(ID source, ID target, Map<String, Object> properties) {
    super.edge(source, target, properties);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> property(String name, Object value) {
    super.property(name, value);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> removeProperty(String name) {
    super.removeProperty(name);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edgeProperty(String name, Object value) {
    super.edgeProperty(name, value);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> removeEdgeProperty(String name) {
    super.removeEdgeProperty(name);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertexProperty(String name, Object value) {
    super.vertexProperty(name, value);
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> removeVertexProperty(String name) {
    super.removeVertexProperty(name);
    return this;
  }

  public List<PropertyGraphObserver<ID>> getObservers() {
    return Collections.unmodifiableList(observers);
  }


  @SuppressWarnings("java:S2583")
  @SafeVarargs
  public final ObservablePropertyGraph<ID> addObserver(@NonNull PropertyGraphObserver<ID>... observers) {
    for (var observer : observers) {
      if (observer == null) {
        throw new NullPointerException("observer must not be null.");
      }
      this.observers.add(observer);
    }
    return this;
  }

  public ObservablePropertyGraph<ID> removeObserver(PropertyGraphObserver<ID> observer) {
    observers.remove(observer);
    return this;
  }

  void send(GraphEvent<ID> event) {
    for (var observable : observers) {
      observable.graphEvent(event);
    }
  }

  @Override
  public void setId(ID id) {
    Optional<ID> oldId = getId();
    super.setId(id);
    send(GraphPropertyEvent.<ID>builder()
        .graphId(oldId.orElse(null))
        .newId(id)
        .build());
  }

  @Override
  public ObservablePropertyGraph<ID> property(Map<String, Object> properties) {
    var id = getId().orElse(null);
    super.property(properties);
    send(GraphPropertyEvent.<ID>builder()
        .graphId(id)
        .properties(new LinkedHashMap<>(properties))
        .build());
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> edgeProperty(Map<String, Object> properties) {
    super.edgeProperty(properties);
    send(EdgeInheritedPropertyEvent.<ID>builder()
        .graphId(getId().orElse(null))
        .properties(new LinkedHashMap<>(properties))
        .build());
    return this;
  }

  @Override
  public ObservablePropertyGraph<ID> vertexProperty(Map<String, Object> properties) {
    Map<String, Object> oldValues = new LinkedHashMap<>(getVertexProperties());
    super.vertexProperty(properties);
    send(VertexInheritedPropertyEvent.<ID>builder()
        .graphId(getId().orElse(null))
        .properties(new LinkedHashMap<>(properties))
        .build());
    return this;
  }

  @Override
  protected Vertex<ID> newVertex(ID id, Map<String, Object> local, Map<String, Object> inherited) {
    return new EventVertex(id, local, inherited);
  }

  @Override
  protected Edge<ID> newEdge(ID from, ID to, Map<String, Object> local, Map<String, Object> inherited) {
    return new EventEdge(from, to, local, inherited);
  }

  @Override
  protected Edge<ID> addEdge(ID from, ID to, Map<String, Object> local) {
    var edge = super.addEdge(from, to, local);
    send(EdgeCreatedEvent.<ID>builder()
        .graphId(getId().orElse(null))
        .sourceId(from)
        .targetId(to)
        .properties(local)
        .build());
    return edge;
  }

  @Override
  public void removeEdge(ID source, ID target) {
    var optional = findEdge(source, target);
    super.removeEdge(source, target);
    optional.ifPresent(edge -> send(
        EdgeRemovedEvent.<ID>builder()
            .graphId(getId().orElse(null))
            .edgeId(edge.getId().orElse(null))
            .sourceId(edge.getSource())
            .targetId(edge.getTarget())
            .build()));
  }

  @Override
  protected Vertex<ID> addVertex(ID id, Map<String, Object> local) {
    var vertex = super.addVertex(id, local);
    send(VertexCreatedEvent.<ID>builder()
        .graphId(getId().orElse(null))
        .vertexId(id)
        .properties(local)
        .build());
    return vertex;
  }

  @Override
  public void removeVertex(ID id) {
    var optional = findVertex(id);
    super.removeVertex(id);
    optional.ifPresent(vertex -> send(VertexRemovedEvent.<ID>builder()
        .graphId(getId().orElse(null))
        .vertexId(id)
        .build()));
  }

  public class EventEdge extends SimpleEdge {
    protected EventEdge(ID from, ID to, Map<String, Object> local, Map<String, Object> inherited) {
      super(from, to, local, inherited);
    }

    @Override
    public void setId(ID id) {
      var oldId = getId().orElse(null);
      if (id == null && oldId == null) {
        return;
      }
      super.setId(id);
      send(EdgePropertyEvent.<ID>builder()
          .graphId(ObservablePropertyGraph.this.getId().orElse(null))
          .sourceId(getSource())
          .targetId(getTarget())
          .edgeId(oldId)
          .newEdgeId(id)
          .build());
    }

    @Override
    public void setSource(ID source) {
      var oldFrom = this.getSource();
      super.setSource(source);
      send(EdgePropertyEvent.<ID>builder()
          .graphId(ObservablePropertyGraph.this.getId().orElse(null))
          .sourceId(oldFrom)
          .newSourceId(source)
          .targetId(getTarget())
          .edgeId(getId().orElse(null))
          .build());
    }

    @Override
    public void setTarget(ID target) {
      var oldTo = this.getTarget();
      super.setTarget(target);
      send(EdgePropertyEvent.<ID>builder()
          .graphId(ObservablePropertyGraph.this.getId().orElse(null))
          .sourceId(getSource())
          .targetId(oldTo)
          .newTargetId(target)
          .edgeId(getId().orElse(null))
          .build());
    }

    @Override
    public Edge<ID> property(Map<String, Object> properties) {
      super.property(properties);
      send(EdgePropertyEvent.<ID>builder()
          .graphId(ObservablePropertyGraph.this.getId().orElse(null))
          .sourceId(getSource())
          .targetId(getTarget())
          .edgeId(getId().orElse(null))
          .properties(properties)
          .build());
      return this;
    }
  }

  public class EventVertex extends SimpleVertex {
    protected EventVertex(ID id, Map<String, Object> local, Map<String, Object> inherited) {
      super(id, local, inherited);
    }

    @Override
    public void setId(ID id) {
      ID oldId = getId();
      super.setId(id);
      send(VertexPropertyEvent.<ID>builder()
          .graphId(ObservablePropertyGraph.this.getId().orElse(null))
          .vertexId(oldId)
          .newId(id)
          .build());
    }

    @Override
    public Vertex<ID> property(Map<String, Object> properties) {
      super.property(properties);
      send(VertexPropertyEvent.<ID>builder()
          .graphId(ObservablePropertyGraph.this.getId().orElse(null))
          .vertexId(getId())
          .properties(properties)
          .build()
      );
      return this;
    }
  }
}
