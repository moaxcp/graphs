package publicapi.graphviz;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.DirectedSimpleGraphs;

import java.io.IOException;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;
import static com.google.common.truth.Truth.assertThat;

public class DirectedDotStringTest {

    @DirectedSimpleGraphs
    void strictDirected(Graph<String> graph) throws IOException {
        var s = dot(graph).toString();
        assertThat(s).startsWith("strict digraph {");
    }

    @DirectedSimpleGraphs
    void containsDirectedEdge(Graph<String> graph) throws IOException {
        graph.edge("A", "B");
        var s = dot(graph).toString();
        assertThat(s).isEqualTo("strict digraph {\n" +
                "  A -> B\n" +
                "}\n");
    }

    @DirectedSimpleGraphs
    void containsVertices(Graph<String> graph) throws IOException {
        graph.vertex("A");
        graph.vertex("B");
        var s = dot(graph).toString();
        assertThat(s).isEqualTo("strict digraph {\n" +
                "  A\n" +
                "  B\n" +
                "}\n");
    }
}
