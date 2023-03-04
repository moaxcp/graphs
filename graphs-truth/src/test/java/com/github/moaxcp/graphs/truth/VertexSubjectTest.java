package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import com.google.common.truth.ExpectFailure;
import com.google.common.truth.ExpectFailure.SimpleSubjectBuilderCallback;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.github.moaxcp.graphs.truth.VertexSubject.vertices;
import static com.google.common.truth.ExpectFailure.assertThat;

public class VertexSubjectTest {
  private static AssertionError expectError(SimpleSubjectBuilderCallback<VertexSubject, Vertex> assertionCallback) {
    return ExpectFailure.expectFailureAbout(vertices(), assertionCallback);
  }

  @Test
  void hasIdFail() {
    var vertex = new UndirectedPropertyGraph<String>().getVertex("A");
    var expected = expectError(whenTesting-> whenTesting.that(vertex).hasId("B"));

    assertThat(expected).factValue("value of").isEqualTo("vertex.getId()");
    assertThat(expected).factKeys().contains("vertex was");
  }

  @Test
  void hasId() {
    var vertex = new UndirectedPropertyGraph<String>().getVertex("A");
    assertThat(vertex).hasId("A");
  }

  @Test
  void withPropertyFail() {
    var vertex = new UndirectedPropertyGraph<String>().getVertex("A");
    AssertionError expected = expectError(whenTesting -> whenTesting.that(vertex).withProperty("name").isPresent());

    assertThat(expected).factValue("value of").isEqualTo("vertex.getProperty(name)");
    assertThat(expected).factKeys().contains("expected to be present");
  }

  @Test
  void withProperty() {
    var vertex = new UndirectedPropertyGraph<String>().getVertex("A").property("name", "value");
    assertThat(vertex).withProperty("name").hasValue("value");
  }

  @Test
  void withLocalFail() {
    var vertex = new UndirectedPropertyGraph<String>().getVertex("A");
    var expected = expectError(whenTesting -> whenTesting.that(vertex).withLocal().containsEntry("name", "value"));

    assertThat(expected).factValue("value of").isEqualTo("vertex.local()");
    assertThat(expected).factKeys().contains("vertex was");
  }

  @Test
  void withLocal() {
    var vertex = new UndirectedPropertyGraph<String>().getVertex("A").property("name", "value");
    assertThat(vertex).withLocal().containsEntry("name", "value");
  }

  @Test
  void withInheritedFail() {
    var vertex = new UndirectedPropertyGraph<String>().getVertex("A");
    var expected = expectError(whenTesting -> whenTesting.that(vertex).withInherited().containsEntry("name", "value"));

    assertThat(expected).factValue("value of").isEqualTo("vertex.inherited()");
    assertThat(expected).factKeys().contains("vertex was");
  }

  @Test
  void withInherited() {
    var vertex = new UndirectedPropertyGraph<String>().vertexProperty("name", "value").getVertex("A");
    assertThat(vertex).withInherited().containsEntry("name", "value");
  }
}
