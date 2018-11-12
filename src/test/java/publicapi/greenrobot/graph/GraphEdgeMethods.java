package publicapi.greenrobot.graph;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static com.github.moaxcp.graphs.Truth.assertThat;

public class GraphEdgeMethods {
    UndirectedGraph graph = new UndirectedGraph("graph");

    @Test
    void addNewEdge() {
        var edge = graph.edge("A", "B");
        assertThat(graph).hasEdge("A", "B").isSameAs(edge);
    }

    @Test
    void newEdgeHasNoId() {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasIdThat().isEmpty();
    }

    @Test
    void newEdgeHasToFromProperties() {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasPropertyThat("from").hasValue("A");
        assertThat(edge).hasPropertyThat("to").hasValue("B");
    }

    @Test
    void newEdgeHasToFromLocalProperties() {
        var edge = graph.edge("A", "B");
        assertThat(edge).thatLocal("from").isEqualTo("A");
        assertThat(edge).thatLocal("to").isEqualTo("B");
    }

    @Test
    void addExistingEdge() {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("A", "B");
        assertThat(edge1).isSameAs(edge2);
    }

    @Test
    void removeEdge() {
        graph.edge("A", "B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
    }

    @Test
    void removeEdgeThatDoesNotExist() {
        graph.vertex("A");
        graph.vertex("B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
        assertThat(graph).hasVertices("A", "B");
    }
}
