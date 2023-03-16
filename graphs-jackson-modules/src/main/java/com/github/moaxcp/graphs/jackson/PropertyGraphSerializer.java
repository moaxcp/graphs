package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.moaxcp.graphs.PropertyGraph;
import java.io.IOException;

public class PropertyGraphSerializer extends StdSerializer<PropertyGraph> {
  protected PropertyGraphSerializer(Class<PropertyGraph> t) {
    super(t);
  }

  @Override
  public void serialize(PropertyGraph value, JsonGenerator gen, SerializerProvider provider) throws IOException {

  }
}
