package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestPropertyGraphs;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DotTest {

    @Test
    void graphIsNull() {
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> dot(null));
        assertThat(thrown).hasMessageThat().isEqualTo("graph must not be null.");
    }

    @Test
    void writerIsNull() throws IOException {
        var writer = dot(new DirectedPropertyGraph<String>());
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> writer.write(null));
        assertThat(thrown).hasMessageThat().isEqualTo("writer must not be null.");
    }

    @TestPropertyGraphs
    void endsWithBraceWriter(PropertyGraph<String> graph) throws IOException {
        var writer = dot(graph);
        StringWriter s = new StringWriter();
        writer.write(s);
        assertThat(s.toString()).endsWith("}\n");
    }

    @TestPropertyGraphs
    void endsWithBrace(PropertyGraph<String> graph) {
        var s = dot(graph).toString();
        assertThat(s).endsWith("}\n");
    }

    @TestPropertyGraphs
    void graphAttributes(PropertyGraph<String> graph) {
        graph.property("color", "blue");
        graph.property("label", "graph");
        graph.edge("A", "B");
        var s = dot(graph).toString();
        assertThat(s).contains("color=blue\n  label=graph");
    }

    @TestPropertyGraphs
    void edgeAttributes(PropertyGraph<String> graph) {
        graph.getEdge("A", "B").property("color", "blue").property("size", 10);
        var s = dot(graph).toString();
        assertThat(s).containsMatch("A -[->] B \\[color=blue, size=10\\]");
    }

    @TestPropertyGraphs
    void vertexAttributes(PropertyGraph<String> graph) {
        graph.getVertex("A").property("color", "blue").property("size", 10);
        var s = dot(graph).toString();
        assertThat(s).contains("A [color=blue, size=10]");
    }

    @TestPropertyGraphs
    void graphEdgeAttributes(PropertyGraph<String> graph) {
        graph.edge("A", "B");
        graph.edgeProperty("color", "blue");
        var s = dot(graph).toString();
        assertThat(s).contains("edge [color=blue]");
    }

    @TestPropertyGraphs
    void graphVertexAttributes(PropertyGraph<String> graph) {
        graph.vertex("A");
        graph.vertexProperty("color", "blue");
        var s = dot(graph).toString();
        assertThat(s).contains("node [color=blue]");
    }

    @TestPropertyGraphs
    void toImageDirected(PropertyGraph<String> graph) throws IOException {
        graph.property("rankdir", "LR");
        graph.edge("A", "B").property("color", "red");
        graph.vertex("A").property("color", "green");
        graph.vertex("B").property("color", "blue");
        var image = dot(graph).toImage();
        assertThat(image).isNotNull();
    }
}
