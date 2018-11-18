package publicapi.greenrobot.graph;

import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class MemberEdgeIds {
    UndirectedEventGraph graph = new UndirectedEventGraph("graph");

    @Test
    void addEdge() {
        var edge = graph.edge("from", "to").id("id");
        assertThat(graph.getEdgeIds()).containsExactly("id", edge);
    }
}
