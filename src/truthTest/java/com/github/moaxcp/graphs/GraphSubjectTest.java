package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.GraphSubject.graphs;
import static com.google.common.truth.ExpectFailure.*;
import static com.github.moaxcp.graphs.Truth.*;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import com.google.common.truth.ExpectFailure;
import org.junit.jupiter.api.Test;



public class GraphSubjectTest {
    @Test
    public void doesNotHaveVertex() {
        AssertionError expected = expectError(whenTesting -> whenTesting.that(new UndirectedEventGraph()).hasVertex("id"));

        assertThat(expected).factValue("value of").isEqualTo("graph.findVertex(id)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void doesHaveVertex() {
        SimpleGraph graph = new UndirectedEventGraph();
        graph.vertex("id");
        assertThat(graph).hasVertexThat("id");
    }

    @Test
    public void doesNotHaveEdge() {
        SimpleGraph graph = new UndirectedEventGraph();
        graph.vertex("A");
        graph.vertex("B");

        AssertionError expected = expectError(whenTesting -> whenTesting.that(graph).hasEdge("A", "B"));

        assertThat(expected).factValue("value of").isEqualTo("graph.findEdge(A, B)");
        assertThat(expected).factKeys().contains("expected to be present");
    }

    @Test
    public void doesHaveEdge() {
        SimpleGraph graph = new UndirectedEventGraph();
        graph.edge("A", "B");
        assertThat(graph).hasEdge("A", "B");
    }

    private static AssertionError expectError(SimpleSubjectBuilderCallback<GraphSubject, SimpleGraph> assertionCallback) {
        return ExpectFailure.expectFailureAbout(graphs(), assertionCallback);
    }
}
