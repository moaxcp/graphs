package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.newevents.*;
import org.greenrobot.eventbus.*;

import java.util.*;
import java.util.Map.*;

import static java.util.Objects.*;

public abstract class AbstractEventGraph<ID> extends AbstractGraph<ID> implements EventGraph<ID> {

  public class EventEdge extends SimpleEdge {
    protected EventEdge(ID from, ID to, Map<String, Object> local, Map<String, Object> inherited) {
      super(from, to, local, inherited);
    }

    @Override
    public void setId(ID id) {
      var oldId = getId();
      if (id == null && !oldId.isPresent()) {
        return;
      }
      super.setId(id);
      if (id == null) {
        bus.post(new EdgeIdRemoved.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .edgeId(oldId.orElse(null))
          .from(getFrom())
          .to(getTo())
          .build());
        return;
      }
      if (oldId.isPresent()) {
        bus.post(new EdgeIdUpdated.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .edgeId(id)
          .oldEdgeId(oldId.orElse(null))
          .from(getFrom())
          .to(getTo())
          .build());
      } else {
        bus.post(new EdgeIdAdded.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .edgeId(id)
          .from(getFrom())
          .to(getTo())
          .build());
      }
    }

    @Override
    public void setFrom(ID from) {
      var oldFrom = this.getFrom();
      super.setFrom(from);
      bus.post(new EdgeFromUpdated.Builder<ID>()
        .graphId(AbstractEventGraph.this.getId().orElse(null))
        .edgeId(getId().orElse(null))
        .from(getFrom())
        .oldFrom(oldFrom)
        .to(getTo())
        .build());
    }

    @Override
    public void setTo(ID to) {
      var oldTo = this.getTo();
      super.setTo(to);
      bus.post(new EdgeToUpdated.Builder<ID>()
        .graphId(AbstractEventGraph.this.getId().orElse(null))
        .edgeId(getId().orElse(null))
        .from(getFrom())
        .oldTo(oldTo)
        .to(getTo())
        .build());
    }

    @Override
    public Edge<ID> property(Map<String, Object> properties) {
      Map<String, Object> oldValues = new LinkedHashMap<>(local());
      super.property(properties);
      for (Entry<String, Object> entry : properties.entrySet()) {
        sendEdgePropertyEvents(entry.getKey(), entry.getValue(), Optional.ofNullable(oldValues.get(entry.getKey())));
      }
      return this;
    }

    private void sendEdgePropertyEvents(String name, Object value, Optional<Object> oldValue) {
      if(value == null) {
        bus.post(new EdgePropertyRemoved.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .edgeId(getId().orElse(null))
          .from(getFrom())
          .to(getTo())
          .name(name)
          .value(oldValue.orElse(null))
          .build());
      } else if (oldValue.isPresent()) {
        bus.post(new EdgePropertyUpdated.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .edgeId(getId().orElse(null))
          .from(getFrom())
          .to(getTo())
          .name(name)
          .value(value)
          .oldValue(oldValue.orElse(null))
          .build());
      } else {
        bus.post(new EdgePropertyAdded.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .edgeId(getId().orElse(null))
          .from(getFrom())
          .to(getTo())
          .name(name)
          .value(value)
          .build());
      }
    }

    @Override
    public Edge<ID> removeProperty(String name) {
      var value = getProperty(name);
      var edge = super.removeProperty(name);
      bus.post(new EdgePropertyRemoved.Builder<ID>()
        .graphId(AbstractEventGraph.this.getId().orElse(null))
        .edgeId(getId().orElse(null))
        .from(getFrom())
        .to(getTo())
        .name(name)
        .value(value.orElse(null))
        .build());
      return edge;
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
      bus.post(new VertexIdUpdated.Builder<ID>()
        .graphId(AbstractEventGraph.this.getId().orElse(null))
        .oldVertexId(oldId)
        .vertexId(id)
        .build());
    }

    @Override
    public Vertex<ID> property(Map<String, Object> properties) {
      Map<String, Object> oldValues = new LinkedHashMap<>(local());
      super.property(properties);
      for (Entry<String, Object> entry : properties.entrySet()) {
        sendVertexPropertyEvents(entry.getKey(), entry.getValue(), Optional.ofNullable(oldValues.get(entry.getKey())));
      }
      return this;
    }

    private void sendVertexPropertyEvents(String name, Object value, Optional<Object> oldValue) {
      if(value == null) {
        bus.post(new VertexPropertyRemoved.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .vertexId(getId())
          .name(name)
          .value(oldValue.orElse(null))
          .build()
        );
      } else if (oldValue.isPresent()) {
        bus.post(new VertexPropertyUpdated.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .vertexId(getId())
          .name(name)
          .value(value)
          .oldValue(oldValue.orElse(null))
          .build());
      } else {
        bus.post(new VertexPropertyAdded.Builder<ID>()
          .graphId(AbstractEventGraph.this.getId().orElse(null))
          .vertexId(getId())
          .name(name)
          .value(value)
          .build());
      }
    }

    @Override
    public Vertex<ID> removeProperty(String name) {
      var value = getProperty(name);
      var vertex = super.removeProperty(name);
      bus.post(new VertexPropertyRemoved.Builder<ID>()
        .graphId(AbstractEventGraph.this.getId().orElse(null))
        .vertexId(getId())
        .name(name)
        .value(value.orElse(null))
        .build());
      return vertex;
    }
  }

  private EventBus bus;

  public AbstractEventGraph(EventBus bus) {
    this.bus = requireNonNull(bus, "bus must not be null.");
  }

  public AbstractEventGraph(ID id, EventBus bus) {
    super(id);
    this.bus = requireNonNull(bus, "bus must not be null.");
  }

  public EventBus getBus() {
    return bus;
  }

  @Override
  public Graph<ID> property(Map<String, Object> properties) {
    var id = getId().orElse(null);
    super.property(properties);
    bus.post(GraphPropertyEvent.<ID>builder()
      .graphId(id)
      .properties(new LinkedHashMap<>(properties))
      .build());
    return this;
  }

  @Override
  public Graph<ID> edgeProperty(Map<String, Object> properties) {
    super.edgeProperty(properties);
    bus.post(EdgeInheritedPropertyEvent.<ID>builder()
      .graphId(getId().orElse(null))
      .properties(new LinkedHashMap<>(properties))
      .build());
    return this;
  }

  @Override
  public Graph<ID> vertexProperty(Map<String, Object> properties) {
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
    bus.post(new EdgeCreated.Builder<ID>()
      .graphId(getId().orElse(null))
      .from(from)
      .to(to)
      .properties(local)
      .build());
    return edge;
  }

  @Override
  public void removeEdge(ID from, ID to) {
    var optional = findEdge(from, to);
    super.removeEdge(from, to);
    optional.ifPresent(edge -> bus.post(
      new EdgeRemoved.Builder<ID>()
        .graphId(getId().orElse(null))
        .edgeId(edge.getId().orElse(null))
        .from(edge.getFrom())
        .to(edge.getTo())
        .properties(edge.local())
        .build()));
  }

  @Override
  protected Vertex<ID> addVertex(ID id, Map<String, Object> local) {
    var vertex = super.addVertex(id, local);
    bus.post(new VertexCreated.Builder<ID>()
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
    optional.ifPresent(vertex -> bus.post(new VertexRemoved.Builder<ID>()
      .graphId(getId().orElse(null))
      .vertexId(id)
      .properties(vertex.local())
      .build()));
  }
}
