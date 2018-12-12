package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.*;
import testframework.SimpleGraphs;

public class GraphVertexMethods {

    @SimpleGraphs
    void testRemoveVertex(SimpleGraph graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        graph.removeVertex("A");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getVertices()).doesNotContainKey("A");
    }

    @SimpleGraphs
    void testRemoveNull(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeVertex(null));
        assertThat(thrown).hasMessage("id must not be null.");
    }

    @SimpleGraphs
    void testRemoveVertexMissing(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeVertex("A"));
        assertThat(thrown).hasMessage("vertex 'A' not found.");
    }

    @SimpleGraphs
    void addNewVertex(SimpleGraph graph) {
        var vertex = graph.vertex("id");
        assertThat(graph).hasVertex("id").isSameAs(vertex);
        assertThat(vertex).hasId("id");
    }

    @SimpleGraphs
    void addExistingVertex(SimpleGraph graph) {
        var vertexA = graph.vertex("A");
        var vertexB = graph.vertex("A");
        assertThat(vertexA).isSameAs(vertexB);
    }
}
