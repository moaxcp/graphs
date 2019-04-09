package publicapi.graphviz;

import com.github.moaxcp.graphs.DirectedGraph;
import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.graphviz.Dot;
import org.junit.jupiter.api.Test;
import testframework.DirectedSimpleGraphs;
import testframework.SimpleGraphs;
import testframework.UndirectedSimpleGraphs;

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

    @DirectedSimpleGraphs
    void toImageDirected(Graph<String> graph) throws IOException {
        graph.edge("A", "B");
        var image = dot(graph).toImage();
        assertThat(image).isNotNull();
    }

    @UndirectedSimpleGraphs
    void toImageUndirected(Graph<String> graph) throws IOException {
        graph.edge("A", "B");
        var image = dot(graph).toImage();
        assertThat(image).isNotNull();
    }
}
