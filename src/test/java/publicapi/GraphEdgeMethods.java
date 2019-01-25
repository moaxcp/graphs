package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.Graph;
import testframework.SimpleGraphs;

public class GraphEdgeMethods {

    @SimpleGraphs
    void addNewEdge(Graph graph) {
        var edge = graph.edge("A", "B");
        assertThat(graph).hasEdge("A", "B").isSameAs(edge);
    }

    @SimpleGraphs
    void newEdgeHasNoId(Graph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasIdThat().isEmpty();
    }

    @SimpleGraphs
    void newEdgeFromIsSet(Graph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasFromThat().isEqualTo("A");
    }

    @SimpleGraphs
    void newEdgeToIsSet(Graph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasToThat().isEqualTo("B");
    }

    @SimpleGraphs
    void addExistingEdge(Graph graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("A", "B");
        assertThat(edge1).isSameAs(edge2);
    }

    @SimpleGraphs
    void removeEdge(Graph graph) {
        graph.edge("A", "B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
    }

    @SimpleGraphs
    void removeEdgeThatDoesNotExist(Graph graph) {
        graph.vertex("A");
        graph.vertex("B");
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
        assertThat(thrown).hasMessage("edge from 'A' to 'B' not found.");
    }

    @SimpleGraphs
    void removeEdgeWithIdNullId(Graph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
        assertThat(thrown).hasMessage("id must not be null.");
    }

    @SimpleGraphs
    void removeEdgeWithId(Graph graph) {
        graph.edge("A", "B").id("id");
        graph.removeEdge("id");
        assertThat(graph).hasNoEdge("id");
    }
}
