package publicapi.graphviz;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.DirectedSimpleGraphs;

import java.io.IOException;
import java.io.StringWriter;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;
import static com.google.common.truth.Truth.assertThat;

public class DirectedDotWriterTest {

    @DirectedSimpleGraphs
    void strictDirected(Graph<String> graph) throws IOException {
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).startsWith("strict digraph {");
    }

    @DirectedSimpleGraphs
    void containsDirectedEdge(Graph<String> graph) throws IOException {
        graph.edge("A", "B");
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).isEqualTo("strict digraph {\n" +
                "  A -> B\n" +
                "}\n");
    }

    @DirectedSimpleGraphs
    void containsVertices(Graph<String> graph) throws IOException {
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
