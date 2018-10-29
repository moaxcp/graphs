package publicapi.graph;

import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsingRemovedElements {
    Graph graph = new Graph();
    @Test
    void edgeSetFrom() {
        var edge = graph.edge("from", "to");
        graph.removeEdge("from", "to");
        var exception = assertThrows(IllegalStateException.class, () -> edge.setFrom("X"));
        assertThat(exception).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @Test
    void edgeSetTo() {
        var edge = graph.edge("from", "to");
        graph.removeEdge("from", "to");
        var exception = assertThrows(IllegalStateException.class, () -> edge.setTo("X"));
        assertThat(exception).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @Test
    void edgeSetProperty() {
        var edge = graph.edge("from", "to");
        graph.removeEdge("from", "to");
        var exception = assertThrows(IllegalStateException.class, () -> edge.setProperty("key", "value"));
        assertThat(exception).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @Test
    void vertexSetId() {
        var vertex = graph.vertex("id");
        graph.removeVertex("id");
        var exception = assertThrows(IllegalStateException.class, () -> vertex.setId("X"));
        assertThat(exception).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @Test
    void vertexSetProperty() {
        var vertex = graph.vertex("id");
        graph.removeVertex("id");
        var exception = assertThrows(IllegalStateException.class, () -> vertex.setProperty("key", "value"));
        assertThat(exception).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }
}
