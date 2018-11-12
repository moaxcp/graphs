package publicapi.greenrobot.graph;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class MemberEdges {
    UndirectedGraph graph = new UndirectedGraph("graph");

    @Test
    void edgesIsEmpty() {
        assertThat(graph.getEdges()).isEmpty();
    }

    @Test
    void addNewEdge() {
        var edge = graph.edge("from", "to");
        assertThat(graph.getEdges()).containsExactly(edge);
    }

    @Test
    void removeEdge() {
        graph.edge("A", "B");
        graph.removeEdge("A", "B");
        assertThat(graph.getEdges()).hasSize(0);
    }
}
