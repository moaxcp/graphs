package publicapi.greenrobot.graph;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class MemberEdgeIds {
    UndirectedGraph graph = new UndirectedGraph("graph");

    @Test
    void addEdge() {
        var edge = graph.edge("from", "to").id("id");
        assertThat(graph.getEdgeIds()).containsExactly("id", edge);
    }
}
