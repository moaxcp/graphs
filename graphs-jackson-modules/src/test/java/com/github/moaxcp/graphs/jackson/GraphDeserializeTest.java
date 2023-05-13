package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;

import static com.google.common.truth.Truth.assertThat;

public class GraphDeserializeTest {

  @InclusionMapperTest
  void emptyGraphDirected(ObjectMapper mapper) throws JsonProcessingException {
    var result = mapper.readValue("{}", DirectedPropertyGraph.class);
    assertThat(result).isEqualTo(new DirectedPropertyGraph<String>());
  }

  @InclusionMapperTest
  void emptyGraphUndirected(ObjectMapper mapper) throws JsonProcessingException {
    var result = mapper.readValue("{}", UndirectedPropertyGraph.class);
    assertThat(result).isEqualTo(new UndirectedPropertyGraph<String>());
  }

  @InclusionMapperTest
  void graphWithIdDirected(ObjectMapper mapper) throws JsonProcessingException {
    var result = mapper.readValue("""
            {
              "properties" : {
                "id": "graph"
              }
            }
            """, DirectedPropertyGraph.class);
    assertThat(result).isEqualTo(new DirectedPropertyGraph<String>().id("graph"));
  }

  @InclusionMapperTest
  void graphWithIdUndirected(ObjectMapper mapper) throws JsonProcessingException {
    var result = mapper.readValue("""
            {
              "properties" : {
                "id": "graph"
              }
            }
            """, UndirectedPropertyGraph.class);
    assertThat(result).isEqualTo(new UndirectedPropertyGraph<String>().id("graph"));
  }
}
