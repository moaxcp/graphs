package publicapi;

import com.github.moaxcp.graphs.Graph;
import testframework.SimpleGraphs;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemovedVertex {
    @SimpleGraphs
    void setId(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.setId("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void id(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.id("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void setProperty(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.setProperty("name", "value"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void property(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.property("name", "value"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void removeProperty(Graph graph) {
        var vertex = graph.vertex("A").property("name", "value");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.removeProperty("name"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void connectsTo(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.connectsTo("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void connectsFrom(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.connectsFrom("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void adjacentEdges(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.adjacentEdges());
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void inEdges(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.inEdges());
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void outEdges(Graph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.outEdges());
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }
}
