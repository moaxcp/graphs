package publicapitests.graphmembers;

import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Vertices {
    Graph graph = new Graph("graph");

    @Test
    void verticesIsEmpty() {
        assertThat(graph.getVertices()).isEmpty();
    }

    @Test
    void vertexAddsToVertices() {
        var vertex = graph.vertex("id");
        assertThat(graph.getVertices()).containsExactly("id", vertex);
    }

    @Test
    void verticesIsUnmodifiable() {
        var vertex = graph.vertex("id");
        assertThrows(UnsupportedOperationException.class, () -> graph.getVertices().put("id", vertex));
    }

    @Test
    void removeVertexFromVertices() {
        var vertex = graph.vertex("id");
        assertThat(graph.getVertices()).containsExactly("id", vertex);
        graph.removeVertex("id");
        assertThat(graph.getVertices()).isEmpty();
    }

    @Test
    void addExistingVertex() {
        graph.vertex("A");
        var vertex = graph.vertex("A");
        assertThat(graph.getVertices()).containsExactly("A", vertex);
    }

    @Test
    void testRemoveVertex() {
        graph.vertex("A");
        graph.removeVertex("A");
        assertThat(graph.getVertices()).doesNotContainKey("A");
    }
}
