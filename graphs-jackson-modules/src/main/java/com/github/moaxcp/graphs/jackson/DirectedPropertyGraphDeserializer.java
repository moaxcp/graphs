package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.moaxcp.graphs.DirectedPropertyGraph;
import java.io.IOException;

public class DirectedPropertyGraphDeserializer extends StdDeserializer<DirectedPropertyGraph<Object>> {
  protected DirectedPropertyGraphDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public DirectedPropertyGraph<Object> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
    return null;
  }
}
