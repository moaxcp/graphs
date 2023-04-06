package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    serializeCheckEmptyList("edges", edges.stream().map(e -> (Map<String, Object>) e.local()).toList(), gen, provider);
  }

  private void serializeVertices(Map<Object, Vertex> vertices, JsonGenerator gen, SerializerProvider provider) throws IOException {
    List<Map<String, Object>> collect = vertices.values().stream()
        .filter(v -> !(v.adjacentEdges().size() > 0 && v.local().size() == 1))
        .map(v -> (Map<String, Object>) v.local())
        .toList();
    serializeCheckEmptyList("vertices", collect, gen, provider);
  }

  private void serializeCheckEmptyList(String name, List<Map<String, Object>> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (value.isEmpty() && !(provider.getConfig().getDefaultPropertyInclusion().getContentInclusion().equals(Include.NON_EMPTY)
        || provider.getConfig().getDefaultPropertyInclusion().getContentInclusion().equals(Include.NON_NULL))) {
      provider.defaultSerializeField(name, null, gen);
    } else if (!value.isEmpty()) {
      provider.defaultSerializeField(name, value, gen);
    }
  }

  private void serializeCheckEmptyMap(String name, Map<String, Object> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
    if (value.isEmpty() && !(provider.getConfig().getDefaultPropertyInclusion().getContentInclusion().equals(Include.NON_EMPTY)
          || provider.getConfig().getDefaultPropertyInclusion().getContentInclusion().equals(Include.NON_NULL))) {
        provider.defaultSerializeField(name, null, gen);
    } else if (!value.isEmpty()) {
      provider.defaultSerializeField(name, value, gen);
    }
  }
}
