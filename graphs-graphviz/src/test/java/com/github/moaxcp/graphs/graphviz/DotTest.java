package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.DirectedGraph;
import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;
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
        var writer = dot(new DirectedGraph<String>());
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> writer.write(null));
        assertThat(thrown).hasMessageThat().isEqualTo("writer must not be null.");
    }

    @SimpleGraphs
    void endsWithBraceWriter(Graph<String> graph) throws IOException {
        var writer = dot(graph);
        StringWriter s = new StringWriter();
        writer.write(s);
        assertThat(s.toString()).endsWith("}\n");
    }

    @SimpleGraphs
    void endsWithBrace(Graph<String> graph) {
        var s = dot(graph).toString();
        assertThat(s).endsWith("}\n");
    }

    @SimpleGraphs
    void graphAttributes(Graph<String> graph) {
        graph.property("color", "blue");
        graph.property("label", "graph");
        graph.edge("A", "B");
        var s = dot(graph).toString();
        assertThat(s).contains("color=blue\n  label=graph");
    }

    @SimpleGraphs
    void edgeAttributes(Graph<String> graph) {
        graph.getEdge("A", "B").property("color", "blue").property("size", 10);
        var s = dot(graph).toString();
        assertThat(s).containsMatch("A -[->] B \\[color=blue, size=10\\]");
    }

    @SimpleGraphs
    void vertexAttributes(Graph<String> graph) {
        graph.getVertex("A").property("color", "blue").property("size", 10);
        var s = dot(graph).toString();
        assertThat(s).contains("A [color=blue, size=10]");
    }

    @SimpleGraphs
    void graphEdgeAttributes(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edgeProperty("color", "blue");
        var s = dot(graph).toString();
        assertThat(s).contains("edge [color=blue]");
    }

    @SimpleGraphs
    void graphVertexAttributes(Graph<String> graph) {
        graph.vertex("A");
        graph.vertexProperty("color", "blue");
        var s = dot(graph).toString();
        assertThat(s).contains("node [color=blue]");
    }

    @SimpleGraphs
    void toImageDirected(Graph<String> graph) throws IOException {
        graph.property("rankdir", "LR");
        graph.edge("A", "B").property("color", "red");
        graph.vertex("A").property("color", "green");
        graph.vertex("B").property("color", "blue");
        var image = dot(graph).toImage();
        assertThat(image).isNotNull();
    }
}
