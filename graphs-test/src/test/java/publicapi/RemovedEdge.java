package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemovedEdge {
    @SimpleGraphs
    void setId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setId("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @SimpleGraphs
    void id(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.id("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @SimpleGraphs
    void setFrom(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setFrom("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @SimpleGraphs
    void from(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.from("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @SimpleGraphs
    void setTo(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setTo("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @SimpleGraphs
    void to(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.to("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @SimpleGraphs
    void setProperty(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setProperty("name", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @SimpleGraphs
    void removeProperty(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.removeProperty("name"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }
}