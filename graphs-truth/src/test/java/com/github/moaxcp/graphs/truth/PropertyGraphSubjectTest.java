package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import com.google.common.truth.ExpectFailure;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.truth.GraphSubject.graphs;
import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.ExpectFailure.SimpleSubjectBuilderCallback;
import static com.google.common.truth.ExpectFailure.assertThat;



public class PropertyGraphSubjectTest {
    @Test
    public void doesNotHaveVertex() {
        AssertionError expected = expectError(whenTesting -> whenTesting.that(new UndirectedPropertyGraph()).hasVertex("id"));

        assertThat(expected).factValue("value of").isEqualTo("graph.findVertex(id)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void doesHaveVertex() {
        PropertyGraph graph = new UndirectedPropertyGraph();
        graph.vertex("id");
        assertThat(graph).hasVertex("id");
    }

    @Test
    public void doesNotHaveEdge() {
        PropertyGraph graph = new UndirectedPropertyGraph();
        graph.vertex("A");
        graph.vertex("B");

        AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasEdge("A", "B"));

        assertThat(expected).factValue("value of").isEqualTo("graph.findEdge(A, B)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void doesHaveEdge() {
        PropertyGraph graph = new UndirectedPropertyGraph();
        graph.edge("A", "B");
        assertThat(graph).hasEdge("A", "B");
    }

    private static AssertionError expectError(SimpleSubjectBuilderCallback<GraphSubject, PropertyGraph> assertionCallback) {
        return ExpectFailure.expectFailureAbout(graphs(), assertionCallback);
    }
}
