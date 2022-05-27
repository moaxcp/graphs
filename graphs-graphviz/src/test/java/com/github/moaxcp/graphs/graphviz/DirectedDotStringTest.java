package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestDirectedGraphs;

import java.io.IOException;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;
import static com.google.common.truth.Truth.assertThat;

public class DirectedDotStringTest {

    @TestDirectedGraphs
    void strictDirected(PropertyGraph<String> graph) throws IOException {
        var s = dot(graph).toString();
        assertThat(s).startsWith("strict digraph {");
    }

    @TestDirectedGraphs
    void containsDirectedEdge(PropertyGraph<String> graph) throws IOException {
        graph.edge("A", "B");
        var s = dot(graph).toString();
        assertThat(s).isEqualTo("strict digraph {\n" +
                "  A -> B\n" +
                "}\n");
    }

    @TestDirectedGraphs
    void containsVertices(PropertyGraph<String> graph) throws IOException {
        graph.vertex("A");
        graph.vertex("B");
        var s = dot(graph).toString();
        assertThat(s).isEqualTo("strict digraph {\n" +
                "  A\n" +
                "  B\n" +
                "}\n");
    }
}
