package publicapitests.graphmembers;

import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class EdgeIds {
    Graph graph = new Graph("graph");

    @Test
    void addEdge() {
        var edge = graph.edge("from", "to").id("id");
        assertThat(graph.getEdgeIds()).containsExactly("id", edge);
    }
}
