package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static java.util.Objects.*;

public abstract class AbstractEventPropertyGraph<ID> extends AbstractPropertyGraph<ID> implements EventPropertyGraph<ID> {

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
      bus.post(EdgePropertyEvent.<ID>builder()
        .graphId(AbstractEventPropertyGraph.this.getId().orElse(null))
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
      bus.post(EdgePropertyEvent.<ID>builder()
        .graphId(AbstractEventPropertyGraph.this.getId().orElse(null))
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
      bus.post(EdgePropertyEvent.<ID>builder()
        .graphId(AbstractEventPropertyGraph.this.getId().orElse(null))
        .sourceId(getSource())
        .targetId(oldTo)
        .newTargetId(target)
        .edgeId(getId().orElse(null))
        .build());
    }

    @Override
    public Edge<ID> property(Map<String, Object> properties) {
      super.property(properties);
      bus.post(EdgePropertyEvent.<ID>builder()
        .graphId(AbstractEventPropertyGraph.this.getId().orElse(null))
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
      bus.post(VertexPropertyEvent.<ID>builder()
        .graphId(AbstractEventPropertyGraph.this.getId().orElse(null))
        .vertexId(oldId)
        .newId(id)
        .build());
    }

    @Override
    public Vertex<ID> property(Map<String, Object> properties) {
      super.property(properties);
      bus.post(VertexPropertyEvent.<ID>builder()
        .graphId(AbstractEventPropertyGraph.this.getId().orElse(null))
        .vertexId(getId())
        .properties(properties)
        .build()
      );
      return this;
    }
  }

  private EventBus bus;

  public AbstractEventPropertyGraph(EventBus bus) {
    this.bus = requireNonNull(bus, "bus must not be null.");
  }

  public AbstractEventPropertyGraph(ID id, EventBus bus) {
    super(id);
    this.bus = requireNonNull(bus, "bus must not be null.");
  }

  public EventBus getBus() {
    return bus;
  }

  @Override
  public void setId(ID id) {
    Optional<ID> oldId = getId();
    super.setId(id);
    bus.post(GraphPropertyEvent.<ID>builder()
      .graphId(oldId.orElse(null))
      .newId(id)
      .build());
  }

  @Override
  public PropertyGraph<ID> property(Map<String, Object> properties) {
    var id = getId().orElse(null);
    super.property(properties);
    bus.post(GraphPropertyEvent.<ID>builder()
      .graphId(id)
      .properties(new LinkedHashMap<>(properties))
      .build());
    return this;
  }

  @Override
  public PropertyGraph<ID> edgeProperty(Map<String, Object> properties) {
    super.edgeProperty(properties);
    bus.post(EdgeInheritedPropertyEvent.<ID>builder()
      .graphId(getId().orElse(null))
      .properties(new LinkedHashMap<>(properties))
      .build());
    return this;
  }

  @Override
  public PropertyGraph<ID> vertexProperty(Map<String, Object> properties) {
    Map<String, Object> oldValues = new LinkedHashMap<>(getVertexProperties());
    super.vertexProperty(properties);
    bus.post(VertexInheritedPropertyEvent.<ID>builder()
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
    bus.post(EdgeCreatedEvent.<ID>builder()
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
    optional.ifPresent(edge -> bus.post(
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
    bus.post(VertexCreatedEvent.<ID>builder()
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
    optional.ifPresent(vertex -> bus.post(VertexRemovedEvent.<ID>builder()
      .graphId(getId().orElse(null))
      .vertexId(id)
      .build()));
  }
}
