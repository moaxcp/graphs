package publicapitests.edgemethods;

import com.github.moaxcp.graphs.Graph;
import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static com.github.moaxcp.graphs.GraphSubject.assertThat;
import static com.github.moaxcp.graphs.EdgeSubject.assertThat;

public class GraphEdgeMethods {
    Graph graph = new Graph("graph");

    @Test
    void addNewEdge() {
        var edge = graph.edge("from", "to");
        assertThat(graph).hasEdge("from", "to").isSameAs(edge);
    }

    @Test
    void newEdgeHasNoId() {
        var edge = graph.edge("from", "to");
        assertThat(edge).id().isEmpty();
    }

    @Test
    void newEdgeHasToFromProperties() {
        var edge = graph.edge("from", "to");
        assertThat(edge).thatProperty("from").hasValue("from");
        assertThat(edge).thatProperty("to").hasValue("to");
    }

    @Test
    void newEdgeHasToFromLocalProperties() {
        var edge = graph.edge("from", "to");
        assertThat(edge).thatLocal("from").isEqualTo("from");
        assertThat(edge).thatLocal("to").isEqualTo("to");
    }

    @Test
    void returnedEdgeIsSame() {
        var edge1 = graph.edge("from", "to");
        var edge2 = graph.edge("from", "to");
        assertThat(edge1).isSameAs(edge2);
    }
}
