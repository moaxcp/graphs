package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.moaxcp.graphs.DirectedPropertyGraph;
import java.util.List;
import java.util.stream.Stream;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class GraphsModuleTest {
  private static Stream<ObjectMapper> inclusionMappers() {
    return List.of(new ObjectMapper().registerModule(new GraphsModule()).setDefaultPropertyInclusion(Include.NON_EMPTY),
        new ObjectMapper().registerModule(new GraphsModule()).setDefaultPropertyInclusion(Include.NON_NULL)).stream();
  }

  private static ObjectMapper mapper = new ObjectMapper().registerModule(new GraphsModule());

  @ParameterizedTest
  @MethodSource("inclusionMappers")
  void emptyGraph(ObjectMapper mapper) throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>();
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("{}", result, JSONCompareMode.STRICT);
  }

  @Test
  void emptyGraph() throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>();
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
        {
          "properties": null,
          "vertexProperties": null,
          "edgeProperties": null,
          "vertices": null,
          "edges": null
        }
        """, result, JSONCompareMode.STRICT);
  }

  @ParameterizedTest
  @MethodSource("inclusionMappers")
  void graphWithId(ObjectMapper mapper) throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>("graph");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "properties" : {
                "id": "graph"
              }
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @Test
  void graphWithId() throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>("graph");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "properties" : {
                "id": "graph"
              },
              "vertexProperties": null,
              "edgeProperties": null,
              "vertices": null,
              "edges": null
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @ParameterizedTest
  @MethodSource("inclusionMappers")
  void graphWithIdAndProperties(ObjectMapper mapper) throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>("graph").property("k1", "v1").property("k2", "v2");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "properties" : {
                "id": "graph",
                "k1": "v1",
                "k2": "v2"
              }
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @Test
  void graphWithIdAndProperties() throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>("graph").property("k1", "v1").property("k2", "v2");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "properties" : {
                "id": "graph",
                "k1": "v1",
                "k2": "v2"
              },
              "vertexProperties": null,
              "edgeProperties": null,
              "vertices": null,
              "edges": null
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @ParameterizedTest
  @MethodSource("inclusionMappers")
  void graphWithVertexProperties(ObjectMapper mapper) throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>().vertexProperty("k1", "v1").vertexProperty("k2", "v2");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "vertexProperties" : {
                "k1": "v1",
                "k2": "v2"
              }
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @Test
  void graphWithVertexProperties() throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>().vertexProperty("k1", "v1").vertexProperty("k2", "v2");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "properties": null,
              "vertexProperties" : {
                "k1": "v1",
                "k2": "v2"
              },
              "edgeProperties": null,
              "vertices": null,
              "edges": null
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @ParameterizedTest
  @MethodSource("inclusionMappers")
  void graphWithEdgeProperties(ObjectMapper mapper) throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>().edgeProperty("k1", "v1").edgeProperty("k2", "v2");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "edgeProperties" : {
                "k1": "v1",
                "k2": "v2"
              }
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @Test
  void graphWithEdgeProperties() throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>().edgeProperty("k1", "v1").edgeProperty("k2", "v2");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "properties": null,
              "vertexProperties": null,
              "edgeProperties" : {
                "k1": "v1",
                "k2": "v2"
              },
              "vertices": null,
              "edges": null
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @ParameterizedTest
  @MethodSource("inclusionMappers")
  void graphWithVertex(ObjectMapper mapper) throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>().vertex("A");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "vertices" : [
                {
                  "id": "A"
                }
              ]
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @Test
  void graphWithVertex() throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>().vertex("A");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
            {
              "properties": null,
              "vertexProperties": null,
              "edgeProperties": null,
              "vertices" : [
                {
                  "id": "A"
                }
              ],
              "edges": null
            }
            """,
        result, JSONCompareMode.STRICT);
  }

  @ParameterizedTest
  @MethodSource("inclusionMappers")
  void graphWithEdge(ObjectMapper mapper) throws JSONException, JsonProcessingException {
    var graph = new DirectedPropertyGraph<>().edge("A", "B");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
        {
          "edges" : [
            {
              "source": "A",
              "target": "B"
            }
          ]
        }
        """, result, JSONCompareMode.STRICT);
  }

  @Test
  void graphWithEdge() throws JSONException, JsonProcessingException {
    var graph = new DirectedPropertyGraph<>().edge("A", "B");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
        {
          "properties": null,
          "vertexProperties": null,
          "edgeProperties": null,
          "edges" : [
            {
              "source": "A",
              "target": "B"
            }
          ],
          "vertices": null
        }
        """, result, JSONCompareMode.STRICT);
  }

  @ParameterizedTest
  @MethodSource("inclusionMappers")
  void graphWithEverything(ObjectMapper mapper) throws JsonProcessingException, JSONException {
    var graph = new DirectedPropertyGraph<>()
        .edge("A", "B", "prop1", "value1", "prop2", "value2")
        .vertex("C")
        .vertex("A", "k1", "v1")
        .edgeProperty("edgeProp", "edgeValue")
        .vertexProperty("vertexProp", "vertexValue")
        .property("graphProperty", "graphValue")
        .id("graphId");
    graph.getEdge("A", "B").id("edgeId");
    var result = mapper.writeValueAsString(graph);
    JSONAssert.assertEquals("""
        {
          "properties":{
            "graphProperty":"graphValue",
            "id":"graphId"
          },
          "vertexProperties": {
            "vertexProp":"vertexValue"
          },
          "edgeProperties": {
            "edgeProp":"edgeValue"
          },
          "vertices": [
            {"id":"A","k1":"v1"},
            {"id":"C"}
          ],
          "edges": [
            {"prop1":"value1","prop2":"value2","source":"A","target":"B","id":"edgeId"}
          ]
        }
        """, result, JSONCompareMode.STRICT);
  }
}
