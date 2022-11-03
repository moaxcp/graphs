package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import com.google.common.truth.ExpectFailure;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.truth.PropertyGraphSubject.graphs;
import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.ExpectFailure.SimpleSubjectBuilderCallback;
import static com.google.common.truth.ExpectFailure.assertThat;



public class PropertyGraphSubjectTest {
    @Test
    public void hasId() {
        var graph = new UndirectedPropertyGraph<String>("id");
        assertThat(graph).hasIdThat().hasValue("id");
    }
    @Test
    public void hasIdFailed() {
        var graph = new UndirectedPropertyGraph<String>();
        AssertionError expected = expectError(whenTesting -> whenTesting.that(new UndirectedPropertyGraph<String>()).hasIdThat().hasValue("id"));
        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.getId()");
        assertThat(expected).factKeys().contains("but was empty");
    }

    @Test
    public void hasEdgeFailed() {
        var graph = new UndirectedPropertyGraph<String>();
        graph.vertex("A");
        graph.vertex("B");

        AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasEdge("A", "B"));

        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findEdge(A, B)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void hasEdge() {
        var graph = new UndirectedPropertyGraph<String>();
        graph.edge("A", "B");
        assertThat(graph).hasEdge("A", "B");
    }

    @Test
    public void hasEdgeIdFailed() {
        var graph = new UndirectedPropertyGraph<String>();
        var expected = expectError(whenTesting -> whenTesting.that(graph).hasEdge("id"));

        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findEdge(id)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void hasEdgeId() {
        var graph = new UndirectedPropertyGraph<String>();
        graph.getEdge("A", "B").id("id");
        assertThat(graph).hasEdge("id");
    }

    @Test
    public void hasNoEdgeFailed() {
        var graph = new UndirectedPropertyGraph<String>();
        graph.edge("A", "B");

        AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasNoEdge("A", "B"));

        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findEdge(A, B)");
        assertThat(expected).factKeys().contains("expected to be empty");
    }

    @Test
    public void hasNoEdge() {
        var graph = new UndirectedPropertyGraph<String>();
        assertThat(graph).hasNoEdge("A", "B");
    }

    @Test
    public void hasNoEdgeIdFailed() {
        var graph = new UndirectedPropertyGraph<String>();
        graph.getEdge("A", "B").id("id");

        AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasNoEdge("id"));

        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findEdge(id)");
        assertThat(expected).factKeys().contains("expected to be empty");
    }

    @Test
    public void hasNoEdgeId() {
        var graph = new UndirectedPropertyGraph<String>();
        assertThat(graph).hasNoEdge("id");
    }

    @Test
    public void hasVertexFailed() {
        AssertionError expected = expectError(whenTesting -> whenTesting.that(new UndirectedPropertyGraph<String>()).hasVertex("id"));

        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findVertex(id)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void hasVertex() {
        var graph = new UndirectedPropertyGraph<String>();
        graph.vertex("id");
        assertThat(graph).hasVertex("id");
    }

    @Test
    public void hasNoVertexFailed() {
        AssertionError expected = expectError(whenTesting -> whenTesting.that(new UndirectedPropertyGraph<String>().vertex("A")).hasNoVertex("A"));

        assertThat(expected).factValue("value of").isEqualTo("propertyGraph.findVertex(A)");
        assertThat(expected).factKeys().contains("expected to be empty");
    }

    @Test
    public void hasNoVertex() {
        var graph = new UndirectedPropertyGraph<String>();
        assertThat(graph).hasNoVertex("id");
    }

    private static AssertionError expectError(SimpleSubjectBuilderCallback<PropertyGraphSubject, PropertyGraph> assertionCallback) {
        return ExpectFailure.expectFailureAbout(graphs(), assertionCallback);
    }
}
