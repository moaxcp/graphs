package publicapi;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class RemovedEdge {
    @SimpleGraphs
    void setId(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setId("id"));
        assertThat(thrown).hasMessage("Edge is not in graph.");
    }

    @SimpleGraphs
    void id(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.id("id"));
        assertThat(thrown).hasMessage("Edge is not in graph.");
    }

    @SimpleGraphs
    void setFrom(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setFrom("C"));
        assertThat(thrown).hasMessage("Edge is not in graph.");
    }

    @SimpleGraphs
    void from(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.from("C"));
        assertThat(thrown).hasMessage("Edge is not in graph.");
    }

    @SimpleGraphs
    void setTo(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setTo("C"));
        assertThat(thrown).hasMessage("Edge is not in graph.");
    }

    @SimpleGraphs
    void to(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.to("C"));
        assertThat(thrown).hasMessage("Edge is not in graph.");
    }

    @SimpleGraphs
    void setProperty(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.setProperty("name", "value"));
        assertThat(thrown).hasMessage("Edge is not in graph.");
    }

    @SimpleGraphs
    void removeProperty(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        graph.removeEdge("A", "B");
        Throwable thrown = assertThrows(IllegalStateException.class, () -> edge.removeProperty("name"));
        assertThat(thrown).hasMessage("Edge is not in graph.");
    }
}
