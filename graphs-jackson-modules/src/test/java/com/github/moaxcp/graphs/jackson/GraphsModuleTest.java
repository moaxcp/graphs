package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.moaxcp.graphs.DirectedPropertyGraph;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class GraphsModuleTest {
  private ObjectMapper mapper = new ObjectMapper().registerModule(new GraphsModule());

  @Test
  void emptyGraph() throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<String>();
    String result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals(result, "{}", JSONCompareMode.STRICT);
  }
}
