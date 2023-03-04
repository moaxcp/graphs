package com.github.moaxcp.graphs.truth;

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
}
