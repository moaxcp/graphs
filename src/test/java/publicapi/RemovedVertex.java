package publicapi;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class RemovedVertex {
    @SimpleGraphs
    void setId(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.setId("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void id(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.id("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void setProperty(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.setProperty("name", "value"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void property(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.property("name", "value"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void removeProperty(SimpleGraph graph) {
        var vertex = graph.vertex("A").property("name", "value");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.removeProperty("name"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void connectsTo(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.connectsTo("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void connectsFrom(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.connectsFrom("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void edgeTo(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.edgeTo("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void edgeFrom(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.edgeFrom("B"));
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void adjacentEdges(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.adjacentEdges());
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void inEdges(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.inEdges());
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }

    @SimpleGraphs
    void outEdges(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        graph.removeVertex("A");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> vertex.outEdges());
        assertThat(thrown).hasMessage("Vertex is not in graph.");
    }
}
