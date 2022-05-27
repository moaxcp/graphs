package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestGraphs;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemovedEdge {
    @TestGraphs
    void setId(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setId("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestGraphs
    void id(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.id("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestGraphs
    void setFrom(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setFrom("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestGraphs
    void from(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.from("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestGraphs
    void setTo(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setTo("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestGraphs
    void to(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.to("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestGraphs
    void setProperty(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setProperty("name", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestGraphs
    void removeProperty(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.removeProperty("name"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }
}
