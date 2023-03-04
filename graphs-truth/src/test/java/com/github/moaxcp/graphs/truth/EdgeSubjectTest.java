package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import com.google.common.truth.ExpectFailure;
import com.google.common.truth.ExpectFailure.SimpleSubjectBuilderCallback;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.truth.EdgeSubject.edges;
import static com.google.common.truth.ExpectFailure.assertThat;
import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class EdgeSubjectTest {
  private static AssertionError expectError(SimpleSubjectBuilderCallback<EdgeSubject, Edge> assertionCallback) {
    return ExpectFailure.expectFailureAbout(edges(), assertionCallback);
  }

  @Test
  void hasIdThatFail() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B").id("id");
    var expected = expectError(whenTesting -> whenTesting.that(edge).hasIdThat().isEqualTo("edge"));

    assertThat(expected).factValue("value of").isEqualTo("edge.getId()");
    assertThat(expected).factKeys().contains("edge was");
  }

  @Test
  void hasIdThat() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B").id("id");
    assertThat(edge).hasIdThat().hasValue("id");
  }

  @Test
  void hadNoIdFail() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B").id("id");
    var expected = expectError(whenTesting -> whenTesting.that(edge).hasNoId());

    assertThat(expected).factValue("value of").isEqualTo("edge.getId()");
    assertThat(expected).factKeys().contains("edge was");
  }

  @Test
  void hadNoId() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    assertThat(edge).hasNoId();
  }

  @Test
  void hasFromThatFail() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    var expected = expectError(whenTesting -> whenTesting.that(edge).hasFromThat().isEqualTo("B"));

    assertThat(expected).factValue("value of").isEqualTo("edge.getFrom()");
    assertThat(expected).factKeys().contains("edge was");
  }

  @Test
  void hasFromThat() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    assertThat(edge).hasFromThat().isEqualTo("A");
  }

  @Test
  void hasToThatFail() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    var expected = expectError(whenTesting -> whenTesting.that(edge).hasToThat().isEqualTo("A"));

    assertThat(expected).factValue("value of").isEqualTo("edge.getTo()");
    assertThat(expected).factKeys().contains("edge was");
  }

  @Test
  void hasToThat() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    assertThat(edge).hasToThat().isEqualTo("B");
  }

  @Test
  void withPropertyFail() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    var expected = expectError(whenTesting -> whenTesting.that(edge).withProperty("name").hasValue("value"));

    assertThat(expected).factValue("value of").isEqualTo("edge.getProperty(name)");
    assertThat(expected).factKeys().contains("edge was");
  }

  @Test
  void withProperty() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B").property("name", "value");
    assertThat(edge).withProperty("name").hasValue("value");
  }

  @Test
  void withLocalFail() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    var expected = expectError(whenTesting -> whenTesting.that(edge).withLocal().containsEntry("name", "value"));

    assertThat(expected).factValue("value of").isEqualTo("edge.local()");
    assertThat(expected).factKeys().contains("edge was");
  }

  @Test
  void withLocal() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B").property("name", "value");
    assertThat(edge).withLocal().containsEntry("name", "value");
  }

  @Test
  void isDirectedFail() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    var expected = expectError(whenTesting -> whenTesting.that(edge).isDirected());

    assertThat(expected).factKeys().contains("Expected directed edge");
  }

  @Test
  void isDirected() {
    var edge = new DirectedPropertyGraph<String>().getEdge("A", "B");
    assertThat(edge).isDirected();
  }

  @Test
  void isUndirectedFail() {
    var edge = new DirectedPropertyGraph<String>().getEdge("A", "B");
    var expected = expectError(whenTesting -> whenTesting.that(edge).isUndirected());

    assertThat(expected).factKeys().contains("Expected undirected edge");
  }

  @Test
  void isUndirected() {
    var edge = new UndirectedPropertyGraph<String>().getEdge("A", "B");
    assertThat(edge).isUndirected();
  }
}
