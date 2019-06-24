package publicapi.graphviz;

import com.github.moaxcp.graphs.Graph;
import testframework.UndirectedSimpleGraphs;

import java.io.IOException;
import java.io.StringWriter;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;
import static com.google.common.truth.Truth.assertThat;

public class UndirectedDotWriterTest {

    @UndirectedSimpleGraphs
    void strictUndirected(Graph<String> graph) throws IOException {
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).startsWith("strict graph {");
    }

    @UndirectedSimpleGraphs
    void containsUndirectedEdge(Graph<String> graph) throws IOException {
        graph.edge("A", "B");
        StringWriter s = new StringWriter();
        dot(graph).write(s);
        assertThat(s.toString()).isEqualTo("strict graph {\n" +
                "  A -- B\n" +
                "}\n");
    }

    @UndirectedSimpleGraphs
    void containsVertices(Graph<String> graph) throws IOException {
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
