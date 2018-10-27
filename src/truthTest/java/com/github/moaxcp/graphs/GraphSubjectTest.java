package com.github.moaxcp.graphs;

import com.google.common.truth.ExpectFailure;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.moaxcp.graphs.GraphSubject.graphs;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.ExpectFailure.assertThat;
import static com.github.moaxcp.graphs.GraphSubject.assertThat;


public class GraphSubjectTest {
    @Test
    public void doesNotHaveVertex() {
        AssertionError expected = expectError(whenTesting -> whenTesting.that(new Graph()).hasVertex("id"));

        assertThat(expected).factValue("value of").isEqualTo("graph.findVertex(id)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void doesHaveVertex() {
        Graph graph = new Graph();
        graph.vertex("id");
        assertThat(graph).hasVertex("id");
    }

    @Test
    public void doesNotHaveEdge() {
        Graph graph = new Graph();
        graph.vertex("A");
        graph.vertex("B");

        AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasEdge("A", "B"));

        assertThat(expected).factValue("value of").isEqualTo("graph.findEdge(A, B)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void doesHaveEdge() {
        Graph graph = new Graph();
        graph.edge("A", "B");
        assertThat(graph).hasEdge("A", "B");
    }

    private static AssertionError expectError(ExpectFailure.SimpleSubjectBuilderCallback<GraphSubject, Graph> assertionCallback) {
        return ExpectFailure.expectFailureAbout(graphs(), assertionCallback);
    }
}
