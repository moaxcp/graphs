package publicapi.directedgraph;

import com.github.moaxcp.graphs.DirectedGraph;
import org.junit.jupiter.api.Test;
import static com.github.moaxcp.graphs.Truth.assertThat;

public class DirectedGraphEdgeMethods {
    DirectedGraph graph = new DirectedGraph();
    @Test
    void edgeIsDirected() {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("B", "A");

        assertThat(edge1).isNotEqualTo(edge2);
        assertThat(edge1).isNotSameAs(edge2);
        assertThat(graph).hasEdge("A", "B");
        assertThat(graph).hasEdge("B", "A");
    }

    @Test
    void otherEdgeIsNotRemoved() {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("B", "A");
        graph.removeEdge("A", "B");


    }
}
