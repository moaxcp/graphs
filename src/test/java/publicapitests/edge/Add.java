package publicapitests.edge;

import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class Add {
    Graph graph = new Graph("graph");

    @Test
    void addNewEdge() {
        Graph.Edge edge = graph.edge("from", "to");
        assertThat(edge.getLocal()).containsExactly("from", "from", "to", "to");
        assertThat(graph.getVertices()).containsKey("from");
        assertThat(graph.getVertices()).containsKey("to");
        assertThat(graph.getEdges()).containsExactly(edge);
    }
}
