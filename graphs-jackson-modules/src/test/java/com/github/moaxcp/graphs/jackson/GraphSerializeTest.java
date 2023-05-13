package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.github.moaxcp.graphs.jackson.MethodSources.mapper;

public class GraphSerializeTest {

  @InclusionMapperWithGraphTest
  void emptyGraphSerialize(ObjectMapper mapper, PropertyGraph<String> graph) throws JsonProcessingException, JSONException {
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

  @InclusionMapperWithGraphTest
  void graphWithId(ObjectMapper mapper, PropertyGraph<String> graph) throws JsonProcessingException, JSONException {
    graph.id("graph");
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
    var graph = new DirectedPropertyGraph<>().id("graph");
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

  @InclusionMapperWithGraphTest
  void graphWithIdAndProperties(ObjectMapper mapper, PropertyGraph<String> graph) throws JsonProcessingException, JSONException {
    graph.id("graph").property("k1", "v1").property("k2", "v2");
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
    var graph = new DirectedPropertyGraph<>().id("graph").property("k1", "v1").property("k2", "v2");
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

  @InclusionMapperWithGraphTest
  void graphWithVertexProperties(ObjectMapper mapper, PropertyGraph<String> graph) throws JsonProcessingException, JSONException {
    graph.vertexProperty("k1", "v1").vertexProperty("k2", "v2");
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

  @InclusionMapperWithGraphTest
  void graphWithEdgeProperties(ObjectMapper mapper, PropertyGraph<String> graph) throws JsonProcessingException, JSONException {
    graph.edgeProperty("k1", "v1").edgeProperty("k2", "v2");
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

  @InclusionMapperWithGraphTest
  void graphWithVertex(ObjectMapper mapper, PropertyGraph<String> graph) throws JsonProcessingException, JSONException {
    graph.vertex("A");
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

  @InclusionMapperWithGraphTest
  void graphWithEdge(ObjectMapper mapper, PropertyGraph<String> graph) throws JSONException, JsonProcessingException {
    graph.edge("A", "B");
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

  @InclusionMapperWithGraphTest
  void graphWithEverything(ObjectMapper mapper, PropertyGraph<String> graph) throws JsonProcessingException, JSONException {
    graph
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
