package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.TestDirectedGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class DirectedGraphEdgeMethods {

    @TestDirectedGraphs
    void twoEdgesConnectSameVertices(Graph<String> graph) {
        var edge1 = graph.getEdge("A", "B");
        var edge2 = graph.getEdge("B", "A");

        assertThat(edge1).isNotEqualTo(edge2);
        assertThat(edge1).isNotSameInstanceAs(edge2);
        assertThat(graph).hasEdge("A", "B");
        assertThat(graph).hasEdge("B", "A");
    }

    @TestDirectedGraphs
    void otherEdgeIsNotRemoved(Graph<String> graph) {
        graph.edge("A", "B").edge("B", "A");
        graph.removeEdge("A", "B");

        assertThat(graph).hasEdge("B", "A");
    }
}
