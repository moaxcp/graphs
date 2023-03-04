package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import com.google.common.truth.ExpectFailure;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.truth.PropertyGraphSubject.graphs;
import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.ExpectFailure.SimpleSubjectBuilderCallback;
import static com.google.common.truth.ExpectFailure.assertThat;


public class PropertyGraphSubjectTest {
  private static AssertionError expectError(SimpleSubjectBuilderCallback<PropertyGraphSubject, PropertyGraph> assertionCallback) {
    return ExpectFailure.expectFailureAbout(graphs(), assertionCallback);
  }

  @Test
  void hasId() {
    var graph = new UndirectedPropertyGraph<String>("id");
    assertThat(graph).hasIdThat().hasValue("id");
  }

  @Test
  void hasIdFailed() {
    var graph = new UndirectedPropertyGraph<String>();
    AssertionError expected = expectError(whenTesting -> whenTesting.that(new UndirectedPropertyGraph<String>()).hasIdThat().hasValue("id"));
    assertThat(expected).factValue("value of").isEqualTo("propertyGraph.getId()");
    assertThat(expected).factKeys().contains("but was empty");
  }

  @Test
  void hasEdgeFailed() {
    var graph = new UndirectedPropertyGraph<String>();
    graph.vertex("A");
    graph.vertex("B");

    AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasEdge("A", "B"));

    assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findEdge(A, B)");
    assertThat(expected).factKeys().contains("expected to be present");
  }

  @Test
  void hasEdge() {
    var graph = new UndirectedPropertyGraph<String>();
    graph.edge("A", "B");
    assertThat(graph).hasEdge("A", "B");
  }

  @Test
  void hasEdgeIdFailed() {
    var graph = new UndirectedPropertyGraph<String>();
    var expected = expectError(whenTesting -> whenTesting.that(graph).hasEdge("id"));

    assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findEdge(id)");
    assertThat(expected).factKeys().contains("expected to be present");
  }

  @Test
  void hasEdgeId() {
    var graph = new UndirectedPropertyGraph<String>();
    graph.getEdge("A", "B").id("id");
    assertThat(graph).hasEdge("id");
  }

  @Test
  void hasNoEdgeFailed() {
    var graph = new UndirectedPropertyGraph<String>();
    graph.edge("A", "B");

    AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasNoEdge("A", "B"));

    assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findEdge(A, B)");
    assertThat(expected).factKeys().contains("expected to be empty");
  }

  @Test
  void hasNoEdge() {
    var graph = new UndirectedPropertyGraph<String>();
    assertThat(graph).hasNoEdge("A", "B");
  }

  @Test
  void hasNoEdgeIdFailed() {
    var graph = new UndirectedPropertyGraph<String>();
    graph.getEdge("A", "B").id("id");

    AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasNoEdge("id"));

    assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findEdge(id)");
    assertThat(expected).factKeys().contains("expected to be empty");
  }

  @Test
  void hasNoEdgeId() {
    var graph = new UndirectedPropertyGraph<String>();
    assertThat(graph).hasNoEdge("id");
  }

  @Test
  void hasVertexFailed() {
    AssertionError expected = expectError(whenTesting -> whenTesting.that(new UndirectedPropertyGraph<String>()).hasVertex("id"));

    assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findVertex(id)");
    assertThat(expected).factKeys().contains("expected to be present");
  }

  @Test
  void hasVertex() {
    var graph = new UndirectedPropertyGraph<String>();
    graph.vertex("id");
    assertThat(graph).hasVertex("id");
  }

  @Test
  void hasNoVertexFailed() {
    AssertionError expected = expectError(whenTesting -> whenTesting.that(new UndirectedPropertyGraph<String>().vertex("A")).hasNoVertex("A"));

    assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findVertex(A)");
    assertThat(expected).factKeys().contains("expected to be empty");
  }

  @Test
  void hasNoVertex() {
    var graph = new UndirectedPropertyGraph<String>();
    assertThat(graph).hasNoVertex("id");
  }

  @Test
  void isDirectedFail() {
    var graph = new UndirectedPropertyGraph<String>();
    var expected = expectError(whenTesting -> whenTesting.that(graph).isDirected());

    assertThat(expected).factKeys().contains("Expected directed graph");
  }

  @Test
  void isDirected() {
    var graph = new DirectedPropertyGraph<String>();
    assertThat(graph).isDirected();
  }

  @Test
  void isUndirectedFail() {
    var graph = new DirectedPropertyGraph<String>();
    var expected = expectError(whenTesting -> whenTesting.that(graph).isUndirected());

    assertThat(expected).factKeys().contains("Expected undirected graph");
  }

  @Test
  void isUndirected() {
    var graph = new UndirectedPropertyGraph<String>();
    assertThat(graph).isUndirected();
  }

  @Test
  void isEmptyFail() {
    var graph = new DirectedPropertyGraph<String>().vertex("A");
    var expected = expectError(whenTesting -> whenTesting.that(graph).isEmpty());

    assertThat(expected).factKeys().contains("Expected empty graph");
  }

  @Test
  public void isEmpty() {
    var graph = new UndirectedPropertyGraph<String>();
    assertThat(graph).isEmpty();
  }

  @Test
  void withPropertyFail() {
    var graph = new UndirectedPropertyGraph<String>();
    AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).withProperty("name").isPresent());

    assertThat(expected).factValue("value of").isEqualTo("propertyGraph.getProperty(name)");
    assertThat(expected).factKeys().contains("expected to be present");
  }

  @Test
  void withProperty() {
    var graph = new UndirectedPropertyGraph<String>().property("name", "value");
    assertThat(graph).withProperty("name").hasValue("value");
  }

    @Test
    void withEdgePropertyFail() {
        var graph = new UndirectedPropertyGraph<String>();
        AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).withEdgeProperty("name").isPresent());

        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.getEdgeProperty(name)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    void withEdgeProperty() {
        var graph = new UndirectedPropertyGraph<String>().edgeProperty("name", "value");
        assertThat(graph).withEdgeProperty("name").hasValue("value");
    }

    @Test
    void withVertexPropertyFail() {
        var graph = new UndirectedPropertyGraph<String>();
        AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).withVertexProperty("name").isPresent());

        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.getVertexProperty(name)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    void withVertexProperty() {
        var graph = new UndirectedPropertyGraph<String>().vertexProperty("name", "value");
        assertThat(graph).withVertexProperty("name").hasValue("value");
    }
}
