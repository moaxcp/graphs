package publicapitests.graphmembers;

import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class Edges {
    Graph graph = new Graph("graph");

    @Test
    void edgesIsEmpty() {
        assertThat(graph.getEdges()).isEmpty();
    }

    @Test
    void testAddNewEdge() {
        var edge = graph.edge("from", "to");
        assertThat(graph.getEdges()).containsExactly(edge);
    }
}
