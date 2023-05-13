package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.moaxcp.graphs.DirectedPropertyGraph;
import java.io.IOException;

public class DirectedPropertyGraphDeserializer extends StdDeserializer<DirectedPropertyGraph<?>> {
  protected DirectedPropertyGraphDeserializer() {
    super(DirectedPropertyGraph.class);
  }

  @Override
  public DirectedPropertyGraph<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
    return new DirectedPropertyGraph<>();
  }
}
