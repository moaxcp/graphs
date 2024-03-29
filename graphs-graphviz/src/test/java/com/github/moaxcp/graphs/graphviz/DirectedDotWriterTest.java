package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestDirectedPropertyGraphs;

import java.io.IOException;
import java.io.StringWriter;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;
import static com.google.common.truth.Truth.assertThat;

public class DirectedDotWriterTest {

    @TestDirectedPropertyGraphs
    void strictDirected(PropertyGraph<String> graph) throws IOException {
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).startsWith("strict digraph {");
    }

    @TestDirectedPropertyGraphs
    void containsDirectedEdge(PropertyGraph<String> graph) throws IOException {
        graph.edge("A", "B");
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).isEqualTo("strict digraph {\n" +
                "  A -> B\n" +
                "}\n");
    }

    @TestDirectedPropertyGraphs
    void containsVertices(PropertyGraph<String> graph) throws IOException {
        graph.vertex("A");
        graph.vertex("B");
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).isEqualTo("strict digraph {\n" +
                "  A\n" +
                "  B\n" +
                "}\n");
    }
}
