package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static java.util.stream.Collectors.toCollection;

public class PropertyGraphSerializer extends StdSerializer<PropertyGraph> {

  static final PropertyGraphSerializer INSTANCE = new PropertyGraphSerializer();
  protected PropertyGraphSerializer() {
    super(PropertyGraph.class);
  }

  @Override
  public void serialize(PropertyGraph value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    serializeCheckEmptyMap("properties", value.getProperties(), gen, provider);
    serializeCheckEmptyMap("vertexProperties", value.getVertexProperties(), gen, provider);
    serializeCheckEmptyMap("edgeProperties", value.getEdgeProperties(), gen, provider);
    serializeVertices(value.getVertices(), gen, provider);
    serializeEdges(value.getEdges(), gen, provider);
    gen.writeEndObject();
  }

  private void serializeEdges(Collection<Edge> edges, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if(!shouldSerializeList(edges, provider)) {
      return;
    }
    gen.writeArrayFieldStart("edges");
    for(Edge edge : edges) {
      gen.writeObject(edge.local());
    }
    gen.writeEndArray();
  }

  private void serializeVertices(Map<Object, Vertex> vertices, JsonGenerator gen, SerializerProvider provider) throws IOException {
    Collection<Vertex> collect = vertices.values().stream()
        .filter(v -> !(v.adjacentEdges().size() > 0 && v.local().size() == 1))
        .collect(toCollection(ArrayList::new));
    if (!shouldSerializeList(collect, provider)) {
      return;
    }
    gen.writeArrayFieldStart("vertices");
    for(Vertex vertex : collect) {
      gen.writeObject(vertex.local());
    }
    gen.writeEndArray();
  }

  private boolean shouldSerializeList(Collection list, SerializerProvider provider) {
    return !(list.isEmpty() && provider.getConfig().getDefaultPropertyInclusion().getContentInclusion().equals(Include.NON_EMPTY));
  }

  private void serializeCheckEmptyMap(String name, Map<String, Object> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if(!(value.isEmpty() && provider.getConfig().getDefaultPropertyInclusion().getContentInclusion().equals(Include.NON_EMPTY))) {
      provider.defaultSerializeField(name, value, gen);
    }
  }
}
