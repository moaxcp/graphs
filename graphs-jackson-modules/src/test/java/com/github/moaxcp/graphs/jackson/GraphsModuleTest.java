package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GraphsModuleTest {
  private ObjectMapper mapper = new ObjectMapper().registerModule(new GraphsModule());
}
