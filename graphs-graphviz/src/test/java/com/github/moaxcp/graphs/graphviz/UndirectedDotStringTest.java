package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestUndirectedPropertyGraphs;

import java.io.IOException;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;
import static com.google.common.truth.Truth.assertThat;

public class UndirectedDotStringTest {

    @TestUndirectedPropertyGraphs
    void strictUndirected(PropertyGraph<String> graph) throws IOException {
        var s = dot(graph).toString();
        assertThat(s).startsWith("strict graph {");
    }

    @TestUndirectedPropertyGraphs
    void containsUndirectedEdge(PropertyGraph<String> graph) throws IOException {
        graph.edge("A", "B");
        var s = dot(graph).toString();
        assertThat(s).isEqualTo("strict graph {\n" +
                "  A -- B\n" +
                "}\n");
    }

    @TestUndirectedPropertyGraphs
    void containsVertices(PropertyGraph<String> graph) throws IOException {
        graph.vertex("A");
        graph.vertex("B");
        var s = dot(graph).toString();
        assertThat(s).isEqualTo("strict graph {\n" +
                "  A\n" +
                "  B\n" +
                "}\n");
    }
}
