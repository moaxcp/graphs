package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.Graph;
import testframework.DirectedSimpleGraphs;

public class DirectedGraphEdgeMethods {

    @DirectedSimpleGraphs
    void twoEdgesConnectSameVertices(Graph<String> graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("B", "A");

        assertThat(edge1).isNotEqualTo(edge2);
        assertThat(edge1).isNotSameAs(edge2);
        assertThat(graph).hasEdge("A", "B");
        assertThat(graph).hasEdge("B", "A");
    }

    @DirectedSimpleGraphs
    void otherEdgeIsNotRemoved(Graph<String> graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("B", "A");
        graph.removeEdge("A", "B");
    }
}
