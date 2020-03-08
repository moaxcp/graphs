package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemovedVertex {
    @SimpleGraphs
    void setId(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.setId("B"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void id(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.id("B"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void setProperty(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.setProperty("name", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void property(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.property("name", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void removeProperty(Graph<String> graph) {
        var vertex = graph.getVertex("A").property("name", "value");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.removeProperty("name"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void connectsTo(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.connectsTo("B"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void connectsFrom(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.connectsFrom("B"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void adjacentEdges(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.adjacentEdges());
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void inEdges(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.inEdges());
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @SimpleGraphs
    void outEdges(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.outEdges());
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }
}