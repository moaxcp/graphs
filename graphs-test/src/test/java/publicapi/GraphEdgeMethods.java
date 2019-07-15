package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphEdgeMethods {

    @SimpleGraphs
    void addNewEdge(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(graph).hasEdge("A", "B").isSameAs(edge);
    }

    @SimpleGraphs
    void newEdgeHasNoId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasIdThat().isEmpty();
    }

    @SimpleGraphs
    void newEdgeFromIsSet(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasFromThat().isEqualTo("A");
    }

    @SimpleGraphs
    void newEdgeToIsSet(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasToThat().isEqualTo("B");
    }

    @SimpleGraphs
    void addExistingEdge(Graph<String> graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("A", "B");
        assertThat(edge1).isSameAs(edge2);
    }

    @SimpleGraphs
    void removeEdge(Graph<String> graph) {
        graph.edge("A", "B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
    }

    @SimpleGraphs
    void findEdgeMissingFrom(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @SimpleGraphs
    void findEdgeMissingTo(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge("A", null));
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @SimpleGraphs
    void findEdge(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(graph.findEdge("A", "B")).hasValue(edge);
    }

    @SimpleGraphs
    void findEdgeMissingId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @SimpleGraphs
    void findEdgeById(Graph<String> graph) {
        var edge = graph.getEdge("A", "B").id("id");
        assertThat(graph.findEdge("id")).hasValue(edge);
    }

    @SimpleGraphs
    void removeEdgeThatDoesNotExist(Graph<String> graph) {
        graph.vertex("A");
        graph.vertex("B");
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
        assertThat(thrown).hasMessage("edge from 'A' to 'B' not found.");
    }

    @SimpleGraphs
    void removeEdgeWithIdNullId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
        assertThat(thrown).hasMessage("id must not be null.");
    }

    @SimpleGraphs
    void removeEdgeWithId(Graph<String> graph) {
        graph.getEdge("A", "B").id("id");
        graph.removeEdge("id");
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void removeByFromTo(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
    }

    @SimpleGraphs
    void removeById(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge with id 'id' not found.");
    }

    @SimpleGraphs
    void removeWithNullId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @SimpleGraphs
    void removeWithNullFrom(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null, "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @SimpleGraphs
    void removeWithNullTo(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge("A", null));
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }
}
