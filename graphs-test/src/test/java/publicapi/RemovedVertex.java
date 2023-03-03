package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestPropertyGraphs;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemovedVertex {
    @TestPropertyGraphs
    void setId(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.setId("B"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void id(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.id("B"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void setProperty(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.setProperty("name", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void property(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.property("name", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void removeProperty(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A").property("name", "value");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.removeProperty("name"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void connectsTo(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.connectsTo("B"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void connectsFrom(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.connectsFrom("B"));
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void adjacentEdges(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.adjacentEdges());
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void inEdges(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.inEdges());
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }

    @TestPropertyGraphs
    void outEdges(PropertyGraph<String> graph) {
        var vertex = graph.getVertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.outEdges());
        assertThat(thrown).hasMessageThat().isEqualTo("Vertex is not in graph.");
    }
}
