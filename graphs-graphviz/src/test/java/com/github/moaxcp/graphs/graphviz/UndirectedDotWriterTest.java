package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestUndirectedPropertyGraphs;

import java.io.IOException;
import java.io.StringWriter;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;
import static com.google.common.truth.Truth.assertThat;

public class UndirectedDotWriterTest {

    @TestUndirectedPropertyGraphs
    void strictUndirected(PropertyGraph<String> graph) throws IOException {
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).startsWith("strict graph {");
    }

    @TestUndirectedPropertyGraphs
    void containsUndirectedEdge(PropertyGraph<String> graph) throws IOException {
        graph.edge("A", "B");
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).isEqualTo("strict graph {\n" +
                "  A -- B\n" +
                "}\n");
    }

    @TestUndirectedPropertyGraphs
    void containsVertices(PropertyGraph<String> graph) throws IOException {
        graph.vertex("A");
        graph.vertex("B");
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).isEqualTo("strict graph {\n" +
                "  A\n" +
                "  B\n" +
                "}\n");
    }
}
