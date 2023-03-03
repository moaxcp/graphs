package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestPropertyGraphs;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemovedEdge {
    @TestPropertyGraphs
    void setId(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setId("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestPropertyGraphs
    void id(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.id("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestPropertyGraphs
    void setFrom(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setSource("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestPropertyGraphs
    void from(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.source("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestPropertyGraphs
    void setTo(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setTarget("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestPropertyGraphs
    void to(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.target("C"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestPropertyGraphs
    void setProperty(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setProperty("name", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }

    @TestPropertyGraphs
    void removeProperty(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.removeProperty("name"));
        assertThat(thrown).hasMessageThat().isEqualTo("Edge is not in graph.");
    }
}
