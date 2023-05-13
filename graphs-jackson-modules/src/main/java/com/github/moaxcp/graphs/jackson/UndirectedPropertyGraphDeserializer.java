package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import java.io.IOException;

public class UndirectedPropertyGraphDeserializer extends StdDeserializer<UndirectedPropertyGraph<?>> {
  protected UndirectedPropertyGraphDeserializer() {
    super(UndirectedPropertyGraph.class);
  }

  @Override
  public UndirectedPropertyGraph<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
    p.
    return new UndirectedPropertyGraph<>();
  }
}
