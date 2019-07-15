package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphVertexMethods {

    @SimpleGraphs
    void testRemoveVertex(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        graph.removeVertex("A");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getVertices()).doesNotContainKey("A");
    }

    @SimpleGraphs
    void testRemoveNull(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeVertex(null));
        assertThat(thrown).hasMessage("id must not be null.");
    }

    @SimpleGraphs
    void testRemoveVertexMissing(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeVertex("A"));
        assertThat(thrown).hasMessage("vertex 'A' not found.");
    }

    @SimpleGraphs
    void addNewVertex(Graph<String> graph) {
        var vertex = graph.getVertex("id");
        assertThat(graph).hasVertex("id").isSameAs(vertex);
        assertThat(vertex).hasId("id");
    }

    @SimpleGraphs
    void addExistingVertex(Graph<String> graph) {
        var vertexA = graph.vertex("A");
        var vertexB = graph.vertex("A");
        assertThat(vertexA).isSameAs(vertexB);
    }

    @SimpleGraphs
    void addNewVertexWithMapProperties(Graph<String> graph) {
        graph.vertex("A", Map.of("name1", "value1", "name2", "value2"));

    }
}
